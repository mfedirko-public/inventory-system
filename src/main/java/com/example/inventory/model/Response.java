package com.example.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.NonNull;
import lombok.Value;

import java.util.List;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    T data;
    @NonNull Status status;
    List<ErrorDetails> errors;

    public static <T> Response<T> success() {
        return of(null, Status.SUCCESS, null);
    }
    public static <T> Response<T> success(T data) {
        return of(data, Status.SUCCESS, null);
    }
    public static <T> Response<T> noData(T data) {
        return of(data, Status.NO_DATA, null);
    }
    public static <T> Response<T> ofErrors(List<ErrorDetails> errors) {
        return of(null, Status.FAILURE, errors);
    }
    public static <T> Response<T> of(T data, Status sts, List<ErrorDetails> errors) {
        return new Response<>(data, sts, errors);
    }

    @Value
    static class ErrorDetails {
        String code;
        String description;
    }
    enum Status {
        SUCCESS,
        FAILURE,
        NO_DATA;
    }
}
