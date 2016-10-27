 package cc.moredo.oa.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataToStl 
{
	/**
	 * 功能：函数主方法
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String readerFilePath = "d:/test.data";
		String writerFilePath = "d:/test.stl";
		
		dataToStl(readerFilePath, writerFilePath);
	}
	
	/**
	 * 功能：根据传过来的源文件（readerFilePath）生成对应的目标文件（writerFilePath）
	 * @param readerFilePath - 源文件的绝对位置
	 * @param writerFilePath - 目标文件的绝对位置
	 */
	public static void dataToStl(String readerFilePath, String writerFilePath)
	{
		FileWriter writer = null;
		BufferedReader reader = null;
		try 
		{
			String line = "";
			int i = 1;
			
			//用BufferedReader逐行读取文本文件中的内容
			reader = new BufferedReader(new FileReader(readerFilePath));
			writer = new FileWriter(writerFilePath);
			writer.write("solid OpenSCAD_Model"+"\n");
			while((line=reader.readLine())!=null)
			{
				if(i == 4)
				{
					writer.write("	  vertex "+line+"\n");
					writer.write("	endloop"+"\n");
					writer.write("	endfacet"+"\n");
					i = 1;
				}
				else
				{
					if(i == 1)
					{
						writer.write("  facet normal "+line+"\n");
						writer.write("	outer loop"+"\n");
					}
					if(i == 2 || i == 3)
					{
						writer.write("	  vertex "+line+"\n");
					}
					i++;
				}
			}
			writer.write("endsolid OpenSCAD_Model"+"\n");
			writer.flush();
			reader.close();
			writer.close();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			
		}
	}
}
