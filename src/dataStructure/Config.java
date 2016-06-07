package dataStructure;
import java.io.Serializable;
public class Config implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public String opType;
public String path;
public String name;
public Config(String opType,String path,String name){
	
	this.opType=opType;
	this.path=path;
	this.name=name;
	
}
}
