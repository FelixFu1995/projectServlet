package javabeans;

public class ReplyBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int replyId;  //欄位共7
	private int articleId;
	private int userId;
	private String replyContent;
	private byte[] replyImg;  //待修正
	private String replyImgMimeType;
	private String replyTime; //待修正
//	private int replyLike;
	
	public ReplyBean() {
		super();
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public byte[] getReplyImg() {
		return replyImg;
	}

	public void setReplyImg(byte[] replyImg) {
		this.replyImg = replyImg;
	}

	public String getReplyImgMimeType() {
		return replyImgMimeType;
	}

	public void setReplyImgMimeType(String replyImgMimeType) {
		this.replyImgMimeType = replyImgMimeType;
	}

	public String getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(String replyTime) {
		this.replyTime = replyTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}