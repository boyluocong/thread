package trafficCar;

/**
 * 
 * @author luo
 *  
 */

//�������ǿ��Կ����ܹ���12��·�ߣ�4������ת�ķ���S2E��E2N��N2W��W2S���������ƣ�������ǳ���״̬��
//����8������ɷ�Ϊ4�飬��1����S2N��N2S����2����S2W��N2E����������E2W��W2E��������E2S��W2N��
//ÿһ�����������ĳ��ǿ��Բ���ͨ���ġ����Կ��԰����ǵĵ�����Ϊͬһ״̬��
//����Ҫ�и�Lamp���󣬴�����12������ĵƣ������й̶������Ķ���12������
//������Ƴ�enum��ö�٣����ͣ����ö����������Ա������opposite��next��lighted���ֱ�����Ӧ�ĵƣ�
//��һ��Ҫ�����ĵƣ����̵ƣ����͵�ǰ�����Ƿ�ʱ���ģ�ͬʱҲ��������Ա������
//public boolean isLighted()���ص�ǰ����ĵƵ�״̬��
//public void light() �����õ�
//public Lamp blackOut()Ϩ��Ʋ�������һ��ơ�
public enum Lamp {
	S2N("N2S","S2W",false),S2W("N2E","E2W",false),E2W("W2E","E2S",false),E2S("W2N","S2N",false),  
    
    N2S(null,null,false),N2E(null,null,false),W2E(null,null,false),W2N(null,null,false),  
      
    S2E(null,null,true),E2N(null,null,true),N2W(null,null,true),W2S(null,null,true);
	
	private Lamp(String opposite,String next,boolean lighted){
		this.lighted=lighted;
		this.next=next;
		this.opposite=opposite;
	}
	private boolean lighted;
	private String opposite;  
    
    private String next;
    //���ص�ǰ����ĵƵ�״̬
    public boolean isLighted(){
    	return lighted;
    	
    }
    //�����õ�
    public void light(){
    	this.lighted=true;
    	if(this.opposite !=null){
    		Lamp.valueOf(opposite).light();
    	}
    	System.out.println(name() + " lamp is green�������ܹ�Ӧ����6�������ܿ�������������");
    }
    //Ϩ��Ʋ�������һ��ơ�
    public Lamp blackOut(){
    	this.lighted=false;
    	Lamp nextLamp=null;
    	
    	if(this.opposite !=null){
    		Lamp.valueOf(opposite).blackOut();
    	}
    	if(next !=null){
    		 nextLamp = Lamp.valueOf(next); 
    		 nextLamp.light();
    		 System.out.println("�̵ƴ�" + name() + "-------->�л�Ϊ" + next);
    	}
		return nextLamp;
    }
}
