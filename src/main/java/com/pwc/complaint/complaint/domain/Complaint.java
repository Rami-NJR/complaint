package com.pwc.complaint.complaint.domain;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@Entity
@Table(name = "PWC_COMPLAINT")
public class Complaint implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPLAINT_ID")
    private Long id;

    @Column(name = "isRepeated")
    private boolean isRepeated;

    @Column(name = "COMPLAINT_STATUS")
    private String ComplaintStatus;

    @Column(name = "COMPLAINT", length = 500)
    private String complaint;

    @Column(name = "PRIORITY")
    private String Priority;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class, cascade = CascadeType.REFRESH)
    @JoinTable(name = "PWC_COMPLAINT_PWC_USER_XREF", joinColumns = @JoinColumn(name = "COMPLAINT_ID",
            referencedColumnName = "COMPLAINT_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ID",
            referencedColumnName = "USER_ID"))
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public String getComplaintStatus() {
        return ComplaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        ComplaintStatus = complaintStatus;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getPriority() {
        return Priority;
    }

    public void setPriority(String priority) {
        Priority = priority;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
