package com.wcx.springboot.demo.java8.InAction.ch3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 可以在之前和之后插入不同逻辑
 */
public class ExecuteAround {

	public static void main(String ...args) throws IOException{

        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");
		//正常处理流程
		String oneLine = processFile((BufferedReader b) -> b.readLine());
		System.out.println(oneLine);
		//一次读两行
		String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
		System.out.println(twoLines);

	}

    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
            return br.readLine();
        }
    }


	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))){
			return p.process(br);
		}

	}
	//行为话接口
	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}
}
