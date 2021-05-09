package com.pwc.complaint.complaint.service;

import com.pwc.complaint.complaint.customException.ComplaintStatusException;
import com.pwc.complaint.complaint.domain.Complaint;
import com.pwc.complaint.complaint.domain.User;
import com.pwc.complaint.complaint.domain.enums.ComplaintStatus;
import com.pwc.complaint.complaint.repository.ComplaintRepository;
import com.pwc.complaint.complaint.wrapper.ComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    ComplaintRepository complaintRepository;

    @Autowired
    UserService userService;

    public void createComplaint(ComplaintDto complaintDto) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();

        Complaint complaint = new Complaint();
        complaint.setComplaintStatus(ComplaintStatus.SUBMITTED.getStatus());
        complaint.setPriority(complaintDto.getPriority());
        complaint.setRepeated(complaintDto.isRepeated());
        complaint.setComplaint(complaintDto.getComplaint());
        complaint.setUser(userService.findByUsername(userDetails.getUsername()).get());
        complaintRepository.save(complaint);

    }

    public List<Complaint> findComplaintsForCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        Optional<User> currentUser = userService.findByUsername(userDetails.getUsername());
        List<Complaint> allComplaintByUser = complaintRepository.findAllByUser(currentUser.get());
        return allComplaintByUser;
    }

    public List<Complaint> findAllComplaint() {
        return complaintRepository.findAll();
    }

    @PreAuthorize("hasAuthority('admin')")
    public Optional<Complaint> findComplaintById(Long id) {
        return complaintRepository.findById(id);
    }

    @Transactional
    public Integer updateComplaintStatusById(String complaintStatus, Long id) {
        if (!ComplaintStatus.getInstance(complaintStatus).isPresent()) {
            throw new ComplaintStatusException(String.format("The complaintStatus Provided \"%s\" is not exist or not configured yet!", complaintStatus));
        }
        return complaintRepository.updateComplaintStatusById(ComplaintStatus.getInstance(complaintStatus).get().getStatus(), id);
    }
}
