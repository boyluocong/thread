package cn.itcast.heima2;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"��׼����������");						
						cdOrder.await();//����await()�������̻߳ᱻ��������ȴ�ֱ��countֵΪ0�ż���ִ��
						System.out.println("�߳�" + Thread.currentThread().getName() + 
						"�ѽ�������");								
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"��Ӧ�������");						
						cdAnswer.countDown();	//-1					
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		try {
			Thread.sleep((long)(Math.random()*10000));
		
			System.out.println("�߳�" + Thread.currentThread().getName() + 
					"������������");						
			cdOrder.countDown();//��countֵ��1
			System.out.println("�߳�" + Thread.currentThread().getName() + 
			"�ѷ���������ڵȴ����");	
			cdAnswer.await();//����await()�������̻߳ᱻ��������ȴ�ֱ��countֵΪ0�ż���ִ��
			System.out.println("�߳�" + Thread.currentThread().getName() + 
			"���յ�������Ӧ���");	
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();

	}
}
