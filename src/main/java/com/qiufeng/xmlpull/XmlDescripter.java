package com.qiufeng.xmlpull;

import android.util.*;
import java.io.*;
import java.util.*;
import org.xmlpull.v1.*;

public class XmlDescripter//<T extends Object>
{
	public Container defaultList=new Container();

	public void setOnTextListener(OnTextListener onTextListener)
	{
		this.onTextListener = onTextListener;
	}

	public OnTextListener getOnTextListener()
	{
		return onTextListener;
	}

	public void setOnCommentListener(OnCommentListener onCommentListener){
		this.onCommentListener = onCommentListener;
	}
	public OnCommentListener getOnCommentListener(){
		return onCommentListener;
	}
	public void setOnEndTagListener(OnEndTagListener onEndTagListener){
		this.onEndTagListener = onEndTagListener;
	}
	public OnEndTagListener getOnEndTagListener(){
		return onEndTagListener;
	}
	public void setOnStartTagListener(OnStartTagListener onStartTagListener){
		this.onStartTagListener = onStartTagListener;
	}
	public OnStartTagListener getOnStartTagListener(){
		return onStartTagListener;
	}
	public void setOnEndDocumentListener(OnEndDocumentListener onEndDocumentListener){
		this.onEndDocumentListener = onEndDocumentListener;
	}
	public OnEndDocumentListener getOnEndDocumentListener(){
		return onEndDocumentListener;
	}
	public void setOnStartDocumentListener(OnStartDocumentListener onStartDocumentListener){
		this.onStartDocumentListener = onStartDocumentListener;
	}
	public OnStartDocumentListener getOnStartDocumentListener(){
		return onStartDocumentListener;
	}
	public Container getDefaultList(){
		return defaultList;
	}
	public static interface Listener{}
	public static interface OnStartDocumentListener extends Listener{
		public void onStartDocument(XmlPullParser parser)
	}
	private OnStartDocumentListener onStartDocumentListener;
	public static interface OnEndDocumentListener extends Listener{
		public void onEndDocument(XmlPullParser parser)
	}
	private OnEndDocumentListener onEndDocumentListener;
	public static interface OnStartTagListener extends Listener{
		public void onStartTag(XmlPullParser parser)
	}
	private OnStartTagListener onStartTagListener;
	public static interface OnEndTagListener extends Listener{
		public void onEndTag(XmlPullParser parser)
	}
	private OnEndTagListener onEndTagListener;
	public static interface OnCommentListener extends Listener{
		public void onComment(XmlPullParser parser)
	}
	private OnCommentListener onCommentListener;
	public static interface OnTextListener extends Listener{
		public void onText(XmlPullParser parser)
	}
	private OnTextListener onTextListener;
	public Container parseXml(InputStream xml)throws XmlPullParserException,IOException,Exception{
		defaultList=new Container("TREE",null);
		defaultList.setName("TREE");
		XmlPullParser pullParser = Xml.newPullParser();
		pullParser.setInput(xml, "utf-8");
		int event = pullParser.getEventType();
		while(event!=XmlPullParser.END_DOCUMENT){
			switch (event){
				case XmlPullParser.START_DOCUMENT:
					defaultList =new Container(); 
					if(onStartDocumentListener!=null)
						onStartDocumentListener.onStartDocument(pullParser);
					break;
				case XmlPullParser.START_TAG:
					if(onStartTagListener!=null)
						onStartTagListener.onStartTag(pullParser);
					break;
				case XmlPullParser.END_TAG:
					if(onEndTagListener!=null)
						onEndTagListener.onEndTag(pullParser);
					break;
				case XmlPullParser.COMMENT:
					if(onCommentListener!=null)
						onCommentListener.onComment(pullParser);
					break;
				case XmlPullParser.TEXT:
					if(onTextListener!=null)
						onTextListener.onText(pullParser);
					break;
			}
			event=pullParser.next();
		}
		if(onEndDocumentListener!=null)
			onEndDocumentListener.onEndDocument(pullParser);
		return defaultList;
	}
	public static class Searcher
	{

		public Searcher(Container scope)
		{
			this.scope = scope;
		}

		public Searcher(Matcher matcher)
		{
			this.matcher = matcher;
		}

		public Searcher(Matcher matcher, Container scope)
		{
			this.matcher = matcher;
			this.scope = scope;
		}

		public void setScope(Container scope)
		{
			this.scope = scope;
		}

		public Container getScope()
		{
			return scope;
		}

		public void setMatcher(Matcher matcher)
		{
			this.matcher = matcher;
		}

		public Matcher getMatcher()
		{
			return matcher;
		}
		public static interface Matcher{
			public boolean match(Container container)
		}
		public Matcher matcher;
		public Container scope;
		public static ArrayList<Container> search(Matcher m,Container c){
			return search(new ArrayList<Container>(),m,c);
		}
		private static ArrayList<Container> search(ArrayList<Container> l,Matcher m,Container c){
			if(m.match(c)){
				l.add(c);
			}
			for(int ind=0;ind<c.subCon.size();ind++){
				l=search(l,m,c.subCon.get(ind));
			}
			return l;
		}
		public ArrayList<Container> search(){
			if(matcher==null||scope==null)return null;
			return search(matcher,scope);
		}
	}
}
