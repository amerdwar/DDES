package dataStructure;

import java.util.Vector;

public class OutChannel {
	public String to;

	public String name;
	  private Vector<Event> chQ;
	  
	public OutChannel(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		  chQ=new Vector<Event>();
	}
	public OutChannel(String name,String cid){
		
		this.name=name;
		this.to=cid;
		chQ=new Vector<Event>();
	}
	public Vector<Event> getChQ() {
		return chQ;
	}
	public void setChQ(Vector<Event> chQ) {
		this.chQ = chQ;
	}

	
    
	public void insert(Event e){
	
	
		chQ.add(e);
		
	}
	
	public Event removeElement(Event e){
		for(int i=0;i<size();i++){
			if(chQ.elementAt(i).equals(e)){
				return chQ.remove(i);
				
			}
			
		}
		return null;
		
	}
	public Event removeLastElement(){
		if (size()>0){
		Event e =chQ.remove(chQ.size()-1);
	
		return e;
		}
		else
		return null;
	}

	public Event firstElement(){
		return chQ.elementAt(0);
		
	}
	public int size(){
		return chQ.size();
		
	}

}
