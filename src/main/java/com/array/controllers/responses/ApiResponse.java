package com.array.controllers.responses;

import com.array.common.builder.IBuilder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XIII
 */
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
    private String error;

    private ApiResponse(Builder<T> builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.error = builder.error;
    }

    public static Builder getBuilder() {
        return new Builder<>();
    }

    public static class Builder<T> implements IBuilder<ApiResponse> {

        private int status;
        private String message;
        private T data;
        private String error;

        public Builder status(int status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder data(T data) {
            this.data = data;
            return this;
        }

        public Builder error(String error) {
            this.error = error;
            return this;
        }

        @Override
        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }
}
