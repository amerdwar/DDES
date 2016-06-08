package test;
import dataStructure.Config;
import dataStructure.Event;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final ActorSystem system = ActorSystem.create("MySystem");
		 final ActorRef source = system.actorOf( Props.create(Source.class), "source");
		 final ActorRef sink = system.actorOf( Props.create(Sink.class), "sink");
      	source.tell(new Config("out", sink.path().toString(), "sout"), ActorRef.noSender());
      	source.tell(new Config("in", sink.path().toString(), "sourceIn"),ActorRef.noSender());
      	sink.tell(new Config("in", source.path().toString(), "in1"),ActorRef.noSender());
      	sink.tell(new Config("out", source.path().toString(), "sinkOut"),ActorRef.noSender());
   
      	 Event ee=new Event(source.path().toString(),source.path().toString(), 0, 0);
     	
       	source.tell(ee, ActorRef.noSender());
      	
	}

}
