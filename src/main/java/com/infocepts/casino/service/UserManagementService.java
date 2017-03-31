package com.infocepts.casino.service;
import com.infocepts.casino.dao.UserManagementRepository;
import com.infocepts.casino.model.User;
import com.infocepts.casino.responsemodels.UserLoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserManagementService {

    @Autowired
    UserManagementRepository userManagementRepository;


    public User createUser(String userName) {

        //todo :create user in mstr first


        //todo:check output of the console decide what to do

        return new User();

    }



    @Autowired
    UserLoginResponse userLoginResponse;
    public UserLoginResponse loginService(User user) {
        //check user input
        if (null == user || null == user.getUserName() || null == user.getPassword()
                || user.getUserName().length() < 4 || user.getPassword().length() < 4) {

            userLoginResponse.setAuthorized(false);
            userLoginResponse.setErrorMessage("Username or Password does not meet the length criteria.");

            return userLoginResponse;
        }
        else{
            return this.userManagementRepository.doLogin(user);
        }
    }
}
