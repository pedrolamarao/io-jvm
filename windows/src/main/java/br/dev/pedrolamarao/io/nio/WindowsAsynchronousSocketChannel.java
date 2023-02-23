package br.dev.pedrolamarao.io.nio;

import java.io.IOException;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

final class WindowsAsynchronousSocketChannel extends AsynchronousSocketChannel
{
    private final WindowsAsynchronousChannelGroup group;

    WindowsAsynchronousSocketChannel (WindowsAsynchronousChannelGroup group)
    {
        super(group.provider());
        this.group = group;
    }

    @Override
    public AsynchronousSocketChannel bind (SocketAddress local) throws IOException
    {
        throw new IOException();
    }

    @Override
    public <T> AsynchronousSocketChannel setOption (SocketOption<T> name, T value) throws IOException
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
    public AsynchronousSocketChannel shutdownInput () throws IOException
    {
        throw new IOException();
    }

    @Override
    public AsynchronousSocketChannel shutdownOutput () throws IOException
    {
        throw new IOException();
    }

    @Override
    public SocketAddress getRemoteAddress () throws IOException
    {
        throw new IOException();
    }

    @Override
    public <A> void connect (SocketAddress remote, A attachment, CompletionHandler<Void, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
    }

    @Override
    public Future<Void> connect (SocketAddress remote)
    {
        return CompletableFuture.failedFuture( new IOException() );
    }

    @Override
    public <A> void read (ByteBuffer dst, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
    }

    @Override
    public Future<Integer> read (ByteBuffer dst)
    {
        return CompletableFuture.failedFuture( new IOException() );
    }

    @Override
    public <A> void read (ByteBuffer[] dsts, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
    }

    @Override
    public <A> void write (ByteBuffer src, long timeout, TimeUnit unit, A attachment, CompletionHandler<Integer, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
    }

    @Override
    public Future<Integer> write (ByteBuffer src)
    {
        return CompletableFuture.failedFuture( new IOException() );
    }

    @Override
    public <A> void write (ByteBuffer[] srcs, int offset, int length, long timeout, TimeUnit unit, A attachment, CompletionHandler<Long, ? super A> handler)
    {
        handler.failed( new IOException(), attachment );
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
