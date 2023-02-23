package br.dev.pedrolamarao.io.windows;

import org.junit.jupiter.api.Test;

import java.lang.foreign.Arena;
import java.lang.foreign.MemorySegment;
import java.lang.foreign.ValueLayout;

import static br.dev.pedrolamarao.io.windows.Kernel32.INVALID_HANDLE_VALUE;
import static java.lang.foreign.MemorySegment.NULL;
import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;
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

    @Test
    public void PostGetCompletionPort () throws Throwable
    {
        final var port = Kernel32.CreateIoCompletionPort(INVALID_HANDLE_VALUE,NULL,NULL,0);
        assertNotEquals( NULL, port );

        final var bytes = 1;
        final var key = MemorySegment.ofAddress(2);
        final var operation = MemorySegment.ofAddress(3);
        assertEquals( 1, Kernel32.PostQueuedCompletionStatus(port,bytes,key,operation) );

        try (var arena = Arena.openConfined())
        {
            final var bytesRef = arena.allocate(JAVA_INT);
            final var keyRef = arena.allocate(ADDRESS);
            final var operationRef = arena.allocate(ADDRESS);
            assertEquals( 1, Kernel32.GetQueuedCompletionStatus(port,bytesRef,keyRef,operationRef,0) );

            assertEquals( bytes, bytesRef.get(JAVA_INT,0) );
            assertEquals( key, keyRef.get(ADDRESS,0) );
            assertEquals( operation, operationRef.get(ADDRESS,0) );
        }
        finally
        {
            assertEquals( 1, Kernel32.CloseHandle(port) );
        }
    }
}
