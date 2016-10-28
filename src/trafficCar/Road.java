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
	 * ģ��ʵ��ʮ��·�ڵĽ�ͨ�ƹ���ϵͳ�߼��������������£�
һ���첽������ɰ��ո���·����ʻ�ĳ�����
���磺
   ���������ȥ������ĳ�������ֱ�г���
   ���������ȥ������ĳ���������ת����
   �ɶ������ȥ������ĳ���������ת����
�����źŵƺ��ԻƵƣ�ֻ���Ǻ�ƺ��̵ơ�
����Ӧ������ת���������źŵƣ���ת���������źŵƿ��ơ�
�ġ������źŵƿ����߼�����ʵ��������ͨ��ͨ�ƿ����߼���ͬ����������������µĿ����߼���
            ע���ϱ������붫������������У�ͬ����ȴ�����Ӧ�ȷ���ֱ�г������������ת������
�塢ÿ����ͨ��·��ʱ��Ϊ1�루��ʾ����ͨ���߳�sleep�ķ�ʽģ�⣩��
����������ɳ���ʱ�����Լ����̵ƽ���ʱ�����Զ����������á�
�ߡ���Ҫ��ʵ��GUI��ֻ����ϵͳ�߼�ʵ�֣���ͨ��log��ʽչ�ֳ������н����
	 */
	/**
	 * ����Road�ṩ��ģ�⳵��������·�Ĺ��̣�����һ�����ϣ����ʱ��1-10��ļ���г���·���������뵽������ȥ��
	 * ����ÿ��һ������Ƿ�Ϊ�̣��������һ������
	 * ע������ʹ�����̳߳غͶ�ʱ��������
	 *  Executors.newSingleThreadExecutor();������һ�����̳߳أ���������������·��
	 *  Executors.newScheduledThreadPool(1)������һ����ʱ�̳߳أ�ÿһ���Ӽ���Ӧ�ĵƵ�״̬��
	 */
	 private List<String> rodes=new ArrayList<String>();
     private String  name;
	public Road(String name) {
		super();
		this.name = name;
		
		//ģ�⳵�����������·�Ĺ��� (���̳߳�)
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
		 
		 
		  //ÿ��һ�����Ӧ�ĵ��Ƿ�Ϊ�̣��������һ����    
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
