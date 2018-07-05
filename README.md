#  **XmlDescriptor** 
 **XmlDescriptor**  provides classes to descript  **XML** files.

##  **USAGE**
 **XmlDescriptor** 

Create an instance:

 ```
XmlParse descriptor=new XmlParse()
```  
Descript XML file:  
```
descriptor.descriptXml(new FileInputStream("xxx.xml")
```  
>  __RETURN VALUE__  


returns class  **Container**  
  
>Class  **__Container__**  

   The class Container contains the information of a node in Xml file and the subnodes contained in this node.  
>>EXAMPLE  

The return value of method `XmlParse.descriptXml` returns class Container.  
>>API of  **Container**  

SubContainer:
`
    getSubContainer():ArrayList<Container>  
    setSubContainer(ArrayList<Container> subCon):void
`  
Attributes  
`
getAttributes():HashMap<AttributeInfo,String>
`  
*AttributeInfo*  
Contains attribute's name.  
Call `toString()` to get full name.  
Name and text of _Container_  
`
    getName():String  
`  
`
    getText():String  
`  
`
    setName(String name):void  
`  
`
  setText(String text):void
`  
>>`xmlparse.tree`  

`xmlparse.tree(StringBuilder sb,Container c,int space):StringBuilder`
generates a tree in String format  
__Example__  
Xml file:  
    
    <tree>  
        <node name="node1">hi</node>  
        <node name="node2">  
            <hi>test</hi>  
        </node>  
    </tree>  
java:  
```
xmlparse.tree(new StringBuilder(),/*Container get from method descriptXml*/,2).toString();
```  
output:  
    >Container tree  
    >text=NONE  
    -->Container node  
    -->text=hi  
    -->attribute name=node1  
    -->Container node
    -->text=NONE
    -->attribute name=node2
    ---->Container hi
    ---->text=test