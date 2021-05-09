package com.pwc.complaint.complaint;

import com.pwc.complaint.complaint.wrapper.JwtDto;
import com.pwc.complaint.complaint.wrapper.LoginDto;
import com.pwc.complaint.complaint.wrapper.SignUpDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class RegistrationIT {

    String baseUrl = "http://localhost:8080";
    String signupUrl = "/api/register/signup";
    String loginUrl = "/api/register/signin";

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void should_createNewAccount() {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Set<String> setOfRoles = new HashSet();
        setOfRoles.add("admin");
        setOfRoles.add("user");

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName("Rami2");
        signUpDto.setUsername("ramiTest2");
        signUpDto.setPassword("passTest2");
        signUpDto.setEmail("rami.csTest@outlook.com2");
        signUpDto.setUserRole(setOfRoles);

        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + signupUrl, new HttpEntity<>(signUpDto, headers), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    public void should_longInAndReturnJWTToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Set<String> setOfRoles = new HashSet();
        setOfRoles.add("admin");
        setOfRoles.add("user");

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName("Ahmad1");
        signUpDto.setUsername("AhmadTest1");
        signUpDto.setPassword("passTest1");
        signUpDto.setEmail("Ahmad.csTest@outlook.com1");
        signUpDto.setUserRole(setOfRoles);

        ResponseEntity<String> responseSignUp = restTemplate.postForEntity(baseUrl + signupUrl, new HttpEntity<>(signUpDto, headers), String.class);
        assertThat(responseSignUp.getStatusCode(), equalTo(HttpStatus.OK));

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("AhmadTest1");
        loginDto.setUsername("passTest1");

        ResponseEntity<JwtDto> responseSignIn = restTemplate.postForEntity(baseUrl + loginUrl, new HttpEntity<>(signUpDto, headers), JwtDto.class);
        assertThat(responseSignIn.getBody().getTokenType(), equalTo("Bearer"));
        assertThat(responseSignIn.getBody().getAccessToken(), notNullValue());
    }
}
