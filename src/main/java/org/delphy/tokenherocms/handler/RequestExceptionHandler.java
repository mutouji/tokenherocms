package org.delphy.tokenherocms.handler;

import org.delphy.tokenherocms.common.RestResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

/**
 * @author mutouji
 */
@ControllerAdvice
public class RequestExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestResult> defaultErrorHandler(Exception e) {
        e.printStackTrace();
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String msg = "server error, please try again later";
        Class exceptionClazz = e.getClass();
        if (Objects.equals(MissingServletRequestParameterException.class, exceptionClazz)) {
            msg = "incorrect parameter";
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (Objects.equals(HttpRequestMethodNotSupportedException.class, exceptionClazz)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            msg = e.getMessage();
        }
        return new ResponseEntity<>(new RestResult<>(httpStatus.value(), msg, e.getMessage()), httpStatus);
    }
}
