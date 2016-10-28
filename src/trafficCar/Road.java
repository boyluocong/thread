package trafficCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Road {
	/*
	 * 
	 * 模拟实现十字路口的交通灯管理系统逻辑，具体需求如下：
一、异步随机生成按照各个路线行驶的车辆。
例如：
   由南向而来去往北向的车辆……直行车辆
   由西向而来去往南向的车辆……右转车辆
   由东向而来去往南向的车辆……左转车辆
二、信号灯忽略黄灯，只考虑红灯和绿灯。
三、应考虑左转车辆控制信号灯，右转车辆不受信号灯控制。
四、具体信号灯控制逻辑与现实生活中普通交通灯控制逻辑相同，不考虑特殊情况下的控制逻辑。
            注：南北向车辆与东西向车辆交替放行，同方向等待车辆应先放行直行车辆而后放行左转车辆。
五、每辆车通过路口时间为1秒（提示：可通过线程sleep的方式模拟）。
六、随机生成车辆时间间隔以及红绿灯交换时间间隔自定，可以设置。
七、不要求实现GUI，只考虑系统逻辑实现，可通过log方式展现程序运行结果。
	 */
	/**
	 * 对象Road提供了模拟车辆不断上路的过程，定义一个集合，随机时间1-10秒的间隔有车上路并把它加入到集合中去，
	 * 并且每隔一秒检查灯是否为绿，是则放行一辆车。
	 * 注意这里使用了线程池和定时器技术，
	 *  Executors.newSingleThreadExecutor();定义了一个单线程池，用于描述汽车上路；
	 *  Executors.newScheduledThreadPool(1)定义了一个定时线程池，每一秒钟检查对应的灯的状态。
	 */
	 private List<String> rodes=new ArrayList<String>();
     private String  name;
	public Road(String name) {
		super();
		this.name = name;
		
		//模拟车辆不断随机上路的过程 (单线程池)
		 ExecutorService pool = Executors.newSingleThreadExecutor();
		 pool.execute(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<1000;i++){
					try {
						Thread.sleep((new Random().nextInt(10)+1)*1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				rodes.add(Road.this.name+"_"+i);	
				}
				
			}
		});
		 
		 
		  //每隔一秒检查对应的灯是否为绿，是则放行一辆车    
		 ScheduledExecutorService timerPool = Executors.newScheduledThreadPool(1);
		 timerPool.scheduleAtFixedRate(
		     new Runnable(){
					public void run() {
						if(rodes.size()>0){
							
						}
						
					}
				}, 
				1, 
				1,
				TimeUnit.SECONDS);
				
	}
     
}
