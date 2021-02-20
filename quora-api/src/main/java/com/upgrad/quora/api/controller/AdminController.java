package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.modal.DeleteUserResponse;
import com.upgrad.quora.service.business.AuthenticationService;
import com.upgrad.quora.service.business.UserService;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.exception.AuthenticationFailedException;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "/admin")
public class AdminController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("userId") String userId, HttpServletRequest request) throws AuthenticationFailedException, AuthorizationFailedException, UserNotFoundException {
        String token = request.getHeader("access-token");
        UserAuthEntity auth = authenticationService.authenticate(token);
        userService.deleteUser(userId, auth);
        DeleteUserResponse response = new DeleteUserResponse(userId, "USER SUCCESSFULLY DELETED");
        return new ResponseEntity<DeleteUserResponse>(response, HttpStatus.OK);
    }

}
