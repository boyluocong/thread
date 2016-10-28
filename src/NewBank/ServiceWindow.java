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
                System.out.println("服务窗口"+windowNum+"("+type.toString()+")开启!");  
                while(true){  
                    Integer customerNum = fetchNum();  
                    if (customerNum!=null){  
                        System.out.println("窗口"+windowNum+"为"+type.toString()+customerNum+"服务!");  
                        long startTime=System.currentTimeMillis();  
                        int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
                        try {  
                            sleepTime(serviceTime);  
                        } catch (InterruptedException e) {  
                            // TODO Auto-generated catch block  
                            e.printStackTrace();  
                        }  
                        long endTime=System.currentTimeMillis();  
                        System.out.println("窗口"+windowNum+"为"+type.toString()+  
                                 customerNum+"服务完毕!耗时"+(endTime-startTime)/1000+"秒");  
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
            System.out.println("窗口"+windowNum+"为"+"普通客户"+commonCustomerNum+"服务!");  
            long startTime=System.currentTimeMillis();  
            int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
            try {  
                    Thread.sleep(serviceTime);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            long endTime=System.currentTimeMillis();  
            System.out.println("窗口"+windowNum+"为"+"普通客户"+  
                    commonCustomerNum+"服务完毕!耗时"+(endTime-startTime)/1000+"秒");  
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
            System.out.println("窗口"+windowNum+"为"+"普通客户"+commonCustomerNum+"服务!");  
            long startTime=System.currentTimeMillis();  
            int serviceTime=MIN_TIME+(new Random().nextInt(MAX_TIME-MIN_TIME)+1);  
            try {  
                sleepTime(serviceTime);  
            } catch (InterruptedException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
            }  
            long endTime=System.currentTimeMillis();  
            System.out.println("窗口"+windowNum+"为"+"普通客户"+  
                    commonCustomerNum+"服务完毕!耗时"+(endTime-startTime)/1000+"秒");  
        }  
    }  
}
