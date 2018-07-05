package com.qiufeng.xmlpull;

import android.graphics.*;
import android.widget.*;
import com.qiufeng.xmlpull.*;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.*;
import android.util.*;
import android.content.*;

public class XmlParse
{
	XmlDescripter xd;
	int current=0;
	public Container descriptXml(InputStream is){

		xd=new XmlDescripter();
		xd.setOnStartDocumentListener(new XmlDescripter.OnStartDocumentListener(){
			public void onStartDocument(XmlPullParser p){
				xd.getDefaultList().setName(p.getName());
				for(int i=0;i<p.getAttributeCount();i++){
					xd.getDefaultList().attributes.put(new AttributeInfo(p,i),p.getAttributeValue(i));
					Log.i("XmlParse","attribute "+(i+1)+":"+p.getAttributeName(i)+"="+p.getAttributeValue(i));
				}
			}
		});
		xd.setOnStartTagListener(new XmlDescripter.OnStartTagListener(){
				public void onStartTag(XmlPullParser p){
					Log.i("XmlParse","tag started:"+p.getName());
					Container container=new Container(p.getName(),null);
					Log.w("XmlParse","Tag:"+container.getName());
					for(int i=0;i<p.getAttributeCount();i++){
						container.attributes.put(new AttributeInfo(p,i),p.getAttributeValue(i));
						Log.i("XmlParse","attribute "+(i+1)+":"+p.getAttributeName(i)+"="+p.getAttributeValue(i));
					}
					Container cur=xd.getDefaultList();
					
					for(int ind=0;ind<current;ind++){
						cur=cur.subCon.get(cur.subCon.size()-1);
					}
					cur.getSubContainer().add(container);
					current++;
					Log.i("XmlParse","Container "+container.getName()+" added.");
					Log.i("XmlParse","StartTag finished.");
				}
			});
		xd.setOnTextListener(new XmlDescripter.OnTextListener(){
			public void onText(XmlPullParser p){
				Container cur=xd.getDefaultList();

				for(int ind=0;ind<current;ind++){
					cur=cur.subCon.get(cur.subCon.size()-1);
				}
				cur.setText(p.getText());
				Log.i("XmlParse",cur.getName()+"'text has been set to "+cur.getText());
			}
		});
		xd.setOnEndTagListener(new XmlDescripter.OnEndTagListener(){
				public void onEndTag(XmlPullParser p){
					Log.i("XmlParse","tag ended:"+p.getName());
					/*Container cur=xd.getDefaultList();
					for(int ind=0;ind<current.size()-1;ind++){
						cur=cur.subCon.get(cur.subCon.size()-1);
					}
					cur.subCon.remove(cur.subCon.size()-1);*/
					current--;
					Log.i("XmlParse","removed last tag.Position minus 1.");
				}
			});
		xd.setOnEndDocumentListener(new XmlDescripter.OnEndDocumentListener(){
				public void onEndDocument(XmlPullParser p){
					Log.i("XmlParse","document ended.");
					//tree(xd.getDefaultList(),0);
				}
			});
		try{
			xd.parseXml(is);
		}
		catch (Exception e)
		{
			Log.e("XmlException",e.getMessage()+"\n"+StackTraceHelper.meanify(e.getStackTrace()),e);
		}
		return xd.defaultList;
	}
	//used for debug.
	/*
	@ForDebug
	public void custom(Context cx,CharSequence str,int col){
		TextView tv=new TextView(cx);
		tv.setText(str);
		tv.setTextColor(col);
		tv.setTextSize(17);
		tv.setLineSpacing(2,1.3f);
		//tv.setLetterSpacing(0.01f);
		tv.setTextIsSelectable(true);
		tv.setTypeface(Typeface.MONOSPACE);
		MainActivity.ml.addView(tv);
	}
	@ForDebug
	public void log(CharSequence s){
		custom(s,Color.WHITE);
	}
	@ForDebug
	public void warn(CharSequence s){
		custom(s,Color.YELLOW);
	}
	@ForDebug
	public void error(CharSequence s){
		custom(s,Color.RED);
	}*/
	@Deprecated
	public String getText(XmlPullParser p){
		try{
			return p.nextText();
		}catch(Exception e){
			return null;
		}
	}
	public StringBuilder tree(StringBuilder sb,Container c,int space){
		String s="";
		if(sb==null)
			sb=new StringBuilder();
		for(int i=0;i<space;i++){
			s+="--";
		}
		s+=">";
		sb.append(s+"Container "+c.getName());
		sb.append('\n');
		sb.append(s+"text="+(c.getText()==null||c.getText().trim().isEmpty()?"NONE":c.getText()));
		sb.append('\n');
		for(Map.Entry me : c.attributes.entrySet()){
			sb.append(s+"attribute "+((AttributeInfo)(me.getKey())).toString(false)+"="+me.getValue());
			sb.append('\n');
		}
		for(int i=0;i<c.subCon.size();i++){
			sb=tree(sb,c.subCon.get(i),space+2);
		}
		return sb;
	}
}
