package com.pwc.complaint.complaint.service.RegaterServices;

import com.pwc.complaint.complaint.domain.User;
import com.pwc.complaint.complaint.wrapper.LoginDto;
import com.pwc.complaint.complaint.wrapper.SignUpDto;

public interface RegisterService {

    User RegisterNewUser(SignUpDto signUpDto);

    String generateJwtToken(LoginDto loginDto);
}
