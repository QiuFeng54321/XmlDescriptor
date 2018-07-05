package com.qiufeng.xmlpull;
import java.util.*;
import java.io.*;

public class Container implements Serializable
{
	public long serialVersionUID=1234567890L;
	public String name,text;
	public ArrayList<Container> subCon;
	public HashMap<AttributeInfo,String> attributes;
	public Container(String name,String text){
		super();
		this.name=name;
		this.text=text;
		subCon=new ArrayList<Container>();
		attributes=new HashMap<AttributeInfo,String>();
	}
	public Container(){
		this(null,null);
	}

	public void setSubContainer(ArrayList<Container> subCon)
	{
		this.subCon = subCon;
	}

	public ArrayList<Container> getSubContainer()
	{
		return subCon;
	}

	public void setAttributes(HashMap<AttributeInfo, String> attributes)
	{
		this.attributes = attributes;
	}

	public HashMap<AttributeInfo, String> getAttributes()
	{
		return attributes;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getText()
	{
		return text;
	}
}
