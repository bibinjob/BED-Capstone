package com.bdc.project.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bdc.project.exception.BalanceNotEnoughException;
import com.bdc.project.exception.UserNotFoundException;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        return errorMap;
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
	public Map<String, String> handleBusinessException(UserNotFoundException ex){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("error Message", ex.getMessage());
		return errorMap;
	}
	
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@org.springframework.web.bind.annotation.ExceptionHandler(BalanceNotEnoughException.class)
	public Map<String, String> handleBusinessException(BalanceNotEnoughException ex){
		Map<String,String> errorMap = new HashMap<>();
		errorMap.put("error Message", ex.getMessage());
		return errorMap;
	}
	

}
