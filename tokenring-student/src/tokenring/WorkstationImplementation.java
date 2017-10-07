package tokenring;

import java.util.Queue;
import java.util.LinkedList;

public class WorkstationImplementation extends Workstation {
	
	protected Queue<Message> queue;
	protected NetworkInterface network;

	public WorkstationImplementation(NetworkInterface nic) {
           queue = new LinkedList<Message>();
           network = nic;
	}

	public NetworkInterface getNIC() {
            return network;
	}
	
	@Override
	public int compareTo(Workstation o) {
           if(this.id == o.id){
        	   return 0;
           }
           else if(this.id < o.id){
        	   return -1;
           }
           else{
        	   return 1;
           }
	}
	
	@Override
	public boolean equals(Object obj) {
		   Workstation work = (Workstation)(obj);
           if(compareTo(work) == 0){
        	   return true;
           }
           else{
        	   return false;
           }
	}

	public void sendMessage(Message m) {
           queue.add(m);
	}

	@Override
	public void tick() {
           if(network.hasFrame()){
        	   
        	   Frame frame = network.read();
        	   
        		   
        	   if(frame.isTokenFrame() && queue.isEmpty()){
        		   network.write(frame);
        	   }
        	   if(frame.isTokenFrame() && !queue.isEmpty()){
        		   DataFrame dataFrame = new DataFrame(queue.remove());
        		   network.write(dataFrame);
        		   this.incMsgSent();
        	   }
           
        	   else if(frame.isDataFrame()){
        		   
        		  DataFrame dataFrame = (DataFrame) frame;
        		  Message message = dataFrame.getMessage();
        		  if(this.id == message.getReceiver()){
        			  System.out.println("message " + message.toString() + "; recieved by " + message.getReceiver() + "sent by " + message.getSender());
        			  this.incMsgRcvd();
        			  dataFrame.setReceived(true);
        			  network.write(dataFrame);
        		  }
        		  else{
        			  network.write(dataFrame);
        		  }
        		  if(this.id == message.getSender()){
        			  
        			  if(dataFrame.wasReceived()){
        				  System.out.println("message " + message.toString() + "acknowledged by sender " + message.getSender() + "from destination " + message.getReceiver());     				  
        				  network.write(TokenFrame.TOKEN);
        				  this.incMsgDelivered();
        			  }
        			  
        			  else{
        				  System.out.println("message " + message.toString() + "dropped; destination not reachable ");
        				  network.write(TokenFrame.TOKEN);
        			  }
        		  }
        	   }
           }
	}

}
