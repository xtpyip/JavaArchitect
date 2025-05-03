package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        serverSocketChannel.configureBlocking(false);
        System.out.println("Server started");
        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel == null) {
                System.out.println("No client");
                Thread.sleep(3000);
                continue;
            }
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int len = socketChannel.read(buffer);
            /*
            len为正数：表示本次读到的有效字节数
            0： 表示本次没有读到数据
            -1： 表示讲到末尾
             */
            System.out.println("client received: " + new String(buffer.array(),0 ,
                    len, StandardCharsets.UTF_8));
            socketChannel.write(ByteBuffer.wrap("no money".getBytes(StandardCharsets.UTF_8)));
            socketChannel.close();
        }
    }
}
