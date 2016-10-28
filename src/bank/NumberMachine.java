package bank;
/**
 * 
 * @author luo
 *
 *�������NumberManager�ͺ������NumberMachine��
 *NumberMachine����������ϵͳ��ֻ����һ�����ü򵥵ĵ������ģʽʵ��
 *
 */
public class NumberMachine {
	  //�õ������ģʽʵ��ȡ�Ż���NumberMachine��
	  // ����commonManager��expressManager��vipManagerҲ�ڴ���Ҳ�ͷֱ�ֻ��һ�����󣬸��԰�˳�����ɺ��롣
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
