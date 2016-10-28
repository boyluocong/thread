package bank;

import java.util.ArrayList;
import java.util.List;

public class NumberManager {
  private int lastNumber=1;
  private List<Integer> queryNumber=new ArrayList<Integer>();
  public synchronized int generateNewNumber(){
	  queryNumber.add(lastNumber);
	  return lastNumber++;
	  
  }
  
  public synchronized Integer getSerivceNumber(){
	  Integer number=null;
	  if(queryNumber.size()>0){
		  number=queryNumber.remove(0);//��ȡ���ڼ������Ƴ����Ԫ��
	  }
	return number;
  }
}
