package br.dev.pedrolamarao.io.nio;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.spi.AsynchronousChannelProvider;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

public final class WindowsAsynchronousChannelProvider extends AsynchronousChannelProvider
{
    @Override
    public AsynchronousChannelGroup openAsynchronousChannelGroup (int nThreads, ThreadFactory threadFactory) throws IOException
    {
        return new WindowsAsynchronousChannelGroup(this);
    }

    @Override
    public AsynchronousChannelGroup openAsynchronousChannelGroup (ExecutorService executor, int initialSize) throws IOException
    {
        return new WindowsAsynchronousChannelGroup(this);
    }

    @Override
    public AsynchronousServerSocketChannel openAsynchronousServerSocketChannel (AsynchronousChannelGroup group) throws IOException
    {
        if (! (group instanceof WindowsAsynchronousChannelGroup windowsGroup))
            throw new IOException("unsupported group: " + group.getClass());
        return new WindowsAsynchronousServerSocketChannel(windowsGroup);
    }

    @Override
    public AsynchronousSocketChannel openAsynchronousSocketChannel (AsynchronousChannelGroup group) throws IOException
    {
        if (! (group instanceof WindowsAsynchronousChannelGroup windowsGroup))
            throw new IOException("unsupported group: " + group.getClass());
        return new WindowsAsynchronousSocketChannel(windowsGroup);
    }
}
