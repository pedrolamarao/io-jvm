package br.dev.pedrolamarao.io.windows;

import org.junit.jupiter.api.Test;

import static br.dev.pedrolamarao.io.windows.Kernel32.INVALID_HANDLE_VALUE;
import static java.lang.foreign.MemorySegment.NULL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class Kernel32Test
{
    @Test
    public void CloseHandle () throws Throwable
    {
        assertEquals( 0, Kernel32.CloseHandle(NULL) );
        assertEquals( 6, Kernel32.GetLastError() );
    }

    @Test
    public void CreateIoCompletionPort () throws Throwable
    {
        assertEquals( NULL, Kernel32.CreateIoCompletionPort(NULL,NULL,NULL,0) );
        assertEquals( 6, Kernel32.GetLastError() );

        final var port = Kernel32.CreateIoCompletionPort(INVALID_HANDLE_VALUE,NULL,NULL,0);
        assertNotEquals( NULL, port );
        assertEquals( 1, Kernel32.CloseHandle(port) );
    }
}
