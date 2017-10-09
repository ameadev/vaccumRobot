package agent;

public class Effector {

	public Effector() {
		super();
	}
	
	public enum EffectorIntentions {
		DRAWUP, PICKUP, MOVE("position");
		
		private String position;
		
		EffectorIntentions() {}
		EffectorIntentions(String position) {
			this.position = position;
		}
	}


	//
	public void doAction()
	{
		
	}
	
}
