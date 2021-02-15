package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class QuestionController {

    // This method will create a new question posted by an authorized user.
    @RequestMapping(method = RequestMethod.POST, path = "/question/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(
            @RequestHeader("authorization") final String authorization,
            final QuestionRequest questionRequest) throws AuthorizationFailedException {
        QuestionResponse questionResponse = new QuestionResponse();
        QuestionEntity questionEntity = new QuestionEntity(); // replace this with a call to Business Service Class after is has been implemented
        questionResponse.setId(questionEntity.getUuid());
        questionResponse.setStatus("QUESTION CREATED");
        return new ResponseEntity<QuestionResponse>(questionResponse, HttpStatus.CREATED);
    }

    // This method will delete a question posted by an authorized user.
    @RequestMapping(method = RequestMethod.DELETE, path = "/question/delete/{questionId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(
            @RequestHeader("authorization") final String authorization,
            @PathVariable("questionId") final String questionId)
            throws AuthorizationFailedException, InvalidQuestionException {
        QuestionDeleteResponse questionDeleteResponse = new QuestionDeleteResponse();
        QuestionEntity questionEntity = new QuestionEntity(); // replace this with a call to Business Service Class after is has been implemented
        questionDeleteResponse.setId(questionEntity.getUuid());
        questionDeleteResponse.setStatus("QUESTION DELETED");
        return new ResponseEntity<QuestionDeleteResponse>(questionDeleteResponse, HttpStatus.OK);
    }

    // This method will update an existing question if it has been edited by an authorized user
    @RequestMapping(method = RequestMethod.PUT, path = "/question/edit/{questionId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionEditResponse> editQuestionContent(
            @RequestHeader("authorization") final String authorization,
            @PathVariable("questionId") final String questionId,
            final QuestionEditRequest questionEditRequest)
            throws AuthorizationFailedException, InvalidQuestionException {
        QuestionEditResponse questionEditResponse = new QuestionEditResponse();
        QuestionEntity questionEntity = new QuestionEntity(); // replace this with a call to Business Service Class after is has been implemented
        questionEditResponse.setId(questionEntity.getUuid());
        questionEditResponse.setStatus("QUESTION EDITED");
        return new ResponseEntity<QuestionEditResponse>(questionEditResponse, HttpStatus.OK);
    }
}
