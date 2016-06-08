package test;

import dataStructure.AbstractEntity;
import dataStructure.Event;

public class Sink extends AbstractEntity{
public int num=0;


 
public double delay;

public Sink(){
	super();
	this.delay=1;
addInChannel("in1");	
addOutChannel("sinkOut");

}

	@Override
	public void body(Event e) {
		// TODO Auto-generated method stub
	if(e.tag==1){
		//process the event
		num++;
         
		  log.info("num is : {}",num);
		Event ee=new Event(path,path, c+lh, 2);	
		addToOut(ee);
		
	}
	if(e.tag==2){
		log.info("end of simulate the event: {}",e.s);
		if(num<3){
		Event ee=new Event(path,null, c+lh, 3);
		log.info("I need more");
		addToOut(ee,"sinkOut");
		}
		
	}
		
		
	}

}
