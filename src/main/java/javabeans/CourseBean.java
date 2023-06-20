package javabeans;

public class CourseBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer courseId;
	private String courseName;
	private String coachName;
	private byte[] courseImg;
	private String courseImgmimeType;
	private String courseIntroduce;
	private Integer courseCost;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCoachName() {
		return coachName;
	}
	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}
	public byte[] getCourseImg() {
		return courseImg;
	}
	public void setCourseImg(byte[] courseImg) {
		this.courseImg = courseImg;
	}
	public String getCourseIntroduce() {
		return courseIntroduce;
	}
	public void setCourseIntroduce(String courseIntroduce) {
		this.courseIntroduce = courseIntroduce;
	}
	public Integer getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(Integer courseCost) {
		this.courseCost = courseCost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getCourseImgmimeType() {
		return courseImgmimeType;
	}
	public void setCourseImgmimeType(String courseImgmimeType) {
		this.courseImgmimeType = courseImgmimeType;
	}

	
}