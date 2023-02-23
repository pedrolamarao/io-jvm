package br.dev.pedrolamarao.io.windows;

import org.junit.jupiter.api.Test;

import static java.lang.foreign.MemorySegment.NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Kernel32Test
{
    @Test
    public void CloseHandle () throws Throwable
    {
        assertEquals( 0, Kernel32.CloseHandle(NULL) );
        assertEquals( 6, Kernel32.GetLastError() );
    }
}
