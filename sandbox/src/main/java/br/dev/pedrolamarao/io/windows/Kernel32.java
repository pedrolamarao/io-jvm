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

    public static int GetLastError ()
    {
        return LastError.get();
    }

    private static final ThreadLocal<Integer> LastError = ThreadLocal.withInitial(() -> -1);

    private static final CaptureCallState CaptureLastError = Linker.Option.captureCallState("GetLastError");

    private static final MethodHandle CloseHandle;

    private static final VarHandle GetLastError;

    static
    {
        final var linker = Linker.nativeLinker();
        final var kernel32 = SymbolLookup.libraryLookup("kernel32",SegmentScope.global());
        CloseHandle = linker.downcallHandle(
            kernel32.find("CloseHandle").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT,ADDRESS),
            CaptureLastError
        );
        GetLastError = CaptureLastError.layout().varHandle(MemoryLayout.PathElement.groupElement("GetLastError"));
    }

    private Kernel32() {}
}
