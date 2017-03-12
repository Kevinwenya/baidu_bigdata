package com.saiti.ner;

import java.util.List;

/*
 *本来主要根据训练数据,创建对象
 * */

public class Obj {
	private String content;
	private List<String> core_entity;//严格说这不符合java的命名规范,应为coreEntity
	public Obj(){}
	
	public Obj(String content,List<String> core_entity){
		this.content = content;
		this.core_entity = core_entity;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return this.content;
	}
	public void setCoreEntity(List<String> core_entity){
		this.core_entity = core_entity;
	}
	public List<String> getCoreEntity(){
		return this.core_entity;
	}
}
