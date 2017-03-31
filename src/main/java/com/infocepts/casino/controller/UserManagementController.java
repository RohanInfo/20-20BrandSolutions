package com.infocepts.casino.controller;

import com.infocepts.casino.dao.TestMstr;
import com.infocepts.casino.model.User;
import com.infocepts.casino.dao.UserManagementRepository;
import com.infocepts.casino.responsemodels.UserCreationResponse;
import com.infocepts.casino.responsemodels.UserCreationStatus;
import com.infocepts.casino.responsemodels.UserLoginResponse;
import com.infocepts.casino.service.UserManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
/*@RequestMapping("/api/infocepts/mstr/v1")*/
@PropertySource(value="classpath:Error.properties", ignoreResourceNotFound=true)
public class UserManagementController {

    private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

    @Autowired
    private UserManagementService userManagementService;

    @Autowired
    private UserManagementRepository userManagementRepository;

    @RequestMapping("/")
    public ResponseEntity isUp()
    {
        return new ResponseEntity(HttpStatus.OK);
    }





    @RequestMapping(value = "/login",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody User user){
        logger.info("Inside Method : login()");

        System.out.println(user.toString());

        return new ResponseEntity<UserLoginResponse>( this.userManagementService.loginService(user), HttpStatus.OK);
    }


    @RequestMapping(value = "/createUser",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreationResponse> createUser(@RequestBody User user ) {
        logger.info("Inside Method : createUser()");
        if(null==user || null==user.getUserName() || null== user.getPassword()){
            logger.error(user.toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        System.out.println(user.toString());

        TestMstr testMstr = new TestMstr();
        int res = testMstr.createLoginUser(user.getUserName());

        UserCreationResponse userCreationResponse = new UserCreationResponse();
        userCreationResponse.setExitCode(res);

        if(res==0){
            userCreationResponse.setUserCreationStatus(UserCreationStatus.SUCCESS);
        }
        else{
            userCreationResponse.setUserCreationStatus(UserCreationStatus.FAIL);
            userCreationResponse.setErrorMessage("User with same name already exists.");
        }

        return new ResponseEntity<UserCreationResponse>( userCreationResponse ,HttpStatus.OK);
    }


    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteUser(@RequestBody User user){
        logger.info("Inside Method : deleteUser()");
        if(null==user || null==user.getUserName()){
            logger.error(user.getUserName());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        //todo:call mstr to delete user and remove it from DB
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/updateUserPassword",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUserPassword(@RequestBody User user){
        logger.info("Inside Method : updateUserPassword()");
        if(null==user || null==user.getUserName() || null== user.getPassword()){
            logger.error(user.toString());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        //todo:call mstr toupdate password  and update it in DB
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/getAllUsers",method = RequestMethod.GET)
    public ResponseEntity getAllUsers(){
        logger.info("Inside Method : getAllUsers()");

        //todo:get all the users created in mstr form DB
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "/getLogs",method = RequestMethod.GET)
    public ResponseEntity getLogs(){
        return new ResponseEntity(HttpStatus.OK);
    }

}
