package dataStructure;

import java.util.PriorityQueue;

public class OutChannel {
	public String to;
    public String name;
	public  PriorityQueue<Event> chQ=new PriorityQueue<Event>();
	  
	public OutChannel(String name) {
		// TODO Auto-generated constructor stub
		this.name=name;
		
		  
	}
	public OutChannel(String name,String cid){
		
		this.name=name;
		this.to=cid;
		
	
	}
	public PriorityQueue<Event> getChQ() {
		return chQ;
	}
	public void setChQ(PriorityQueue<Event> chQ) {
		this.chQ = chQ;
	}

	
    
	public void insert(Event e){
		chQ.add(e);
	}
	

	public Event removeLastElement(){
		
		Event e =chQ.poll();
	
		return e;
		
	}

	public Event firstElement(){
		return chQ.peek();
		
	}
	public int size(){
		return chQ.size();
		
	}

}
