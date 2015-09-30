package Main;

public class TimedEventComponentBase extends BaseComponent {

	Entity parent;
	float pingInterval;
	int pingCount;
	
	public TimedEventComponentBase(float pingIntervalInMills, int pingCount){
		this.name = "TimeComponentBase";
		this.pingInterval = pingIntervalInMills;
		this.pingCount = pingCount;
	}
		
}
