package NewBank;

public class MainClass {
	public static void main(String[] args) {  
        // TODO Auto-generated method stub  
          
        for(int i=1;i<5;i++){  
            ServiceWindow sw=null;  
            sw=new ServiceWindow(i,QueueMachine.COMMON);  
            sw.start();  
        }  
        new ExpressServiceWindow(5,QueueMachine.EXPRESS).start();  
        new VipServiceWindow(6,QueueMachine.VIP).start();  
          
        QueueMachine.COMMON.start();  
        QueueMachine.EXPRESS.start();  
        QueueMachine.VIP.start();  
    }  
}
