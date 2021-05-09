package com.pwc.complaint.complaint.service;

import com.pwc.complaint.complaint.repository.RoleRepository;
import com.pwc.complaint.complaint.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleDao;

    public List<Role> findAllRole(){
        return roleDao.findAll();
    }
}
