package br.dev.pedrolamarao.io.nio;

import java.io.IOException;
import java.nio.channels.AsynchronousChannelGroup;
import java.util.concurrent.TimeUnit;

final class WindowsAsynchronousChannelGroup extends AsynchronousChannelGroup
{
    WindowsAsynchronousChannelGroup (WindowsAsynchronousChannelProvider provider)
    {
        super(provider);
    }

    @Override
    public boolean isShutdown ()
    {
        return true;
    }

    @Override
    public boolean isTerminated ()
    {
        return true;
    }

    @Override
    public void shutdown ()
    {

    }

    @Override
    public void shutdownNow () throws IOException
    {

    }

    @Override
    public boolean awaitTermination (long timeout, TimeUnit unit) throws InterruptedException
    {
        return true;
    }
}
