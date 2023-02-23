package br.dev.pedrolamarao.io.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

final class WindowsAsynchronousServerSocketChannel extends AsynchronousServerSocketChannel
{
    private final WindowsAsynchronousChannelGroup group;

    WindowsAsynchronousServerSocketChannel (WindowsAsynchronousChannelGroup group)
    {
        super(group.provider());
        this.group = group;
    }

    @Override
    public AsynchronousServerSocketChannel bind (SocketAddress local, int backlog) throws IOException
    {
        throw new IOException();
    }

    @Override
    public <T> AsynchronousServerSocketChannel setOption (SocketOption<T> name, T value) throws IOException
    {
        throw new IOException();
    }

    @Override
    public <T> T getOption (SocketOption<T> name) throws IOException
    {
        throw new IOException();
    }

    @Override
    public Set<SocketOption<?>> supportedOptions ()
    {
        return Set.of();
    }

    @Override
    public <A> void accept (A attachment, CompletionHandler<AsynchronousSocketChannel, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
    }

    @Override
    public Future<AsynchronousSocketChannel> accept ()
    {
        return CompletableFuture.failedFuture( new IOException() );
    }

    @Override
    public SocketAddress getLocalAddress () throws IOException
    {
        throw new IOException();
    }

    @Override
    public boolean isOpen ()
    {
        return false;
    }

    @Override
    public void close () throws IOException
    {
        throw new IOException();
    }
}
