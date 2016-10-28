package bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {

		// 产生4个普通窗户
		for (int i = 1; i < 5; i++) {
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setServiceWindow(i);
			commonWindow.start();
		}
		// 产生1个快速窗口
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		// 产生1个VIP窗口
		ServiceWindow VIPWindow = new ServiceWindow();
		VIPWindow.setType(CustomerType.VIP);
		VIPWindow.start();
		// 普通客户拿号
		
//		1. newSingleThreadExecutor
//		创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
//		2.newFixedThreadPool
//		创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
//		3. newCachedThreadPool
//		创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，
//		那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
//		4.newScheduledThreadPool
//		创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求。
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getCommonManager()
						.generateNewNumber();
				System.out.println("第" + Number + "号普通客户正在等待服务");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME, TimeUnit.SECONDS);

		// 快速客户拿号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getExpressManager()
						.generateNewNumber();
				System.out.println("第" + Number + "号快速客户正在等待服务");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME*3, TimeUnit.SECONDS);
		

		// VIP客户拿号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getVipManager()
						.generateNewNumber();
				System.out.println("第" + Number + "号VIP客户正在等待服务");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME*6, TimeUnit.SECONDS);
	}
}
