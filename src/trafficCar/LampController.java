package trafficCar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @author luo
 *
 */
//对象LampController用来控制所有灯的状态，先点亮某一组灯，
//然后用定时器函数隔一定时间熄灭并点亮下一组灯（本例用的10秒）
//如此不断循环。
//注意，S2E、E2N、N2W、W2S这四个方向的灯为常亮状态。
public class LampController {
	private Lamp currentLamp;
	
	public LampController(){
		 //刚开始让由南向北的灯变绿;       
		currentLamp=Lamp.S2N;
		currentLamp.light();
	
	
	ScheduledExecutorService timer=Executors.newScheduledThreadPool(1);
	timer.scheduleAtFixedRate(
			new Runnable() {
		public void run() {
			System.out.println("时间到了");  
            currentLamp = currentLamp.blackOut(); 
		}    
	}, 
	10, 
	10, 
	TimeUnit.SECONDS);
    }
}