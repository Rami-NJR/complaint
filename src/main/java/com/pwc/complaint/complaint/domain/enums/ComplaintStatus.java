package com.pwc.complaint.complaint.domain.enums;

import java.util.LinkedHashMap;
import java.util.Optional;

public class ComplaintStatus {

    private static final LinkedHashMap<String, ComplaintStatus> STATUS = new LinkedHashMap<>();

    public static final ComplaintStatus SUBMITTED = new ComplaintStatus("SUBMITTED", "Submitted");
    public static final ComplaintStatus RECEIVED = new ComplaintStatus("RECEIVED", "Received");

    public static Optional<ComplaintStatus> getInstance(final String type) {
        return Optional.ofNullable(STATUS.get(type));
    }

    private String status;
    private String friendlyName;

    public ComplaintStatus() {
        //do nothing
    }

    public ComplaintStatus(String type, String friendlyName) {
        this.friendlyName = friendlyName;
        this.setStatus(type);
    }

    public String getStatus() {
        return status;
    }

    public String getFriendlyName() {
        return friendlyName;
    }

    private void setStatus(final String status) {
        this.status = status;
        if (!STATUS.containsKey(status)) {
            STATUS.put(status, this);
        }
    }
}
