package agent;


public class PickUp extends Effector {
	public void doAction() {
		Agent.manor.delRoomContent(Agent.x,Agent.y);
		consumeEnergy ();
	}
	
	//
	public void consumeEnergy ()
	{
		Agent.energy ++;
		Agent.actionCount++;
	}
}
