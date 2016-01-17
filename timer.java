import java.util.Timer;
import java.util.TimerTask;

public class timer{
	int secondsPassed = 0;
	int minutes = 0;
	int seconds = 0;
	Timer myTimer = new Timer();
	TimerTask task = new TimerTask() {
		public void run(){
			secondsPassed++;
		}
	};
	
	public String getTime(){
		
		minutes = (secondsPassed / 60);
		seconds = secondsPassed % 60;
		String minutesString = Integer.toString(minutes);
		String secondsString = Integer.toString(seconds);
		
		if (seconds < 10) {
			return ( minutesString + ":0" + secondsString);}
		else {
			return (minutesString + ":" + secondsString);
		}
	}

	public void start(){
	myTimer.schedule(task, 1000, 1000);
	}
	
	public void stop(){
		task.cancel();
		myTimer.purge();
		myTimer.cancel();
		
		//myTimer = null;
		}
	
}

    
