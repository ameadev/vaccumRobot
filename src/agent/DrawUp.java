package agent;

import environment.Element;

public class DrawUp extends Effector {
	
	public void doAction () {
		System.out.println("Drowup " +Agent.newPosX + ":" + Agent.newPosY);
		Agent.manor.delRoomContent(Agent.newPosX,Agent.newPosY);
		consumeEnergy();
	}
	
	public void consumeEnergy () {
		Element currentElement = Agent.manor.getRoomsElement(Agent.posX,Agent.posY);
		if ((currentElement.getSize() == 1) && (currentElement.getContent().get(0) == 1)) {
			Agent.energy +=2;
		} else if ((currentElement.getSize() == 1) && ((currentElement.getContent().get(0) == 1) || (currentElement.getContent().get(1) == 1))) {
			Agent.energy +=2;
		} else {
			Agent.energy ++;
		}
		Agent.actionCount++;
	}
}
