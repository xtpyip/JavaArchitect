package bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(9999);
        System.out.println("server started");
        while (true) {
            Socket client = socket.accept();
            System.out.println("client connected");
            executorService.execute(new Runnable() {
                public void run() {
                    handle(client);
                }
            });
        }

    }
    public static void handle(Socket client) {
        try {
            System.out.println("thread id: "+Thread.currentThread().getId()
            +" thread name: "+Thread.currentThread().getName());
            InputStream is = client.getInputStream();
            byte[] b = new byte[1024];
            int read = is.read(b);
            System.out.println("client : "+ new String(b, 0, read));
            OutputStream os = client.getOutputStream();
            os.write("没钱".getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
