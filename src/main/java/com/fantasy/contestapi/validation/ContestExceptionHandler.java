package com.fantasy.contestapi.validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ContestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ContestException.class})
    protected Object handleContestException(RuntimeException e, WebRequest request) {
        ContestException exception = (ContestException) e;
        List<Map<String, String>> retErrors = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("name", "");
        map.put("fieldName", "");
        map.put("errorCode", exception.getErrorCode());
        map.put("message", exception.getErrorMessage());
        retErrors.add(map);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, retErrors, headers, getAnnotatedResponseStatus(e), request);
    }

    private HttpStatus getAnnotatedResponseStatus(Exception mawaniException) {
        ResponseStatus responseStatus = mawaniException.getClass().getAnnotation(ResponseStatus.class);
        if (responseStatus != null) {
            return responseStatus.code();
        }
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
