package test;

import akka.event.Logging;
import akka.event.LoggingAdapter;
import dataStructure.AbstractEntity;
import dataStructure.Event;
import dataStructure.OutChannel;

public class Source extends AbstractEntity{



    public Source(){
    	super();
    addOutChannel("sout");
    addInChannel("sourceIn");

	
}
	@Override
	public void body(Event e) {
		// TODO Auto-generated method stub
		if(e.tag==0){
			
			log.info("start from {}",e.s);
				c+=Math.random()+1;
			Event ee=new Event(this.path.toString(), null, c+lh,1);
			log.info("send event");
			addToOut(ee,"sout");
		
	}
		if(e.tag==3){
			log.info("i will give you another ");
			c+=Math.random()+1;
			Event ee=new Event(this.path.toString(), null, c+lh,1);
			addToOut(ee,"sout");
		}

}
}