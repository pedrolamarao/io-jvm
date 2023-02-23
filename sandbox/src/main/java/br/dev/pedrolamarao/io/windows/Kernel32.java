package br.dev.pedrolamarao.io.windows;

import java.lang.foreign.FunctionDescriptor;
import java.lang.foreign.Linker;
import java.lang.foreign.SegmentScope;
import java.lang.foreign.SymbolLookup;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.JAVA_INT;

public final class Kernel32
{
    private Kernel32() {}

    static MethodHandle GetLastError;

    static
    {
        final var linker = Linker.nativeLinker();
        final var kernel32 = SymbolLookup.libraryLookup("kernel32",SegmentScope.global());
        GetLastError = linker.downcallHandle(
            kernel32.find("GetLastError").orElseThrow(),
            FunctionDescriptor.of(JAVA_INT)
        );
    }

    public static int GetLastError () throws Throwable
    {
        return (int) GetLastError.invokeExact();
    }
}
