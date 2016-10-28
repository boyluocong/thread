package bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainClass {

	public static void main(String[] args) {

		// ����4����ͨ����
		for (int i = 1; i < 5; i++) {
			ServiceWindow commonWindow = new ServiceWindow();
			commonWindow.setServiceWindow(i);
			commonWindow.start();
		}
		// ����1�����ٴ���
		ServiceWindow expressWindow = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		// ����1��VIP����
		ServiceWindow VIPWindow = new ServiceWindow();
		VIPWindow.setType(CustomerType.VIP);
		VIPWindow.start();
		// ��ͨ�ͻ��ú�
		
//		1. newSingleThreadExecutor
//		����һ�����̵߳��̳߳ء�����̳߳�ֻ��һ���߳��ڹ�����Ҳ�����൱�ڵ��̴߳���ִ����������������Ψһ���߳���Ϊ�쳣��������ô����һ���µ��߳�������������̳߳ر�֤���������ִ��˳����������ύ˳��ִ�С�
//		2.newFixedThreadPool
//		�����̶���С���̳߳ء�ÿ���ύһ������ʹ���һ���̣߳�ֱ���̴߳ﵽ�̳߳ص�����С���̳߳صĴ�Сһ���ﵽ���ֵ�ͻᱣ�ֲ��䣬���ĳ���߳���Ϊִ���쳣����������ô�̳߳ػᲹ��һ�����̡߳�
//		3. newCachedThreadPool
//		����һ���ɻ�����̳߳ء�����̳߳صĴ�С�����˴�����������Ҫ���̣߳�
//		��ô�ͻ���ղ��ֿ��У�60�벻ִ�����񣩵��̣߳�������������ʱ�����̳߳��ֿ������ܵ�������߳����������񡣴��̳߳ز�����̳߳ش�С�����ƣ��̳߳ش�С��ȫ�����ڲ���ϵͳ������˵JVM���ܹ�����������̴߳�С��
//		4.newScheduledThreadPool
//		����һ����С���޵��̳߳ء����̳߳�֧�ֶ�ʱ�Լ�������ִ�����������
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getCommonManager()
						.generateNewNumber();
				System.out.println("��" + Number + "����ͨ�ͻ����ڵȴ�����");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME, TimeUnit.SECONDS);

		// ���ٿͻ��ú�
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getExpressManager()
						.generateNewNumber();
				System.out.println("��" + Number + "�ſ��ٿͻ����ڵȴ�����");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME*3, TimeUnit.SECONDS);
		

		// VIP�ͻ��ú�
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				Integer Number = NumberMachine.getInstance().getVipManager()
						.generateNewNumber();
				System.out.println("��" + Number + "��VIP�ͻ����ڵȴ�����");
			}
		}, 0, Contants.COMMON_CUSTOMER_INTERVAL_TIME*6, TimeUnit.SECONDS);
	}
}
