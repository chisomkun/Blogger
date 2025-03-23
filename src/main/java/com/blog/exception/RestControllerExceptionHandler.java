package com.blog.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.nio.file.InvalidPathException;

@ControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(InvalidPathException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String exception(final InvalidPathException throwable, HttpServletRequest request, final Model model) {
        model.addAttribute("errorMessage", "Error with title name... use different name");
        return "error";
    }
}
