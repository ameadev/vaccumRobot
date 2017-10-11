package agent;

public class Move extends Effector {
	private String moveTo;

	public Move(String moveTo) {
		this.moveTo = moveTo;
	}

	@Override
	public void doAction() {
		
		 switch (this.moveTo) {
         case "right": Agent.x -=1; 
                       Agent.actionCount++;
                       break;
         case "left":  Agent.x +=1; 
                       Agent.actionCount++;
                       break;
         case "top":   Agent.y +=1; 
                       Agent.actionCount++;
                       break;  
         case "down":  Agent.y -=1;
                       Agent.actionCount++;
                       break; 
         default: 
                  break;
		 }

		//return (newPos);
	}
	
	

}
