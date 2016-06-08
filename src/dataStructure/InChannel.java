package dataStructure;


import java.util.PriorityQueue;

public class InChannel {

	
	public String from;
	public double  cc;
	public String name;
    private PriorityQueue<Event> chQ;
  public InChannel(String name){
	  this.name=name;
	  cc=0;
	  chQ=new PriorityQueue<Event>();
  } 
   
    
	
	public PriorityQueue<Event> getChQ() {
		return chQ;
	}
	public void setChQ(PriorityQueue<Event> chQ) {
		this.chQ = chQ;
	}

	
    
	public void insert(Event e){
	
		this.cc=e.ts;
		chQ.add(e);
		
	}
	
	
	public Event removeLastElement(){
		if (size()>0){
		Event e =chQ.poll();
		this.cc=e.ts;
		return e;
		}
		else
		return null;
	}

	public Event firstElement(){
		return chQ.peek();
		
	}
	public int size(){
		return chQ.size();
		
	}

}
