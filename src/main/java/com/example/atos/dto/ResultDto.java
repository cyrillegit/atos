package com.example.atos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultDto<T> {

    private HttpStatus httpStatus;

    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T resultObject;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> resultObjects;

    public ResultDto(T resultObject, HttpStatus httpStatus, String message) {
        this.resultObject = resultObject;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    @JsonProperty("status")
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("resource")
    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    @JsonProperty("resources")
    public List<T> getResultObjects() {
        return resultObjects;
    }

    public void setResultObjects(List<T> resultObjects) {
        this.resultObjects = resultObjects;
    }
}
