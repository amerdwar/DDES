package dataStructure;


import java.util.Vector;

public class InChannel {

	
	public String from;
	public double  cc;
	public String name;
    private Vector<Event> chQ;
  public InChannel(String name){
	  this.name=name;
	  cc=0;
	  chQ=new Vector<Event>();
  } 
   
    
	
	public Vector<Event> getChQ() {
		return chQ;
	}
	public void setChQ(Vector<Event> chQ) {
		this.chQ = chQ;
	}

	
    
	public void insert(Event e){
	
		this.cc=e.ts;
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
		this.cc=e.ts;
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
