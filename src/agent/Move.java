package agent;

public class Move extends Effector {
	private String moveTo;

	public Move(String moveTo) {
		this.moveTo = moveTo;
	}

	@Override
	public void doAction() {
		
		 switch (this.moveTo) {
         case "right": Agent.posX -=1; 
                       consumeEnergy ();
                       break;
         case "left":  Agent.posX +=1; 
                       consumeEnergy ();
                       break;
         case "up":    Agent.posY -=1; 
                       consumeEnergy ();
                       break;  
         case "down":  Agent.posY +=1;
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
