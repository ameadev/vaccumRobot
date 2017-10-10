package agent;

import environment.Manor;

public class Camera extends Sensor {

	// 
	
	//see environment and updating bilieves
	public void seeEnvironment(Manor manor, Agent owner) {
		owner.updateBilieves(manor.getRooms());
	}
	
}
