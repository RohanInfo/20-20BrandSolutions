package com.infocepts.casino.responsemodels;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.infocepts.casino.model.UserRole;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"authorized", "userName" , "userRole" , "token", "errorMessage" })
public class UserLoginResponse {
    boolean authorized;
    String userName;
    UserRole userRole =null;
    String token;
    String errorMessage = null;


    public UserLoginResponse() {}

    public UserLoginResponse(boolean authorized, String userName, UserRole userRole, String token, String errorMessage) {
        this.authorized = authorized;
        this.userName = userName;
        this.userRole = userRole;
        this.token = token;
        this.errorMessage = errorMessage;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
