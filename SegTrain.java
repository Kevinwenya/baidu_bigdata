package com.saiti.ner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 该文件主要用来将文本分件进行分割,得到content.txt纯文本内容文件,和json.txt文件
 * */
public class SegTrain {
	private static String[] result = new String[2];
	private static String patternStr = "(.*).*(\\[\\{.*\\]$)";
	private static Pattern pattern = Pattern.compile(patternStr);
	private static String path = "/home/wsm/BaiDu/Train_Data/";
	/*
	 * 解析文本数据,根据正则表达式,将文件分为两组
	 * */
	public static String[] parseStr(String str){
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			result[0] = matcher.group(1);
			result[1] = matcher.group(2);
			return result;
		}
		return null;
	}
	/*
	 * 流输入,由磁盘读取文件,并调用解析方法和写入文件的方法,将得到的两个文件存入到对应目录下
	 * */
	public static void readFile(){
		BufferedReader bufferedReader = null;
		StringBuffer first = new StringBuffer();
		StringBuffer second = new StringBuffer();
		File file = new File("/home/wsm/BaiDu/Train_Data/opendata.txt");
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String line = "";
			while ((line = bufferedReader.readLine())!= null) {
				String[] res = parseStr(line); //调用parseStr方法
				if (res != null) {
					first.append(res[0]+"\n");
					second.append(res[1]+"\n");
				}
			}
			writeFile("content.txt",first);		//调用写入文件writeFile方法
			writeFile("json.txt",second);
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
	/*
	 * 将文件写入到磁盘路径path下.
	 * */
	public static void writeFile(String fileName,StringBuffer buf){
		FileWriter fileWriter = null;
		try {
			File file = new File(path,fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			fileWriter = new FileWriter(file);
			fileWriter.append(buf);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			if (fileWriter != null) {
				try {
					fileWriter.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		readFile();
		System.out.println("Congratulations,Done,Complete");
	}
}
