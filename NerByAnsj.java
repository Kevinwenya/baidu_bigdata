	package com.saiti.ner;

	import java.io.BufferedReader;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.UnsupportedEncodingException;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.ansj.domain.Result;
	import org.ansj.splitWord.analysis.NlpAnalysis;




	/*
	 * 该类主要实现书名号内的实体内容识别以及人名识别,地名识别和机构名识别
	 * 并将结果输出到BookMarkAndPersonName.txt中
	 * 
	 * */
	public class UpdateNerByAnsj {
		private static String[] book = new String[1];		//书影名等
		private static String[] person = new String[1];		//人名
		private static String[] place = new String[1];		//地名
		private static String[] organization = new String[1];	//	机构名
		private static String[] japan = new String[1];	//日语人名
		private static String[] yintransperson = new String[1];//音译人名
		private static String[] yintransplace = new String[1]; //音译地名
		private static String[] specialname = new String[1];  //其他专名
		private static String[] newword = new String[1];	//新词
		private static String[] namexin = new String[1];   //姓氏
		
		private static String[] subject = new String[1];	//主语短语
		private static String[] english = new String[1];	//英文专有
		private static String[] kuohao = new String[1];	//左括号前
		private static String[] maohao = new String[1];	//冒号前
		private static String[] zhonghao = new String[1]; //中文中括号内
		
		private static String patternBook = "(?<=《)[^》]+(?=》)";//”《([^》]+)》“;
		private static String patternPerson = "[\u4e00-\u9fa5]*(?=/nr)";
		private static String patternPlace = "[\u4e00-\u9fa5]*(?=/ns)";
		private static String patternOrg = "[\u4e00-\u9fa5]*(?=/nt)";
		private static String patternJanpan = "[\u4e00-\u9fa5]*(?=/nrj)";
		private static String patternYinTransPerson = "[\u4e00-\u9fa5]*(?=/nrf)";
		private static String patternYinTransPlace = "[\u4e00-\u9fa5]*(?=/nsf)";
		private static String patternSpecialName = "[\u4e00-\u9fa5]*(?=/nz)";
		private static String patternNewWord = "[\u4e00-\u9fa5]*(?=/nw)";
		private static String patternNameXin = "[\u4e00-\u9fa5]*(?=/nr1)";
		
		private static String patternSubject = "[\u4e00-\u9fa5]*(?=\u662f)";
		private static String patternEnglish = "[a-zA-Z]{1,10}";
		private static String patternKuohao = "[\u4e00-\u9fa5]*(?=/wkz)";
		private static String patternMaohao = "[\u4e00-\u9fa5]*(?=/wm)";
		private static String patternZhongHao = "(?<=【)*[\u4e00-\u9fa5]*(?=】)";
		//private static String patternKuohao = "[\u4e00-\u9fa5]*(?<=()";
		
		
		private static Pattern patternB = Pattern.compile(patternBook);
		private static Pattern patternP = Pattern.compile(patternPerson);
		private static Pattern patternPl = Pattern.compile(patternPlace);
		private static Pattern patternO = Pattern.compile(patternOrg);
		private static Pattern patternJ = Pattern.compile(patternJanpan);
		private static Pattern patternYTP = Pattern.compile(patternYinTransPerson);
		private static Pattern patternYTPl = Pattern.compile(patternYinTransPlace);
		private static Pattern patternSN = Pattern.compile(patternSpecialName);
		private static Pattern patternNW = Pattern.compile(patternNewWord);
		
		private static Pattern patternS = Pattern.compile(patternSubject);
		private static Pattern patternE = Pattern.compile(patternEnglish);
		private static Pattern patternK = Pattern.compile(patternKuohao);
		private static Pattern patternM = Pattern.compile(patternMaohao);
		private static Pattern patternZ = Pattern.compile(patternZhongHao);
		private static Pattern patternN = Pattern.compile(patternNameXin);
		//读取文本数据,根据正则表达式,提取出书名号内的数据
		public static String[] parseTextBook(String text){
			Matcher matcherB = patternB.matcher(text);
			if (matcherB.find()) {
				book[0] = matcherB.group(0);
				return book;
			}
			return null;
		}
		
		public static String[] parseTextPerson(String text){
			Matcher matcherP = patternP.matcher(text);
			if (matcherP.find()) {
				person[0] = matcherP.group(0);
				return person;
			}
			return null;
		}
		
		public static String[] parseTextOrg(String text){
			Matcher matcherO = patternO.matcher(text);
			if (matcherO.find()) {
				organization[0] = matcherO.group(0);
				return organization;
			}
			return null;
		}
		
		public static String[] parseTextPlace(String text){
			Matcher matcherPl = patternPl.matcher(text);
			if (matcherPl.find()) {
				place[0] = matcherPl.group(0);
				return place;
			}
			return null;
		}
		public static String[] parseTextSubject(String text){
			Matcher matcherS = patternS.matcher(text);
			if (matcherS.find()) {
				subject[0] = matcherS.group(0);
				return subject;
			}
			return null;
		}
		public static String[] parseTextEnglish(String text){
			Matcher matcherE = patternE.matcher(text);
			if (matcherE.find()) {
				english[0] = matcherE.group(0);
				return english;
			}
			return null;
		}
		
		public static String[] parseTextJanpan(String text){
			Matcher matcherJ = patternJ.matcher(text);
			if (matcherJ.find()) {
				japan[0] = matcherJ.group(0);
				return japan;
			}
			return null;
		}
		public static String[] parseTextYinTransPerson(String text){
			Matcher matcherYTP = patternYTP.matcher(text);
			if (matcherYTP.find()) {
				yintransperson[0] = matcherYTP.group(0);
				return yintransperson;
			}
			return null;
		}
		
		public static String[] parseTextYinTransPlace(String text){
			Matcher matcherYTPl = patternYTPl.matcher(text);
			if (matcherYTPl.find()) {
				yintransplace[0] = matcherYTPl.group(0);
				return yintransplace;
			}
			return null;
		}
		
		public static String[] parseTextSpecialName(String text){
			Matcher matcherSN = patternSN.matcher(text);
			if (matcherSN.find()) {
				specialname[0] = matcherSN.group(0);
				return specialname;
			}
			return null;
		}
		public static String[] parseTextNewWord(String text){
			Matcher matcherNW = patternNW.matcher(text);
			if (matcherNW.find()) {
				newword[0] = matcherNW.group(0);
				return newword;
			}
			return null;
		}
		
		
		public static String[] parseTextKuohao(String text){
			Matcher matcherK = patternK.matcher(text);
			if (matcherK.find()) {
				kuohao[0] = matcherK.group(0);
				return kuohao;
			}
			return null;
		}
		public static String[] parseTextMaohao(String text){
			Matcher matcherM = patternM.matcher(text);
			if (matcherM.find()) {
				maohao[0] = matcherM.group(0);
				return maohao;
			}
			return null;
		}
		public static String[] parseTextZhonghao(String text){
			Matcher matcherZ = patternZ.matcher(text);
			if (matcherZ.find()) {
				zhonghao[0] = matcherZ.group(0);
				return zhonghao;
			}
			return null;
		}
		public static String[] parseTextNameXin(String text){
			Matcher matcherNX = patternN.matcher(text);
			if (matcherNX.find()) {
				namexin[0] = matcherNX.group(0);
				return namexin;
			}
			return null;
		}
		
		
		public static void recognitionBookPerson(){
			
			try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
					new FileInputStream("/home/wsm/BaiDu/Test_Data/opendata_20w"), "utf-8"))){
			
				String line = " ";
				StringBuffer bookMarkPersonName = new StringBuffer();
				int count = 0;
				while ((line = bufferedReader.readLine())!= null) {
					line = line.trim();
					Result termList = NlpAnalysis.parse(line);
					String[] book = parseTextBook(line);
					String[] subject = parseTextSubject(line);
					String[] english = parseTextEnglish(line);
					
					String[] person = parseTextPerson(termList.toString());
					String[] place = parseTextPlace(termList.toString());
					String[] organization = parseTextOrg(termList.toString());
					String[] japan = parseTextJanpan(termList.toString());
					String[] yintransperson = parseTextYinTransPerson(termList.toString());
					String[] yintransplace = parseTextYinTransPlace(termList.toString());
					String[] specialname  = parseTextSpecialName(termList.toString());
					String[] newword = parseTextNewWord(termList.toString());
					
					if (book != null & person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null ) {
								bookMarkPersonName.append(book[0]+"\n");}
							else if(book != null && person != null ){
								if(line.indexOf(book[0]) <= line.indexOf(person[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(person[0]+"\n");
								}
							}else if (book != null && yintransperson !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(yintransperson[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(yintransperson[0]+"\n");
								}
							}else if (book != null && japan !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(japan[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(japan[0]+"\n");
								}
							}else if (book != null && place !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(place[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(place[0]+"\n");
								}
							}else if (book != null && yintransplace !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(yintransplace[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(yintransplace[0]+"\n");
								}
							}else if (book != null && organization !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(organization[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(organization[0]+"\n");
								}
							}else if (book != null && english !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(english[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(english[0]+"\n");
								}
							}else if (book != null && newword !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(newword[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(newword[0]+"\n");
								}
							}else if (book != null && specialname !=null) {
								if(line.indexOf(book[0]) <= line.indexOf(specialname[0])){
									bookMarkPersonName.append(book[0]+"\n");
								} else {
									bookMarkPersonName.append(specialname[0]+"\n");
								}
							}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				else if (book == null && person != null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null) {
								bookMarkPersonName.append(person[0]+"\n");}
								else if (person != null && yintransperson !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(yintransperson[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(yintransperson[0]+"\n");
									}
								}else if (person != null && japan !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(japan[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(japan[0]+"\n");
									}
								}else if (person != null && place !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(place[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(place[0]+"\n");
									}
								}else if (person != null && yintransplace !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(yintransplace[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(yintransplace[0]+"\n");
									}
								}else if (person != null && organization !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(organization[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(organization[0]+"\n");
									}
								}else if (person != null && english !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(english[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(english[0]+"\n");
									}
								}else if (person != null && newword !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (person != null && specialname !=null) {
									if(line.indexOf(person[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(person[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}
							
					/////////////////////////////////////////////////////////////								
							
				  else if (book == null && person == null && yintransperson != null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null) {
								bookMarkPersonName.append(yintransperson[0]+"\n");}
							     else if (yintransperson != null && japan !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(japan[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(japan[0]+"\n");
									}
								}else if (yintransperson != null && place !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(place[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(place[0]+"\n");
									}
								}else if (yintransperson != null && yintransplace !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(yintransplace[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(yintransplace[0]+"\n");
									}
								}else if (yintransperson != null && organization !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(organization[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(organization[0]+"\n");
									}
								}else if (yintransperson != null && english !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(english[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(english[0]+"\n");
									}
								}else if (yintransperson != null && newword !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (yintransperson != null && specialname !=null) {
									if(line.indexOf(yintransperson[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(yintransperson[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}							
							
				 else if (book == null && person == null && yintransperson == null && japan != null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null) {
								bookMarkPersonName.append(japan[0]+"\n");}
							 else if (japan != null && place !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(place[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(place[0]+"\n");
								}
							}else if (japan != null && yintransplace !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(yintransplace[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(yintransplace[0]+"\n");
								}
							}else if (japan != null && organization !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(organization[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(organization[0]+"\n");
								}
							}else if (japan != null && english !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(english[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(english[0]+"\n");
								}
							}else if (japan != null && newword !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(newword[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(newword[0]+"\n");
								}
							}else if (japan != null && specialname !=null) {
								if(line.indexOf(japan[0]) <= line.indexOf(specialname[0])){
									bookMarkPersonName.append(japan[0]+"\n");
								} else {
									bookMarkPersonName.append(specialname[0]+"\n");
								}
							}			
					
		////////////////////////////////////////////////////////////////////////////					
					else	if (book == null && person == null && yintransperson == null && japan == null && place != null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null){
								bookMarkPersonName.append(place[0]+"\n");
								}	else if (place != null && yintransplace !=null) {
									if(line.indexOf(place[0]) <= line.indexOf(yintransplace[0])){
										bookMarkPersonName.append(place[0]+"\n");
									} else {
										bookMarkPersonName.append(yintransplace[0]+"\n");
									}
								}else if (place != null && organization !=null) {
									if(line.indexOf(place[0]) <= line.indexOf(organization[0])){
										bookMarkPersonName.append(place[0]+"\n");
									} else {
										bookMarkPersonName.append(organization[0]+"\n");
									}
								}else if (place != null && english !=null) {
									if(line.indexOf(place[0]) <= line.indexOf(english[0])){
										bookMarkPersonName.append(place[0]+"\n");
									} else {
										bookMarkPersonName.append(english[0]+"\n");
									}
								}else if (place != null && newword !=null) {
									if(line.indexOf(place[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(place[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (place != null && specialname !=null) {
									if(line.indexOf(place[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(place[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}		
												
					else if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace != null  && organization == null && english == null && specialname == null && newword == null) {
								bookMarkPersonName.append(yintransplace[0]+"\n");
								}else if (yintransplace != null && organization !=null) {
									if(line.indexOf(yintransplace[0]) <= line.indexOf(organization[0])){
										bookMarkPersonName.append(yintransplace[0]+"\n");
									} else {
										bookMarkPersonName.append(organization[0]+"\n");
									}
								}else if (yintransplace != null && english !=null) {
									if(line.indexOf(yintransplace[0]) <= line.indexOf(english[0])){
										bookMarkPersonName.append(yintransplace[0]+"\n");
									} else {
										bookMarkPersonName.append(english[0]+"\n");
									}
								}else if (yintransplace != null && newword !=null) {
									if(line.indexOf(yintransplace[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(yintransplace[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (yintransplace != null && specialname !=null) {
									if(line.indexOf(yintransplace[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(yintransplace[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}		
							
						
							else if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization != null && english == null && specialname == null && newword == null) {
								bookMarkPersonName.append(organization[0]+"\n");
								}else if (organization != null && english !=null) {
									if(line.indexOf(organization[0]) <= line.indexOf(english[0])){
										bookMarkPersonName.append(organization[0]+"\n");
									} else {
										bookMarkPersonName.append(english[0]+"\n");
									}
								}else if (organization != null && newword !=null) {
									if(line.indexOf(organization[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(organization[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (organization != null && specialname !=null) {
									if(line.indexOf(organization[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(organization[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}			
							
							
						else	if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english != null && specialname == null && newword == null) {
								bookMarkPersonName.append(english[0]+"\n");
								}else if (english != null && newword !=null) {
									if(line.indexOf(english[0]) <= line.indexOf(newword[0])){
										bookMarkPersonName.append(english[0]+"\n");
									} else {
										bookMarkPersonName.append(newword[0]+"\n");
									}
								}else if (english != null && specialname !=null) {
									if(line.indexOf(english[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(english[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}						
							
						else	if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname != null && newword == null) {
								bookMarkPersonName.append(specialname[0]+"\n");
								}else if (newword != null && specialname !=null) {
									if(line.indexOf(newword[0]) <= line.indexOf(specialname[0])){
										bookMarkPersonName.append(newword[0]+"\n");
									} else {
										bookMarkPersonName.append(specialname[0]+"\n");
									}
								}
						else	if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword != null) {
								bookMarkPersonName.append(newword[0]+"\n");
							}		
							
							
							
						else	if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null && subject != null) {
								bookMarkPersonName.append(subject[0]+"\n");
							}
							
						else if (book == null && person == null && yintransperson == null && japan == null && place == null && yintransplace == null  && organization == null && english == null && specialname == null && newword == null && subject == null) {
								bookMarkPersonName.append("[]"+"\n");
							}
					//上述是修改规则约束的过程
					count++;
				SegTrain.writeFile("RuleBiLen2.txt", bookMarkPersonName);	
				System.out.println("目前处理行数: "+count);
				}

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
			recognitionBookPerson();
			System.out.println("Congratulations,BookMarkPersonNameDone,Complete");
		}
	}
