package NewBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public enum QueueMachine {
	COMMON, EXPRESS, VIP;  
    private List commonList = new ArrayList();  
    private List expressList = new ArrayList();  
    private List vipList = new ArrayList();  
    private static Integer customerNum = 1;// commonNum=1,expressNum=1,vipNum=1;  
  
    public String toString() {  
        String type = null;  
        if (this == COMMON)  
            type = "普通客户";  
        if (this == EXPRESS)  
            type = "快速客户";  
        if (this == VIP)  
            type = "VIP客户";  
        return type;  
    }  
  
    public synchronized void start() {  
        long period = 0;  
        switch (this) {  
        case COMMON:  
            period = new Random().nextInt(3) + 1;  
            break;  
        case EXPRESS:  
            period = new Random().nextInt(6) + 1;  
            break;  
        case VIP:  
            period = new Random().nextInt(18) + 1;  
            break;  
        }  
      
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {  
            public void run() {  
  
                switch (QueueMachine.this) {// 重点  
                case COMMON:  
                    System.out.println(customerNum + "号普通业务客户，欢迎光临，您前面有"  
                            + commonList.size() + "位客户");  
                    commonList.add(customerNum++);  
                    break;  
                case EXPRESS:  
                    System.out.println(customerNum + "号快速业务客户，欢迎光临，您前面有"  
                            + expressList.size() + "位客户");  
                    expressList.add(customerNum++);  
                    break;  
                case VIP:  
                    System.out.println(customerNum + "号VIP客户，欢迎光临，您前面有"  
                            + vipList.size() + "位客户");  
                    vipList.add(customerNum++);  
                    break;  
                }  
            }  
        }, 0, period, TimeUnit.SECONDS);  
    }  
  
    public synchronized Integer fetchNum() {  
        Integer fn = null;  
        switch (this) {  
        case COMMON:  
            if (commonList.size() > 0)  
                fn = (Integer) commonList.remove(0);  
            break;  
        case EXPRESS:  
            if (expressList.size() > 0)  
                fn = (Integer) expressList.remove(0);  
            break;  
        case VIP:  
            if (vipList.size() > 0)  
                fn = (Integer) vipList.remove(0);  
            break;  
        }  
        return fn;  
    }  
}
