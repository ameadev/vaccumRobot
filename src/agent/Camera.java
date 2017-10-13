package agent;

import environment.Manor;

public class Camera extends Sensor {
	// Update local map
	public void seeEnvironment(Manor manor, Agent owner) {
		owner.updateBelief(manor.getRooms());
	}
	
}
