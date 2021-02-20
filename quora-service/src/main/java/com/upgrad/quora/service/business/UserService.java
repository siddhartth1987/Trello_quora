package com.upgrad.quora.service.business;

import com.upgrad.quora.service.dao.UserDao;
import com.upgrad.quora.service.entity.UserAuthEntity;
import com.upgrad.quora.service.entity.UserEntity;
import com.upgrad.quora.service.exception.AuthorizationFailedException;
import com.upgrad.quora.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserDao userRepo;

    @Transactional(propagation = Propagation.REQUIRED)
    public String deleteUser(String uuid, UserAuthEntity auth) throws AuthorizationFailedException, UserNotFoundException {
        if (auth.getUser().getRole().equals("nonadmin")) {
            throw new AuthorizationFailedException("ATHR-003", "Unauthorized Access, Entered user is not an admin");
        }

        UserEntity user = userRepo.getUser(uuid);

        if(user == null){
            throw new UserNotFoundException("USR-001", "User with entered uuid to be deleted does not exist");
        }

        userRepo.delete(user);
        return uuid;
    }

}
