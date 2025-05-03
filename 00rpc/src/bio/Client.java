package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            Thread.sleep(3000);
            Socket socket = new Socket("127.0.0.1", 9999);
            socket.getOutputStream().write("还钱".getBytes());

            InputStream is = socket.getInputStream();
            byte[] b = new byte[1024];
            int len = is.read(b);
            String data = new String(b, 0, len);
            System.out.println("127.0.0.1:9999 response : "+data);
            is.close();
        }
    }
}
