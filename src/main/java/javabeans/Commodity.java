package javabeans;

public class Commodity {
	private int comId;
	private String comName;
	private int comNumber;
	private int comPrice;
	private String comContent;
	private String comType;
	private byte[] comPic;
	private String comPicBase64;
	private String comStatus;
	private int itemNum;
	
	public String getComStatus() {
		return comStatus;
	}
	public void setComStatus(String comStatus) {
		this.comStatus = comStatus;
	}
	public int getComId() {
		return comId;
	}
	public int getItemNum() {
		return itemNum;
	}
	public void setItemNum(int itemNum) {
		this.itemNum = itemNum;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public int getComNumber() {
		return comNumber;
	}
	public void setComNumber(int comNumber) {
		this.comNumber = comNumber;
	}
	public int getComPrice() {
		return comPrice;
	}
	public void setComPrice(int comPrice) {
		this.comPrice = comPrice;
	}
	public String getComContent() {
		return comContent;
	}
	public void setComContent(String comContent) {
		this.comContent = comContent;
	}
	public String getComType() {
		return comType;
	}
	public void setComType(String comType) {
		this.comType = comType;
	}
	public byte[] getComPic() {
		return comPic;
	}
	public void setComPic(byte[] comPic) {
		this.comPic = comPic;
	}
	public Commodity() {
		super();
	}
	public Commodity(int comId, String comName, int comNumber, int comPrice, String comContent, String comType,
			byte[] comPic, String comStatus) {
		super();
		this.comId = comId;
		this.comName = comName;
		this.comNumber = comNumber;
		this.comPrice = comPrice;
		this.comContent = comContent;
		this.comType = comType;
		this.comPic = comPic;
		this.comStatus= comStatus;
	}

	public void setComPicBase64(String base64String) {
	    this.comPicBase64 = base64String;
	}
	public String getComPicBase64() {
	    return comPicBase64;
	}
}
