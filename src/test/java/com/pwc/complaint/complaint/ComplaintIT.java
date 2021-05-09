package com.pwc.complaint.complaint;

import com.pwc.complaint.complaint.wrapper.ComplaintDto;
import com.pwc.complaint.complaint.wrapper.JwtDto;
import com.pwc.complaint.complaint.wrapper.LoginDto;
import com.pwc.complaint.complaint.wrapper.SignUpDto;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
class ComplaintIT {

    String baseUrl = "http://localhost:8080";
    String signupUrl = "/api/register/signup";
    String loginUrl = "/api/register/signin";
    String complaintCreationUrl = "/api/complaint/create";
    String allComplaintUrl = "/api/complaint/all";

    @Autowired
    RestTemplate restTemplate;

    @Test
    public void should_createComplaintSuccessfully() {

        ResponseEntity<JwtDto> responseSignIn = createAccountAndLogin("rami", "rami10", "123456", "rami@gmail.com", new HashSet<>(Arrays.asList("user")));

        assertThat(responseSignIn.getBody().getTokenType(), equalTo("Bearer"));
        assertThat(responseSignIn.getBody().getAccessToken(), notNullValue());

        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        h.add(HttpHeaders.AUTHORIZATION, "Bearer " + responseSignIn.getBody().getAccessToken());

        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setComplaint("any text, any text, any text, any text");
        complaintDto.setPriority("critical");
        complaintDto.setRepeated(false);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(baseUrl + complaintCreationUrl, new HttpEntity<>(complaintDto, h), String.class);
        assertThat(stringResponseEntity.getBody(), equalTo("Your complaint submitted successfully"));
    }

    @Test
    public void should_returnAllComplaints() {

        ResponseEntity<JwtDto> responseSignIn = createAccountAndLogin("ibrahim", "ibrahim110", "123456", "ibrahim100@gmail1.com", new HashSet<>(Arrays.asList("admin")));

        assertThat(responseSignIn.getBody().getTokenType(), equalTo("Bearer"));
        assertThat(responseSignIn.getBody().getAccessToken(), notNullValue());

        HttpHeaders h = new HttpHeaders();
        h.setContentType(MediaType.APPLICATION_JSON);
        h.add(HttpHeaders.AUTHORIZATION, "Bearer " + responseSignIn.getBody().getAccessToken());

        ComplaintDto complaintDto = new ComplaintDto();
        complaintDto.setComplaint("any text, any text, any text, any text");
        complaintDto.setPriority("critical");
        complaintDto.setRepeated(false);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity(baseUrl + complaintCreationUrl, new HttpEntity<>(complaintDto, h), String.class);
        assertThat(stringResponseEntity.getBody(), equalTo("Your complaint submitted successfully"));

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + allComplaintUrl);
        HttpEntity<?> entity = new HttpEntity<>(h);
        ResponseEntity<List> complaintDtos = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, List.class);
        assertThat(complaintDtos.getBody().size(),greaterThanOrEqualTo(1));
    }

    @Test
    @Ignore
    public void should_updateComplaintStats(){
        // todo
    }

    public ResponseEntity<JwtDto> createAccountAndLogin(String name, String userName, String password, String email, Set<String> roles) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setName(name);
        signUpDto.setUsername(userName);
        signUpDto.setPassword(password);
        signUpDto.setEmail(email);
        signUpDto.setUserRole(roles);

        ResponseEntity<String> responseSignUp = restTemplate.postForEntity(baseUrl + signupUrl, new HttpEntity<>(signUpDto, headers), String.class);
        assertThat(responseSignUp.getStatusCode(), equalTo(HttpStatus.OK));

        LoginDto loginDto = new LoginDto();
        loginDto.setUsername(userName);
        loginDto.setUsername(password);
        ResponseEntity<JwtDto> responseSignIn = restTemplate.postForEntity(baseUrl + loginUrl, new HttpEntity<>(signUpDto, headers), JwtDto.class);
        return responseSignIn;
    }
}
