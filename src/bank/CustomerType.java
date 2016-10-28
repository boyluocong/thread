package bank;
/*
 * 业务类型枚举值
 */
public enum CustomerType {
	COMMON,EXPRESS,VIP;
	private String tostring(){
		switch (this) {
		case COMMON:
			return "普通";
		case EXPRESS:
			return "快速";
		case VIP:
		    return "VIP";
		}
		return null;
	}

}
