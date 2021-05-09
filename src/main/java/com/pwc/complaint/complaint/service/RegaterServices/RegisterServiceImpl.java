package com.pwc.complaint.complaint.service.RegaterServices;

import com.pwc.complaint.complaint.customException.EmailExistException;
import com.pwc.complaint.complaint.customException.RoleSupportException;
import com.pwc.complaint.complaint.customException.UserNameExistException;
import com.pwc.complaint.complaint.domain.Role;
import com.pwc.complaint.complaint.domain.enums.RoleType;
import com.pwc.complaint.complaint.domain.User;
import com.pwc.complaint.complaint.repository.RoleRepository;
import com.pwc.complaint.complaint.config.securityConfiguration.jwt.JwtUtil;
import com.pwc.complaint.complaint.service.UserService;
import com.pwc.complaint.complaint.wrapper.LoginDto;
import com.pwc.complaint.complaint.wrapper.SignUpDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    public User RegisterNewUser(SignUpDto signUpDto) {
        validateUserDto(signUpDto);

        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(encoder.encode(signUpDto.getPassword()));

        Set<String> strRoles = signUpDto.getUserRole();
        Set<Role> roles = new HashSet<>();
        List<Role> allRole = roleRepository.findAll();
        for (String role : strRoles) {
            allRole.stream().filter(r -> r.getRole().equals(RoleType.getInstance(role).DbRoleName())).forEach(r -> roles.add(r));
        }
        user.setRoles(roles);
        User userSave = userService.save(user);
        return userSave;
    }

    public String generateJwtToken(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(authentication);

    }

    public void validateUserDto(SignUpDto signUpDto) {

        if (signUpDto.getUsername() == null || signUpDto.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username can't be Null or Empty!");
        }
        if (signUpDto.getPassword() == null || signUpDto.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password can't be Null or Empty!");
        }
        if (signUpDto.getEmail() == null || signUpDto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email can't be Null or Empty!");
        }

        if (userService.existsByUsername(signUpDto.getUsername())) {
            throw new UserNameExistException("Username is already taken!");
        }
        if (userService.existsByEmail(signUpDto.getEmail())) {
            throw new EmailExistException("Email is already in use!");
        }
        Set<String> strRoles = signUpDto.getUserRole();
        for (String role : strRoles) {
            if (RoleType.getInstance(role) == null) {
                throw new RoleSupportException(String.format("The Role Provided \"%s\" is not exist or not configured yet!", role));
            }
        }
    }
}
