package agent;

import environment.Element;

public class DrawUp extends Effector {
	
	public void doAction () {
		System.out.println("Drowup " +Agent.newPosX + ":" + Agent.newPosY);
		int posX = Agent.newPosX;
		int posY = Agent.newPosY;
		Agent.manor.delRoomContent(posX, posY, Agent.getBeliefContent(posX, posY));
		consumeEnergy();
	}
	
	public void consumeEnergy () 
	{
		Agent.actionCount++;
		Agent.energy ++;
		
	}
}
