package bank;
/**
 * 
 * @author luo
 *
 *码管理器NumberManager和号码机器NumberMachine，
 *NumberMachine对象在整个系统中只能有一个，用简单的单例设计模式实现
 *
 */
public class NumberMachine {
	  //用单例设计模式实现取号机器NumberMachine，
	  // 这样commonManager、expressManager、vipManager也内存中也就分别只有一个对象，各自按顺序生成号码。
	private NumberMachine(){}
    private static NumberMachine instance=new NumberMachine();
    public static NumberMachine getInstance(){
    	return instance;
    }
    
    private NumberManager commonManager=new NumberManager();
    private NumberManager expressManager=new NumberManager();
    private NumberManager vipManager=new NumberManager();
	public NumberManager getCommonManager() {
		return commonManager;
	}
	public NumberManager getExpressManager() {
		return expressManager;
	}
	public NumberManager getVipManager() {
		return vipManager;
	}
    
    
}
