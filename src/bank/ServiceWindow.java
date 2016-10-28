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
//	����һ�����̵߳��̳߳ء�����̳߳�ֻ��һ���߳��ڹ�����Ҳ�����൱�ڵ��̴߳���ִ����������������Ψһ���߳���Ϊ�쳣��������ô����һ���µ��߳�������������̳߳ر�֤���������ִ��˳����������ύ˳��ִ�С�
//	2.newFixedThreadPool
//	�����̶���С���̳߳ء�ÿ���ύһ������ʹ���һ���̣߳�ֱ���̴߳ﵽ�̳߳ص�����С���̳߳صĴ�Сһ���ﵽ���ֵ�ͻᱣ�ֲ��䣬���ĳ���߳���Ϊִ���쳣����������ô�̳߳ػᲹ��һ�����̡߳�
//	3. newCachedThreadPool
//	����һ���ɻ�����̳߳ء�����̳߳صĴ�С�����˴�����������Ҫ���̣߳�
//	��ô�ͻ���ղ��ֿ��У�60�벻ִ�����񣩵��̣߳�������������ʱ�����̳߳��ֿ������ܵ�������߳����������񡣴��̳߳ز�����̳߳ش�С�����ƣ��̳߳ش�С��ȫ�����ڲ���ϵͳ������˵JVM���ܹ�����������̴߳�С��
//	4.newScheduledThreadPool
//	����һ����С���޵��̳߳ء����̳߳�֧�ֶ�ʱ�Լ�������ִ�����������
	
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
		String windowName=serviceWindow+"��"+type+"����";
		Integer number=NumberMachine.getInstance().getCommonManager().getSerivceNumber();
		System.out.println(windowName+"���ڻ�ȡ��ͨ�ͻ���");
		if(number !=null){
			System.out.println(windowName+"��ʼΪ"+number+"����ͨ�ͻ�����");
			  //��ͨ���ڵķ���ʱ��������ģ���������ʱ������С����ʱ��֮��
			int maxRand=Contants.MAX_SERVICE_TIME-Contants.MIN_SERVICE_TIME;
			int serviceTime=new Random().nextInt(maxRand)+1+Contants.MIN_SERVICE_TIME;
			try {
				Thread.sleep(serviceTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(windowName+"Ϊ��"+number+"����ͨ�ͻ���ɷ��񣬺�ʱ"+serviceTime/1000+"��");
		}else{
			System.out.println("û����ͨ�ͻ��ȴ���"+windowName+"����Ϣ1��");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public void expressService(){
		String windowName=serviceWindow+"��"+type+"����";
		Integer number=NumberMachine.getInstance().getExpressManager().getSerivceNumber();
		System.out.println(windowName+"���ڻ�ȡ���ٿͻ���");
		if(number !=null){
			System.out.println(windowName+"��ʼΪ"+number+"�ſ��ٿͻ�����");
			
			//���ٿͻ��ķ���ʱ������̵�
		      long serviceTime=Contants.MIN_SERVICE_TIME;
		      try {
		        Thread.sleep(serviceTime);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      System.out.println(windowName+"Ϊ��"+number+"��"+type+"�ͻ���ɷ��񣬺�ʱ"+serviceTime/1000+"��");
		}else{
			System.out.println(windowName+"û��ȡ����������");
		      //���ٴ���û������ʱ����Ϊ��ͨ�ͻ�����
		      commonService();
		}
	}
	public void VipService(){
		String windowName=serviceWindow+"��"+type+"����";
		Integer number=NumberMachine.getInstance().getVipManager().getSerivceNumber();
		System.out.println(windowName+"���ڻ�ȡVIP�ͻ���");
		if(number !=null){
			System.out.println(windowName+"��ʼΪ"+number+"��VIP�ͻ�����");
			
			 //��ͨ���ڵķ���ʱ��������ģ���������ʱ������С����ʱ��֮��
			int maxRand=Contants.MAX_SERVICE_TIME-Contants.MIN_SERVICE_TIME;
			int serviceTime=new Random().nextInt(maxRand)+1+Contants.MIN_SERVICE_TIME;
		      try {
		        Thread.sleep(serviceTime);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		      System.out.println(windowName+"Ϊ��"+number+"��"+type+"�ͻ���ɷ��񣬺�ʱ"+serviceTime/1000+"��");
		}else{
			System.out.println(windowName+"û��ȡ��VIP����");
		      //VIP����û������ʱ����Ϊ��ͨ�ͻ�����
		      commonService();
		}
	}

}
