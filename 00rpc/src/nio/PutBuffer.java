package nio;

import java.nio.ByteBuffer;

public class PutBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position()); //0 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //10 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println("--------------------------------");
        buffer.put((byte)1);
        System.out.println(buffer.position()); //1 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //9 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量

        System.out.println("--------------------------------");
        buffer.put("hello".getBytes());
        System.out.println(buffer.position()); //6 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //4 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量

        System.out.println("--------------------------------");
        buffer.put("1234".getBytes());
        System.out.println(buffer.position()); //10 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //0 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //false 是否还能操作

        System.out.println("--------------------------------");
        buffer.position(0); // 定位到新的位置
        System.out.println(buffer.position()); //0 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //10 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //true 是否还能操作

        System.out.println("-------------------------------");
        buffer.put((byte)2); // 会将原本存在的内容覆盖
        System.out.println(buffer.position()); //1 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //9 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //true 是否还能操作

    }
}
