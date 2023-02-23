package br.dev.pedrolamarao.io.windows;

import java.lang.foreign.*;
import java.lang.foreign.Linker.Option.CaptureCallState;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.VarHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

public final class Kernel32
{
    public static final MemorySegment INVALID_HANDLE_VALUE = MemorySegment.ofAddress(-1);

    public static int CloseHandle (MemorySegment handle) throws Throwable
    {
        try (var arena = Arena.openConfined())
        {
            final var callState = arena.allocate(CaptureLastError.layout());
            final var result = (int) CloseHandle.invokeExact(callState,handle);
            LastError.set( (int) GetLastError.get(callState) );
            return result;
        }
    }

    public static MemorySegment CreateIoCompletionPort (MemorySegment device, MemorySegment port, MemorySegment key, int concurrency) throws Throwable
    {
        try (var arena = Arena.openConfined())
        {
            final var callState = arena.allocate(CaptureLastError.layout());
            final var result = (MemorySegment) CreateIoCompletionPort.invokeExact(callState,device,port,key,concurrency);
            LastError.set( (int) GetLastError.get(callState) );
            return result;
        }
    }

    public static int GetLastError ()
    {
        return LastError.get();
    }

    public static int GetQueuedCompletionStatus (MemorySegment port, MemorySegment bytes, MemorySegment key, MemorySegment operation, int milliseconds) throws Throwable
    {
        try (var arena = Arena.openConfined())
        {
            final var callState = arena.allocate(CaptureLastError.layout());
            final var result = (int) GetQueuedCompletionStatus.invokeExact(callState,port,bytes,key,operation,milliseconds);
            LastError.set( (int) GetLastError.get(callState) );
            return result;
        }
    }

    public static int PostQueuedCompletionStatus (MemorySegment port, int bytes, MemorySegment key, MemorySegment operation) throws Throwable
    {
        try (var arena = Arena.openConfined())
        {
            final var callState = arena.allocate(CaptureLastError.layout());
            final var result = (int) PostQueuedCompletionStatus.invokeExact(callState,port,bytes,key,operation);
            LastError.set( (int) GetLastError.get(callState) );
            return result;
        }
    }

    private static final ThreadLocal<Integer> LastError = ThreadLocal.withInitial(() -> -1);

    private static final CaptureCallState CaptureLastError = Linker.Option.captureCallState("GetLastError");

    private static final MethodHandle CloseHandle;

    private static final MethodHandle CreateIoCompletionPort;

    private static final VarHandle GetLastError;

    private static final MethodHandle GetQueuedCompletionStatus;

    private static final MethodHandle PostQueuedCompletionStatus;

    static
    {
        final var linker = Linker.nativeLinker();
        final var kernel32 = SymbolLookup.libraryLookup("kernel32",SegmentScope.global());
        CloseHandle = linker.downcallHandle(
            kernel32.find("CloseHandle").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT,ADDRESS),
            CaptureLastError
        );
        CreateIoCompletionPort = linker.downcallHandle(
            kernel32.find("CreateIoCompletionPort").orElseThrow(),
            FunctionDescriptor.of(ADDRESS,ADDRESS,ADDRESS,ADDRESS,JAVA_INT),
            CaptureLastError
        );
        GetLastError = CaptureLastError.layout().varHandle(MemoryLayout.PathElement.groupElement("GetLastError"));
        GetQueuedCompletionStatus = linker.downcallHandle(
            kernel32.find("GetQueuedCompletionStatus").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT,ADDRESS,ADDRESS,ADDRESS,ADDRESS,JAVA_INT),
            CaptureLastError
        );
        PostQueuedCompletionStatus = linker.downcallHandle(
            kernel32.find("PostQueuedCompletionStatus").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT,ADDRESS,JAVA_INT,ADDRESS,ADDRESS),
            CaptureLastError
        );
    }

    private Kernel32() {}
}
