package com.wcx.springboot.demo.midware.json.fastjson.readfromfile;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.JSONWriter;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Example {
    public static void main(String[] args) {
        try {
//            test1();
//            test2();
//            test3();
            test4();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    /**
     * 超大JSON数组序列化
     * @throws IOException
     */
    public static void test1() throws IOException {
        JSONWriter writer = new JSONWriter(new FileWriter("conf/hugeArray.json"));
        writer.startArray();
        for (int i = 0; i < 10; ++i) {
            writer.writeValue(new VO(i));
        }
        writer.endArray();
        writer.close();
    }

    /**
     * 超大JSON对象序列化
     */
    public static void test2() throws IOException {

        JSONWriter writer = new JSONWriter(new FileWriter("conf/hugeObject.json"));
        writer.startObject();
        for (int i = 0; i < 10; ++i) {
            writer.writeKey("x" + i);
            writer.writeValue(new VO(i));
        }
        writer.endObject();
        writer.close();
    }

    /**
     * 超大JSON数组反序列化
     */
    private static void test3() throws FileNotFoundException {
        // 读入上面输出的文件
        JSONReader reader = new JSONReader(new FileReader("conf/hugeArray.json"));
        reader.startArray();
        while (reader.hasNext()) {
            VO vo = reader.readObject(VO.class);
            System.out.println(vo);
        }
        reader.endArray();
        reader.close();
    }

    /**
     * 超大JSON对象反序列化
     */
    private static void test4() throws FileNotFoundException {
        // 读入上面输出的文件
        JSONReader reader = new JSONReader(new FileReader("conf/hugeObject.json"));
        reader.startObject();
        while (reader.hasNext()) {
            String key = reader.readString();
            VO vo = reader.readObject(VO.class);
            System.out.println(key + "：" + vo);
        }
        reader.endObject();
        reader.close();
    }

    /**
     * 读取数据对象object
     */
    public static void test5() throws IOException {
        InputStream inputStream = new FileInputStream("config/billtype.conf.json");
        String text = IOUtils.toString(inputStream,"utf8");
//        Root root = JSON.parseObject(text,Root.class);

    }
}
