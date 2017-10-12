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
                       consumeEnergy ();
                       break;
         case "left":  Agent.x +=1; 
                       consumeEnergy ();
                       break;
         case "up":    Agent.y +=1; 
                       consumeEnergy ();
                       break;  
         case "down":  Agent.y -=1;
                       consumeEnergy ();
                       break; 
         default: 
                  break;
		 }

		//return (newPos);
	}
	
	//
	public void consumeEnergy ()
	{
		Agent.energy ++;
		Agent.actionCount++;
	}
}
