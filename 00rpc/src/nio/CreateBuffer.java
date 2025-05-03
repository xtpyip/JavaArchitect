package nio;

import java.nio.ByteBuffer;

public class CreateBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(6);
        for (int i = 0; i < 6; i++) {
            System.out.println(byteBuffer.get());
        }
//        System.out.println(byteBuffer.get()); // nextGetIndex
        ByteBuffer buffer = ByteBuffer.wrap("pyip".getBytes());
        for (int i = 0; i < 4; i++) {
            System.out.println(buffer.get());
        }
    }
}
