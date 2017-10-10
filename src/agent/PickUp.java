package agent;

import environment.Manor;

public class PickUp extends Effector {
	public void doAction() {
		//Manor pickUp = delContent();
		Manor.DelRoomConten(Agent.x,Agent.y);
	}
}
