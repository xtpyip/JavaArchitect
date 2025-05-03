package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",9999));
        socketChannel.write(ByteBuffer.wrap("boss, give me money".getBytes(StandardCharsets.UTF_8)));

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int len = socketChannel.read(buffer);
        System.out.println("client received: " +new String(buffer.array(),0,len, StandardCharsets.UTF_8));
        socketChannel.close();
    }
}
