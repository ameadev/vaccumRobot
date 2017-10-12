package agent;

import environment.Manor;

public class DrawUp extends Effector {
	public void doAction () {
		//Manor drawUp = delContent();
		Manor.delRoomContent(Agent.x,Agent.y);
		
	}
}
