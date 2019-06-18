package com.wcx.springboot.demo.java.file;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Paths;
import java.util.Scanner;

public class ByteBufferExample {
    public static void main(String[] args) throws IOException {
        //bytebuffer设计成先写后读的场景
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.putChar('a');
        byteBuffer.flip();  //将limit设置成当前position,position设置成0，准备开始读
        System.out.println(byteBuffer.getChar());
        byteBuffer.rewind(); //回退，可以重新再读一次
        System.out.println(byteBuffer.getChar());
        byteBuffer.clear();//将limit设置成capacity,postion设置成0，准备新一轮写入

        //文件锁
        FileChannel channel = FileChannel.open(Paths.get("path"));
        try (FileLock lock = channel.lock()) {

        }

    }

    /**
     * InetAddress
     * @throws UnknownHostException
     */
    @Test
    public void testInetAddress() throws UnknownHostException {
        //InetAddress
        //获取该域名下所有ip
        InetAddress[] addresses = InetAddress.getAllByName("www.google.com");
        for (InetAddress address : addresses) {
            System.out.println(address);
            //获取ip地址
            byte[] address1 = address.getAddress();
        }
    }
    @Test
    public void testUri() throws IOException {
        //URL和URI用处：从远程站点获取资源
        //URL是URI的一种，URI (uniform resource
        //locators) and URIs (uniform resource identifers).
        //URI还包括 mailto:wang@123.com等其他资源
        URL url = new URL("http://www.google.com");
        //URL可以获取该站点响应，URI只是单纯的解析
        //URI的作用就是解析路径，获取具体参数          getScheme getSchemeSpecificPart getAuthority getUserInfo getHost getPort getPath getQuery getFragment
        InputStream inStream = url.openStream();
        Scanner in = new Scanner(inStream, "UTF-8");
        String next = in.next();
        System.out.println(next);

    }
}
