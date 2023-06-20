package javabeans;

public class ArticleBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int articleId;   //欄位共10
	private int userId;
	private String articleTitle;
	private String articleContent;
	private byte[] articleImg;    //待修正  byte[]
	private String articleImgMimeType;
	private String articleTime;  //待修正   java.sql.Timestamp
	private String articleType;   //文章種類
//	private int articleLikes;
//	private int articleFollowNum;
//	private int ariticleSaveNum;
	
	public ArticleBean() {
		super();
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

	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public byte[] getArticleImg() {
		return articleImg;
	}

	public void setArticleImg(byte[] articleImg) {
		this.articleImg = articleImg;
	}

	public String getArticleImgMimeType() {
		return articleImgMimeType;
	}

	public void setArticleImgMimeType(String articleImgMimeType) {
		this.articleImgMimeType = articleImgMimeType;
	}

	public String getArticleTime() {
		return articleTime;
	}

	public void setArticleTime(String articleTime) {
		this.articleTime = articleTime;
	}

	public String getArticleType() {
		return articleType;
	}

	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

