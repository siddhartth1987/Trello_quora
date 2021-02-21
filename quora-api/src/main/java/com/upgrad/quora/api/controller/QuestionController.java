package com.upgrad.quora.api.controller;

import com.upgrad.quora.api.model.*;
import com.upgrad.quora.service.business.QuestionBusinessService;
import com.upgrad.quora.service.entity.QuestionEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.InvalidQuestionException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class QuestionController {

    @Autowired
    private QuestionBusinessService questionBusinessService;

    // This method will create a new question posted by an authorized user.
    @RequestMapping(method = RequestMethod.POST, path = "/question/create", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<QuestionResponse> createQuestion(
            @RequestHeader("authorization") final String authorization,
            final QuestionRequest questionRequest) throws AuthorizationFailedException {
        QuestionResponse questionResponse = new QuestionResponse();
        QuestionEntity questionEntity = questionBusinessService.createQuestion(authorization, questionRequest.getContent());
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
        QuestionEntity questionEntity = questionBusinessService.deleteQuestion(authorization, questionId);
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
        QuestionEntity questionEntity = questionBusinessService.editQuestionContent(authorization, questionId, questionEditRequest.getContent());
        questionEditResponse.setId(questionEntity.getUuid());
        questionEditResponse.setStatus("QUESTION EDITED");
        return new ResponseEntity<QuestionEditResponse>(questionEditResponse, HttpStatus.OK);
    }

    // This method will retrieve all questions posted in the application if the authorization is valid
    @RequestMapping(method = RequestMethod.GET, path = "/question/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuestions(
            @RequestHeader("authorization") final String authorization)
            throws AuthorizationFailedException {
        List<QuestionDetailsResponse> questionDetailsResponse = new ArrayList<QuestionDetailsResponse>();
        List<QuestionEntity> questionEntityList = questionBusinessService.getAllQuestions(authorization);
        for (QuestionEntity question : questionEntityList) {
            QuestionDetailsResponse questionDetails = new QuestionDetailsResponse();
            questionDetails.setId(question.getUuid());
            questionDetails.setContent(question.getContent());
            questionDetailsResponse.add(questionDetails);
        }
        return new ResponseEntity<List<QuestionDetailsResponse>>(questionDetailsResponse,
                HttpStatus.OK);
    }

    // This method will retrieve all questions posted in the application by a user
    @RequestMapping(method = RequestMethod.GET, path = "/question/all/{userId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<QuestionDetailsResponse>> getAllQuestionsByUser(
            @RequestHeader("authorization") final String authorization,
            @PathVariable("userId") final String userId)
            throws AuthorizationFailedException, UserNotFoundException {
        List<QuestionDetailsResponse> questionDetailsResponse = new ArrayList<QuestionDetailsResponse>();
        List<QuestionEntity> questionEntityList = questionBusinessService.getAllQuestionsByUser(authorization, userId);
        for (QuestionEntity question : questionEntityList) {
            QuestionDetailsResponse questionDetails = new QuestionDetailsResponse();
            questionDetails.setId(question.getUuid());
            questionDetails.setContent(question.getContent());
            questionDetailsResponse.add(questionDetails);
        }
        return new ResponseEntity<List<QuestionDetailsResponse>>(questionDetailsResponse,
                HttpStatus.OK);
    }
}
