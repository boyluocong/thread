/**
 * 
 */
package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author luo
 *  @date  2016-10-26
 */
public class BlockingQueue {
	private int queueSize = 10;
    private ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(queueSize,true);
     
    public static void main(String[] args)  {
    	BlockingQueue test = new BlockingQueue();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
         
        producer.start();
        consumer.start();
    }
     
    class Consumer extends Thread{
         
        @Override
        public void run() {
            consume();
        }
         
        private void consume() {
            while(true){
                try {
                    queue.take();
                    
                    System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��"+queue.size()+"��Ԫ��");
                  
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
     
    class Producer extends Thread{
         
        @Override
        public void run() {
            produce();
        }
         
        private void produce() {
            while(true){
                try {
                    queue.put(1);
                  
                    System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺"+(queueSize-queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
