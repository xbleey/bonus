package com.xbleey.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR_4XX_VIEW = "error/4xx";
    private static final String ERROR_5XX_VIEW = "error/5xx";

    @ExceptionHandler(GlobalException.class)
    public ModelAndView handleGlobalException(
            GlobalException ex,
            HttpServletRequest request,
            HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        ModelAndView mv = new ModelAndView(ERROR_4XX_VIEW);
        mv.addObject("status", HttpStatus.BAD_REQUEST.value());
        mv.addObject("timestamp", LocalDateTime.now());
        mv.addObject("exception", ex.getClass().getName());
        mv.addObject("message", buildMessage(ex));
        mv.addObject("path", request.getRequestURI());
        return mv;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(
            Exception ex,
            HttpServletRequest request,
            HttpServletResponse response) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        ModelAndView mv = new ModelAndView(ERROR_5XX_VIEW);
        mv.addObject("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        mv.addObject("timestamp", LocalDateTime.now());
        mv.addObject("exception", ex.getClass().getName());
        mv.addObject("message", ex.getMessage());
        mv.addObject("path", request.getRequestURI());
        return mv;
    }

    private String buildMessage(GlobalException ex) {
        String message = ex.getMessage();
        if (message == null || message.isBlank()) {
            return ex.getCode();
        }
        return ex.getCode() + ": " + message;
    }
}
