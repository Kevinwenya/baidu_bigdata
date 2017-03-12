package com.saiti.ner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/*
 * Gson 是 Google 提供的用来在 Java 对象和 JSON 数据之间进行映射的 Java 类库。
 * 可以将一个 JSON 字符串转成一个 Java 对象，或者反过来,推荐使用.
 * 本类主要完成对象到Json格式数据的转换,主要使用gson的toJson方法
 * */

public class Object2Json {
	private static List<Obj> objlist = new ArrayList<Obj>();
	private static List<String> core_entity=new ArrayList<String>();
	
	public static void readObj(){
		Gson gson = new Gson();
		try(BufferedReader bufferedReaderCoreEntity = new BufferedReader(new InputStreamReader(
			new FileInputStream("/home/wsm/BaiDu/Train_Data/RuleBiLen2.txt"), "utf-8"));
			BufferedReader bufferedReaderContent = new BufferedReader(new InputStreamReader(
			new FileInputStream("/home/wsm/BaiDu/Test_Data/opendata_20w"), "utf-8"));){
			String coreEntityStr= " ";
			String contentStr = " ";
			Obj obj = null;
			StringBuffer  bufForJson = new StringBuffer();
			while ((coreEntityStr = bufferedReaderCoreEntity.readLine())!= null && 
					(contentStr = bufferedReaderContent.readLine())!= null ) {
				obj = new Obj();
				core_entity = new ArrayList<String>();
				core_entity.add(coreEntityStr.trim());
				obj.setCoreEntity(core_entity);
				obj.setContent(contentStr.trim());
				String str = gson.toJson(obj);
				bufForJson.append("["+str+"]"+"\n");
			}
			SegTrain.writeFile("OutPutJsonRuleBiLen2.txt",bufForJson);		//调用写入文件writeFile方法
			System.out.println("Congratulations,Json Done");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		readObj();
	}
	
}
