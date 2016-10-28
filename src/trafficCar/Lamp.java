package trafficCar;

/**
 * 
 * @author luo
 *  
 */

//这里我们可以看到总共有12条路线，4个向右转的方向（S2E、E2N、N2W、W2S）不受限制，方向灯是常绿状态，
//其他8个方向可分为4组，第1组是S2N和N2S，第2组是S2W和N2E，第三组是E2W和W2E，第四组E2S和W2N。
//每一组的两个方向的车是可以并行通过的。所以可以把它们的灯设置为同一状态。
//首先要有个Lamp对象，代表这12个方向的灯，由于有固定个数的对象（12个），
//所以设计成enum（枚举）类型，这个枚举有三个成员变量，opposite、next和lighted，分别代表对应的灯，
//下一次要点亮的灯（即绿灯），和当前对象是否时亮的，同时也有三个成员方法，
//public boolean isLighted()返回当前对象的灯的状态，
//public void light() 点亮该灯
//public Lamp blackOut()熄灭灯并点亮下一组灯。
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
    //返回当前对象的灯的状态
    public boolean isLighted(){
    	return lighted;
    	
    }
    //点亮该灯
    public void light(){
    	this.lighted=true;
    	if(this.opposite !=null){
    		Lamp.valueOf(opposite).light();
    	}
    	System.out.println(name() + " lamp is green，下面总共应该有6个方向能看到汽车穿过！");
    }
    //熄灭灯并点亮下一组灯。
    public Lamp blackOut(){
    	this.lighted=false;
    	Lamp nextLamp=null;
    	
    	if(this.opposite !=null){
    		Lamp.valueOf(opposite).blackOut();
    	}
    	if(next !=null){
    		 nextLamp = Lamp.valueOf(next); 
    		 nextLamp.light();
    		 System.out.println("绿灯从" + name() + "-------->切换为" + next);
    	}
		return nextLamp;
    }
}
