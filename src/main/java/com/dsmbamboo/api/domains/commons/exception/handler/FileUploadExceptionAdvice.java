package com.dsmbamboo.api.domains.commons.exception.handler;

import com.dsmbamboo.api.domains.commons.dto.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class FileUploadExceptionAdvice {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public void handleMaxSizeException(MaxUploadSizeExceededException exception, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ErrorResponse.builder()
                .statusCode(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE)
                .message("Request too large.")
                .objectMapper(new ObjectMapper())
                .response(resp)
                .build().write();

        resp.getWriter().flush();
        resp.getWriter().close();
    }

}
