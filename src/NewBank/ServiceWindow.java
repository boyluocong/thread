package NewBank;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow {
	public int windowNum=1;  
    public QueueMachine type=QueueMachine.COMMON;  
    public static final int MIN_TIME=1000,MAX_TIME=3000;  
    public  ServiceWindow(int windowNum,QueueMachine type){  
        this.type=type;  
        this.windowNum=windowNum;  
    }  
    public void start(){  
        Executors.newSingleThreadExecutor().execute(new Runnable(){  
            public void run(){  
                System.out.println("���񴰿�"+windowNum+"("+type.toString()+")����!");  
                while(true){  
                    Integer customerNum = fetchNum();  
                    if (customerNum!=null){  
                        System.out.println("����"+windowNum+"Ϊ"+type.toString()+customerNum+"����!");  
                        long startTime=System.currentTimeMillis();  
                        int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
                        try {  
                            sleepTime(serviceTime);  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                        long endTime=System.currentTimeMillis();  
                        System.out.println("����"+windowNum+"Ϊ"+type.toString()+  
                                 customerNum+"�������!��ʱ"+(endTime-startTime)/1000+"��");  
                    }  
                    else{  
                        extend();  
                    }  
                }  
            }  
  
          
        });  
    }  
  
    protected Integer fetchNum() {  
        Integer customerNum=QueueMachine.COMMON.fetchNum();  
        return customerNum;  
    }  
    protected void sleepTime(int serviceTime)  
            throws InterruptedException {  
        Thread.sleep(serviceTime);  
    }  
    protected void extend(){  
          
    }  
      
}  
class ExpressServiceWindow extends ServiceWindow{  
    public int windowNum=5;   
    public QueueMachine type=QueueMachine.EXPRESS;  
    public  ExpressServiceWindow(int windowNum,QueueMachine type){  
        super(windowNum, type);  
        this.type=type;  
        this.windowNum=windowNum;  
    }  
    protected Integer fetchNum() {  
        Integer customerNum=QueueMachine.EXPRESS.fetchNum();  
        return customerNum;  
    }  
    protected void sleepTime(int serviceTime)  
            throws InterruptedException {  
        Thread.sleep(MIN_TIME);  
    }  
    protected void extend(){  
        Integer commonCustomerNum =QueueMachine.COMMON.fetchNum();  
        if (commonCustomerNum!=null){  
            System.out.println("����"+windowNum+"Ϊ"+"��ͨ�ͻ�"+commonCustomerNum+"����!");  
            long startTime=System.currentTimeMillis();  
            int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
            try {  
                    Thread.sleep(serviceTime);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            long endTime=System.currentTimeMillis();  
            System.out.println("����"+windowNum+"Ϊ"+"��ͨ�ͻ�"+  
                    commonCustomerNum+"�������!��ʱ"+(endTime-startTime)/1000+"��");  
        }  
    }  
}  
class VipServiceWindow extends ServiceWindow{  
    public int windowNum=6;   
    public QueueMachine type=QueueMachine.VIP;  
    public  VipServiceWindow(int windowNum,QueueMachine type){  
        super(windowNum, type);  
        this.type=type;  
        this.windowNum=windowNum;  
    }  
    protected Integer fetchNum() {  
        Integer customerNum=QueueMachine.EXPRESS.fetchNum();  
        return customerNum;  
    }  
    protected void sleepTime(int serviceTime)  
            throws InterruptedException {  
        Thread.sleep(serviceTime);  
    }  
    protected void extend(){  
        Integer commonCustomerNum =QueueMachine.COMMON.fetchNum();  
        if (commonCustomerNum!=null){  
            System.out.println("����"+windowNum+"Ϊ"+"��ͨ�ͻ�"+commonCustomerNum+"����!");  
            long startTime=System.currentTimeMillis();  
            int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
            try {  
                sleepTime(serviceTime);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            long endTime=System.currentTimeMillis();  
            System.out.println("����"+windowNum+"Ϊ"+"��ͨ�ͻ�"+  
                    commonCustomerNum+"�������!��ʱ"+(endTime-startTime)/1000+"��");  
        }  
    }  
}
