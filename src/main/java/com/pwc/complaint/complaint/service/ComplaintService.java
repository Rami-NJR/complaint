package com.pwc.complaint.complaint.service;

import com.pwc.complaint.complaint.domain.Complaint;
import com.pwc.complaint.complaint.wrapper.ComplaintDto;

import java.util.List;
import java.util.Optional;

public interface ComplaintService {

    void createComplaint(ComplaintDto complaintDto);

    List<Complaint> findComplaintsForCurrentUser();

    List<Complaint> findAllComplaint();

    Optional<Complaint> findComplaintById(Long id);

    Integer updateComplaintStatusById(String complaintStatus, Long id);
}
