package agent;


public class PickUp extends Effector {
	public void doAction() {
		System.out.println("pickup " +Agent.newPosX + ":" + Agent.newPosY);
		int posX = Agent.newPosX;
		int posY = Agent.newPosY;
		Agent.manor.delRoomContent(posX, posY, Agent.getBeliefContent(posX, posY));
		consumeEnergy ();
	}
	
	public void consumeEnergy() {
		Agent.energy ++;
		Agent.actionCount++;
	}
}
