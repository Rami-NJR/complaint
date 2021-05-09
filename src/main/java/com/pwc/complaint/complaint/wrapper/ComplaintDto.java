package com.pwc.complaint.complaint.wrapper;

import com.pwc.complaint.complaint.domain.Complaint;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

public class ComplaintDto {

    @ApiModelProperty(hidden = true)
    private Long complaintId;

    private boolean isRepeated;

    private String complaint;

    private String priority;

    @ApiModelProperty(hidden = true)
    private String complaintStatus;

    @ApiModelProperty(hidden = true)
    private Long userId;

    @ApiModelProperty(hidden = true)
    private String userName;

    public ComplaintDto() {

    }

    public ComplaintDto(Complaint complaintDomain) {
        this.complaintId = complaintDomain.getId();
        this.isRepeated = complaintDomain.isRepeated();
        this.complaint = complaintDomain.getComplaint();
        this.priority = complaintDomain.getPriority();
        this.complaintStatus = complaintDomain.getComplaintStatus();
        this.userId = complaintDomain.getUser().getId();
        this.userName = complaintDomain.getUser().getName();

    }

    public Long getComplaintId() {
        return complaintId;
    }

    public void setComplaintId(Long complaintId) {
        this.complaintId = complaintId;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
