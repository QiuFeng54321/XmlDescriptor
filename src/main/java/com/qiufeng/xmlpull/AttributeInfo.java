package com.qiufeng.xmlpull;
import org.xmlpull.v1.*;

public class AttributeInfo
{
	public String prefix;
	public String name;
	public String type;
	public String namespace;
	public AttributeInfo(XmlPullParser p,int ind){
		prefix=p.getAttributePrefix(ind);
		name=p.getAttributeName(ind);
		type=p.getAttributeType(ind);
		namespace=p.getAttributeNamespace(ind);
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getType()
	{
		return type;
	}

	public void setNamespace(String namespace)
	{
		this.namespace = namespace;
	}

	public String getNamespace()
	{
		return namespace;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return "<"+getType()+">"+getPrefix()+":"+getName();
	}
	public String toString(boolean showType){
		StringBuilder sb=new StringBuilder();
		if(showType){
			sb.append("<").append(getType()).append(">");
		}
		if(getPrefix()!=null&&(!(getPrefix().trim().isEmpty()))){
			sb.append(getPrefix()).append(":");
		}
		sb.append(getName());
		return sb.toString();
	}
	
}
