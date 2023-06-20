package javabeans;

import java.util.Base64;

public class ActivityBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String activityId;
	private String activityName;
	private String activityContent;
	private byte[] activityCover;
	private String activityDate;
	private String activityLocation;
	private String activityStatus;
	private String activityCoverBase64; // 新增的屬性
	
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}

	public byte[] getActivityCover() {
		return activityCover;
	}
	public void setActivityCover(byte[] activityCover) {
		this.activityCover = activityCover;
	}
	public String getActivityDate() {
		return activityDate;
	}
	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public String getActivityLocation() {
		return activityLocation;
	}
	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}

	public String getActivityStatus() {
		return activityStatus;
	}
	public void setActivityStatus(String activityStatus) {
		this.activityStatus = activityStatus;
	}
	
    public String getActivityCoverBase64() {
        if (activityCover != null) {
            return Base64.getEncoder().encodeToString(activityCover);
        }
        return activityCoverBase64;
    }

    public void setActivityCoverBase64(String activityCoverBase64) {
        this.activityCoverBase64 = activityCoverBase64;
    }
}
	
	
	
	
