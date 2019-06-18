package com.wcx.springboot.demo.source;

import org.junit.Test;

import java.nio.ByteBuffer;

public class IOEx {
    //byte buffer
    @Test
    public void test() {
        //bytebuffer设计成先写后读的场景
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.putChar('a');
        byteBuffer.flip(); //将limit设置成当前position,position设置成0，准备开始读
        System.out.println(byteBuffer.getChar());
        byteBuffer.rewind(); //回退，可以重新再读一次
        System.out.println(byteBuffer.getChar());
        byteBuffer.clear();//将limit设置成capacity,postion设置成0，准备新一轮写入

        //wrap 将byte[]包装成byte buffer
        byte[] reqBytes = new byte[4];
        ByteBuffer req = ByteBuffer.wrap(reqBytes);
        //put int的时候相当于操作了reqBytes数组，影响原数组
        req.putInt(ByteBuffer.wrap("ruok".getBytes()).getInt());
    }
}
