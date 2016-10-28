/**
 * 
 */
package copyOnWrite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luo
 *  @date  2016-10-25
 */
public class CopyOnWriteList {
	
	public static void list(){
		 ArrayList<Integer> list = new ArrayList<Integer>();
	        list.add(2);
	        Iterator<Integer> iterator = list.iterator();
	        while(iterator.hasNext()){
	            Integer integer = iterator.next();
	            if(integer==2)
	               // list.remove(integer);
	            	 iterator.remove();
	        }
	        System.out.println(list.size());
	}
	
	public static void copyWriteList() {
		
		 
        final CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        list.add("a");  
		list.add("b");  
		list.add("c"); 
        final Iterator<String> iterator = list.iterator();
        Thread t = new Thread(new Runnable() {  
            @Override  
            public void run() {  
            	while(iterator.hasNext()){
    	            String str = iterator.next();
    	            if("b".equals(str))
    	                list.remove(str);
    	        } 
            }
        });  
        t.setDaemon(true);  
        t.start();  
        try {
			Thread.currentThread().sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}  
        for (String s : list) {  
            System.out.println(s);  
        }  
        System.out.println(list.size());  
    }  
//	public static void eachOne(){
//		// ��ʼ��һ��list������5��Ԫ��
//	    final List<Integer> list = new CopyOnWriteArrayList<>();
//	    for(int i = 0; i < 5; i++) {
//	        list.add(i);
//	    }
//
//	    // �߳�һ��ͨ��Iterator����List
//	    new Thread(new Runnable() {
//	        @Override
//	        public void run() {
//	            for(int item : list) {
//	                System.out.println("����Ԫ�أ�" + item);
//	                // ���ڳ����ܵ�̫�죬����sleep��1������������������ٶ�
//	                try {
//	                    Thread.sleep(1000);
//	                } catch (InterruptedException e) {
//	                    e.printStackTrace();
//	                }
//	            }
//	        }
//	    }).start();
//
//	    // �̶߳���removeһ��Ԫ��
//	    new Thread(new Runnable() {
//	        @Override
//	        public void run() {
//	            // ���ڳ����ܵ�̫�죬����sleep��1������������������ٶ�
//	            try {
//	                Thread.sleep(1000);
//	            } catch (InterruptedException e) {
//	                e.printStackTrace();
//	            }
//
//	            list.remove(4);
//	            System.out.println("list.remove(4)");
//	        }
//	    }).start();
//	}
	
	
	
	private static class ReadTask implements Runnable {
		List<String> list;

		public ReadTask(List<String> list) {
			this.list = list;
		}

		public void run() {
			for (String str : list) {
				System.out.println(str);
			}
		}
	}
	/**
	 * д�߳�
	 * @author wangjie
	 *
	 */
	private static class WriteTask implements Runnable {
		List<String> list;
		int index;

		public WriteTask(List<String> list, int index) {
			this.list = list;
			this.index = index;
		}

		public void run() {
			list.remove(index);
			
			list.add(index, "write_" + index);
		}
	}

	public void run() {
		final int NUM = 10;
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
		for (int i = 0; i < NUM; i++) {
			list.add("main_" + i);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(NUM);
		for (int i = 0; i < NUM; i++) {
			executorService.execute(new ReadTask(list));
			executorService.execute(new WriteTask(list, i));
		}
		executorService.shutdown();
	}
	
	
	
	public static void main(String[] args) {
		//list();
	  // 	copyWriteList();
		 new CopyOnWriteList().run();
		
	}

}
