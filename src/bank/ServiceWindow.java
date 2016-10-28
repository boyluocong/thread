package bank;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {
	private CustomerType type=CustomerType.COMMON;
	private int serviceWindow=1;
	public CustomerType getType() {
		return type;
	}
	public int getServiceWindow() {
		return serviceWindow;
	}
	public void setType(CustomerType type) {
		this.type = type;
	}
	public void setServiceWindow(int serviceWindow) {
		this.serviceWindow = serviceWindow;
	}
	
//	1. newSingleThreadExecutor
//	创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
//	2.newFixedThreadPool
//	创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
//	3. newCachedThreadPool
//	创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
//	那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
//	4.newScheduledThreadPool
//	创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
	
	public void start(){
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
			while(true){
             switch (type) {
				case COMMON:
					commonService();
					break;
				case EXPRESS:
					expressService();
					break;
				case VIP:
					VipService();
					break;
			}				
			}
			}
		});
	}
	

	private void commonService(){
		String windowName=serviceWindow+"号"+type+"窗口";
		Integer number=NumberMachine.getInstance().getCommonManager().getSerivceNumber();
		System.out.println(windowName+"正在获取普通客户！");
		if(number !=null){
			System.out.println(windowName+"开始为"+number+"号普通客户服务");
			  //普通窗口的服务时间是随机的，在最大服务时间与最小服务时间之间
			int maxRand=Contants.MAX_SERVICE_TIME-Contants.MIN_SERVICE_TIME;
			int serviceTime=new Random().nextInt(maxRand)+1+Contants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serviceTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(windowName+"为第"+number+"个普通客户完成服务，耗时"+serviceTime/1000+"秒");
		}else{
			System.out.println("没有普通客户等待，"+windowName+"先休息1秒");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void expressService(){
		String windowName=serviceWindow+"号"+type+"窗口";
		Integer number=NumberMachine.getInstance().getExpressManager().getSerivceNumber();
		System.out.println(windowName+"正在获取快速客户！");
		if(number !=null){
			System.out.println(windowName+"开始为"+number+"号快速客户服务");
			
			//快速客户的服务时间是最短的
		      long serviceTime=Contants.MIN_SERVICE_TIME;
		      try {
		        Thread.sleep(serviceTime);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务，耗时"+serviceTime/1000+"秒");
		}else{
			System.out.println(windowName+"没有取到快速任务！");
		      //快速窗口没有任务时可以为普通客户服务
		      commonService();
		}
	}
	public void VipService(){
		String windowName=serviceWindow+"号"+type+"窗口";
		Integer number=NumberMachine.getInstance().getVipManager().getSerivceNumber();
		System.out.println(windowName+"正在获取VIP客户！");
		if(number !=null){
			System.out.println(windowName+"开始为"+number+"号VIP客户服务");
			
			 //普通窗口的服务时间是随机的，在最大服务时间与最小服务时间之间
			int maxRand=Contants.MAX_SERVICE_TIME-Contants.MIN_SERVICE_TIME;
			int serviceTime=new Random().nextInt(maxRand)+1+Contants.MIN_SERVICE_TIME;
		      try {
		        Thread.sleep(serviceTime);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      System.out.println(windowName+"为第"+number+"个"+type+"客户完成服务，耗时"+serviceTime/1000+"秒");
		}else{
			System.out.println(windowName+"没有取到VIP任务！");
		      //VIP窗口没有任务时可以为普通客户服务
		      commonService();
		}
	}

}
