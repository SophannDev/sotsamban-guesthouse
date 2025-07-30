package com.sotsamban.guesthouse.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sotsamban.guesthouse.common.ApiStatus;
import com.sotsamban.guesthouse.common.StatusCode;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude
public class ApiResponse<T> {

    @JsonProperty("status")
    private ApiStatus statusCode;

    private T data;

    public ApiResponse(T data) {
        this.data = data;
    }

    public ApiResponse(ApiStatus statusCode, T data) {
        this.statusCode = statusCode;
        this.data = data;
    }

    @Builder
    public ApiResponse(StatusCode status, T data) {
        this.statusCode = new ApiStatus(status);
        this.data = data;
    }
}
