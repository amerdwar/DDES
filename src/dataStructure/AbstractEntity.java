package dataStructure;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import akka.actor.ActorSelection;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;


public abstract class AbstractEntity extends UntypedActor implements entityInterface{
	public HashMap<String,InChannel> inc=new HashMap<String,InChannel>();
	public HashMap<String,OutChannel> outc=new HashMap<String,OutChannel>();
	public HashMap<String,InChannel> incTemp=new HashMap<String,InChannel>();
	InChannel myin=new InChannel("me");
	OutChannel myout=new OutChannel("me");
	
	
	
	
	public double lh=1;
    public  String path=null;
	public double c=0;
	public LoggingAdapter log = Logging.getLogger(getContext().system(), this);
	
	
	public AbstractEntity() {
		// TODO Auto-generated constructor stub
		
		path=getSelf().path().toString();
		myin.from=path;
		myout.to=path;
	
	
		
	}
 
	
	
     @Override
	  public void onReceive(Object message) throws Exception {
	  if(message  instanceof Event){
		  
		  Event e=(Event)message;
		  if (e.tag!=-1)
		  log.info("Received event  at time: {}",e.ts);
		  if(e.s.equals(path)){
			  
			  myin.insert(e);
		  }
		  else{
		  InChannel eventIn=inc.get(e.s);
		  eventIn.insert(e);
		  inc.replace(e.s,eventIn);
		  }
			int count=0;
			
			do{
			Event e4e;
			if(inc.size()>0){
			InChannel mininchannel=inc.get(minChannelQ());
		    count=mininchannel.size();
		    if(myin.cc<=mininchannel.cc&&myin.size()>0){
			e4e=myin.removeLastElement();
			c=e4e.ts;
			if(e4e.tag!=-1)
				body(e4e);
				
			    
	       	}else  if(count>0){
		   e4e = mininchannel.removeLastElement();//get the event
		    inc.replace(e4e.s, mininchannel);//update the entry in hash map
		    c=e4e.ts;
		    if(e4e.tag!=-1)
		    body(e4e);
		    
		     
		     }
		    }
	       	else{
	       		if(myin.size()>0){
	       			
	       			e4e=myin.removeLastElement();
	       			c=e4e.ts;
	       			if(e4e.tag!=-1)
				    body(e4e);

	       		}
	       			
	       	
	       	}
	       	
			}while (count>0);
			////send the contents of all q
			
			 Iterator<Entry<String, OutChannel>> it = outc.entrySet().iterator();
			 Event nullMessage=new Event( getSelf().path().toString(), null, c+lh, -1);
		     while(it.hasNext()){
		    	 OutChannel ou=it.next().getValue();
		    	 
		    	 if(ou.size()>0){
		    		for(int i=0;i<ou.size();i++){
		    			Event ee=ou.removeLastElement();
		    			log.info("s {} d {} ",ee.s,ee.d);
		    			 ActorSelection a = getContext().actorSelection(ee.d);
				    	
				    	 a.tell(ee, this.getSelf());
		    		} 
		    		 
		    	 }else{//send null message to empty buffers
		    		 //there is no need to send null message to me
		    		  	
				    	 ActorSelection a = getContext().actorSelection( ou.to);
				    	 nullMessage.d=ou.to;
				    	 a.tell(nullMessage, this.getSelf());
		    		 
		    	 }
		  
		    	 
		     }
		     //send the content of mine
		     flushMyOutChannel();
		
	
	  }else
		  log.info("recieve con");
	  if (message instanceof Config){
		  Config con=(Config) message;
		  if(con.opType.equals("in")){
			InChannel in=incTemp.get(con.name);
			if(in!=null){
			in.from=con.path;
			incTemp.replace(con.name, in);
			inc.put(in.from, in);
			}else {
				
				log.info("in channel name not exist  :abstactentity ");
			}
			  
		  }else if(con.opType.equals("out")){
			
			  //here we have to add outchannel to outc and update the channel inn outcby name
			 OutChannel ou=outc.get(con.name);
			 if(ou!=null){
			 ou.to=con.path;
			 outc.replace(con.name, ou);
			 outc.put(con.path, ou);
			 }else {
				 
				 log.info("out channel name not exist :abstactentity ");
			 }
			  
		  }
		  
	  }
	 
    	 
	 }
  public String minChannelQ(){
	 
	  InChannel minch;
	  String minId;
	  double minc;
	 
	  Iterator<Entry<String, InChannel>> it = inc.entrySet().iterator();
      minch = it.next().getValue();
      
     minId=minch.from;
     minc=minch.cc;
     
      while(it.hasNext()){
    	  minch=it.next().getValue();
    	  if(minch.cc<minc){
    		  minc=minch.cc;
    		  minId=minch.from;
    		  
    	  }
    	  
      }
     
  
    	
	  return minId;

  }
  public void addInChannel(String name){
	incTemp.put(name, new InChannel(name));  
	 
	 
  }
  
  public void addOutChannel(String name){
	  outc.put(name,new OutChannel(name));
	  
  }


	
	public void addToOut(Event e,String name){
		OutChannel ou=outc.get(name);
		e.d=ou.to;
		ou.insert(e);
		outc.replace(name, ou);
		
	}
	public void addToOut(Event e){
		e.d=path;
		myout.insert(e);
		
	}
	public void flushMyOutChannel(){
		for(int i=0;i<myout.size();i++){
			Event ee=myout.removeLastElement();
			log.info("s {} d {} ",ee.s,ee.d);
			 ActorSelection a = getContext().actorSelection(ee.d);
	    	
	    	 a.tell(ee, this.getSelf());
		} 
		
	}

}

	
	
	
	
	

