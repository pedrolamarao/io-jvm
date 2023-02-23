import br.dev.pedrolamarao.io.nio.WindowsAsynchronousChannelProvider;

import java.nio.channels.spi.AsynchronousChannelProvider;

module br.dev.pedrolamarao.jvm.io
{
    exports br.dev.pedrolamarao.io.nio;
    provides AsynchronousChannelProvider with WindowsAsynchronousChannelProvider;
}
