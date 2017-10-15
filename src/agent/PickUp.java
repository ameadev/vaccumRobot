package agent;


public class PickUp extends Effector {
	public void doAction() {
		Agent.manor.delRoomContent(Agent.posX,Agent.posY);
		consumeEnergy ();
	}
	
	public void consumeEnergy() {
		Agent.energy ++;
		Agent.actionCount++;
	}
}
