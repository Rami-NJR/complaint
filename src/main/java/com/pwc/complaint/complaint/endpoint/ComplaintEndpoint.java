package com.pwc.complaint.complaint.endpoint;

import com.pwc.complaint.complaint.customException.ComplaintStatusException;
import com.pwc.complaint.complaint.domain.Complaint;
import com.pwc.complaint.complaint.service.ComplaintService;
import com.pwc.complaint.complaint.service.ComplaintServiceImpl;
import com.pwc.complaint.complaint.wrapper.ComplaintDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/complaint")
public class ComplaintEndpoint {

    @Autowired
    ComplaintService complaintServiceImpl;

    @PostMapping("/create")
    public ResponseEntity<?> createComplaint(@RequestBody ComplaintDto complaintDto) {

        complaintServiceImpl.createComplaint(complaintDto);
        return ResponseEntity.ok("Your complaint submitted successfully");
    }

    @GetMapping("/user")
    public ResponseEntity<?> getComplaintForCurrentUser() {
        List<Complaint> complaintsForCurrentUser = complaintServiceImpl.findComplaintsForCurrentUser();
        List<ComplaintDto> complaintDtos = new ArrayList<>();
        complaintsForCurrentUser.forEach(c -> complaintDtos.add(new ComplaintDto(c)));
        return ResponseEntity.ok(complaintDtos);
    }

    /**
     * for admin permissions
     */
    @GetMapping("/{id}/complaintId")
    public ResponseEntity<?> getComplaintForUserById(@PathVariable("id") Long id) {
        Optional<Complaint> complaint = complaintServiceImpl.findComplaintById(id);
        ComplaintDto complaintDto = new ComplaintDto(complaint.get());
        return ResponseEntity.ok(complaintDto);
    }

    /**
     * for admin permissions
     */
    @GetMapping("/all")
    public ResponseEntity<?> getAllComplaint() {
        List<Complaint> complaint = complaintServiceImpl.findAllComplaint();
        List<ComplaintDto> complaintDtos = new ArrayList<>();
        complaint.forEach(c -> complaintDtos.add(new ComplaintDto(c)));
        return ResponseEntity.ok(complaintDtos);
    }

    /**
     * for admin permissions
     */
    @PatchMapping("/update/status")
    public ResponseEntity<?> updateComplaintStatus(@RequestParam String complaintStatus, @RequestParam Long id) {
        try {
            complaintServiceImpl.updateComplaintStatusById(complaintStatus, id);
        } catch (ComplaintStatusException e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("Complaint status updated successfully");
    }
}
