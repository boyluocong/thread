package trafficCar;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



/**
 * 
 * @author luo
 *
 */
//����LampController�����������еƵ�״̬���ȵ���ĳһ��ƣ�
//Ȼ���ö�ʱ��������һ��ʱ��Ϩ�𲢵�����һ��ƣ������õ�10�룩
//��˲���ѭ����
//ע�⣬S2E��E2N��N2W��W2S���ĸ�����ĵ�Ϊ����״̬��
public class LampController {
	private Lamp currentLamp;
	
	public LampController(){
		 //�տ�ʼ�������򱱵ĵƱ���;       
		currentLamp=Lamp.S2N;
		currentLamp.light();
	
	
	ScheduledExecutorService timer=Executors.newScheduledThreadPool(1);
	timer.scheduleAtFixedRate(
			new Runnable() {
		public void run() {
			System.out.println("ʱ�䵽��");  
            currentLamp = currentLamp.blackOut(); 
		}    
	}, 
	10, 
	10, 
	TimeUnit.SECONDS);
    }
}