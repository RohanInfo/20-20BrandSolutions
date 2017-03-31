package com.infocepts.casino.responsemodels;

/**
 * Created by rragashe on 30-03-17.
 */

public class UserCreationResponse {
    UserCreationStatus userCreationStatus;
    int exitCode;
    String errorMessage;

    public UserCreationResponse() {
    }

    public UserCreationResponse(UserCreationStatus userCreationStatus, int exitCode, String errorMessage) {
        this.userCreationStatus = userCreationStatus;
        this.exitCode = exitCode;
        this.errorMessage = errorMessage;
    }

    public UserCreationStatus getUserCreationStatus() {
        return userCreationStatus;
    }

    public void setUserCreationStatus(UserCreationStatus userCreationStatus) {
        this.userCreationStatus = userCreationStatus;
    }

    public int getExitCode() {
        return exitCode;
    }

    public void setExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
