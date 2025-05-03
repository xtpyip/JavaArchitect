package nio;

import java.nio.ByteBuffer;

public class ReadBuffer {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put("123456".getBytes());
        System.out.println(buffer.position()); //6 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //4 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //true 是否还能操作

        System.out.println("------------------------------");
        // 切换至读模式
        buffer.flip();
        System.out.println(buffer.position()); //0 当前位置
        System.out.println(buffer.limit()); //6 最多能操作到哪里
        System.out.println(buffer.remaining()); //6 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //true 是否还能操作

        System.out.println("------------------------------");
        System.out.println(buffer.get());// 读一个字节
        byte[] bytes = new byte[2];
        buffer.get(bytes); // 读多个字节
        System.out.println(new String(bytes));

        System.out.println("------------------------------");
        buffer.rewind(); // 将position置0，重复读
        buffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println("------------------------------");
        buffer.clear(); // 切换回写模式
        System.out.println(buffer.position()); //0 当前位置
        System.out.println(buffer.limit()); //10 最多能操作到哪里
        System.out.println(buffer.remaining()); //10 还剩下多少操作
        System.out.println(buffer.capacity()); //10 当前缓冲区的容量
        System.out.println(buffer.hasRemaining()); //true 是否还能操作
        buffer.put("abc".getBytes());
        buffer.flip(); // 切换回读模式
        System.out.println("-------------------------------");
        System.out.println(new String(buffer.array()));
        System.out.println(buffer.position()); //0 当前位置
        buffer.get(bytes); // 读多个字节
        System.out.println(new String(bytes));


    }
}
