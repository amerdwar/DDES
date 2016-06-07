package dataStructure;
public class Event {
	
	
    public  String s;
    public String d;
    public  double ts;
    public int tag;
    
    
    public Event(String s,String d,double ts,int et){
    	
    	this.s=s;
    	this.d=d;
    	this.ts=ts;
    	this.tag=et;
    	
    }
    
    
    
	 public boolean lessThan(Event e) {
	       // Will throw an exception if y is not an Event
	        return (this.ts < e.ts);
	    }
}
