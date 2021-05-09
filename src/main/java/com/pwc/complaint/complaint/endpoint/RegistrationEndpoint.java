package com.pwc.complaint.complaint.endpoint;

import com.pwc.complaint.complaint.service.RegaterServices.RegisterService;
import com.pwc.complaint.complaint.wrapper.JwtDto;
import com.pwc.complaint.complaint.wrapper.SignUpDto;
import com.pwc.complaint.complaint.wrapper.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationEndpoint {

    @Autowired
    RegisterService registerService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {

        String jwt = registerService.generateJwtToken(loginDto);
        return ResponseEntity.ok(new JwtDto(jwt));
    }


    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignUpDto signUpDto) {

        try {
            registerService.RegisterNewUser(signUpDto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body("registration completed successfully!");
    }
}
