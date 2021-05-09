package com.pwc.complaint.complaint.repository;

import com.pwc.complaint.complaint.domain.Complaint;
import com.pwc.complaint.complaint.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findAllByUser(User user);

    @Modifying
    @Query("update Complaint c set c.ComplaintStatus = :complaintStatus where c.id = :id ")
    Integer updateComplaintStatusById(String complaintStatus, Long id);

}
