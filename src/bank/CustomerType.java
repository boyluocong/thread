package bank;
/*
 * ҵ������ö��ֵ
 */
public enum CustomerType {
	COMMON,EXPRESS,VIP;
	private String tostring(){
		switch (this) {
		case COMMON:
			return "��ͨ";
		case EXPRESS:
			return "����";
		case VIP:
		    return "VIP";
		}
		return null;
	}

}
