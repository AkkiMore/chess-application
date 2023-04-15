package com.bank.application.exception;

public class InvalidInputException extends RuntimeException {

    String errorMsg;

    public InvalidInputException(String msg){
        this.errorMsg = msg;
    }

    public void setErrorMsg(String message){
        this.errorMsg = message;
    }

    public String getErrorMsg(){
        return this.errorMsg;
    }

}
