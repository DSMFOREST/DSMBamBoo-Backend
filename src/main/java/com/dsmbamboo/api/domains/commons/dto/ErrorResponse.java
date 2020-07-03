package com.dsmbamboo.api.domains.commons.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    @JsonProperty("status_code")
    private int statusCode;

    private String message;

    @JsonIgnore
    private ObjectMapper objectMapper;

    @JsonIgnore
    private HttpServletResponse response;

    public void write() throws IOException {
        response.setStatus(this.statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        String mappedValue = objectMapper.writeValueAsString(this);
        response.getWriter().write(mappedValue);
    }

}
