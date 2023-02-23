package br.dev.pedrolamarao.io.windows;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Kernel32Test
{
    @Test
    public void GetLastError () throws Throwable
    {
        final var error = Kernel32.GetLastError();
        assertEquals(0,error);
    }
}
