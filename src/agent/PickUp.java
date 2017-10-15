package agent;


public class PickUp extends Effector {
	public void doAction() {
		System.out.println("pickup " +Agent.newPosX + ":" + Agent.newPosY);
		Agent.manor.delRoomContent(Agent.newPosX,Agent.newPosY);
		consumeEnergy ();
	}
	
	public void consumeEnergy() {
		Agent.energy ++;
		Agent.actionCount++;
	}
}
