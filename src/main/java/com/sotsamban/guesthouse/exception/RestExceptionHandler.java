package com.sotsamban.guesthouse.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sotsamban.guesthouse.common.ApiStatus;
import com.sotsamban.guesthouse.common.EmptyJsonResponse;
import com.sotsamban.guesthouse.common.StatusCode;
import com.sotsamban.guesthouse.dto.response.ApiResponse;
import com.sotsamban.guesthouse.logging.AppLogManager;
import com.sotsamban.guesthouse.utils.StringUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Component
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {



    /**
     * Handle HandleBusinessException
     *
     * @param ex BusinessException
     * @return the ApiError object
     */
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(final BusinessException ex) {

        AppLogManager.error(ex);
        StatusCode statusCode = ex.getErrorCode();
        ApiStatus apiStatus = new ApiStatus(statusCode);
        apiStatus.setMessage(StringUtils.defaultIfBlank(ex.getMessage(), statusCode.getMessage()));
//        apiStatus.setMessage(MessageHelper.getMessage(String.valueOf(statusCode.getCode()), statusCode.getMessage()));
        return buildResponseEntity(apiStatus, ex.getBody(), statusCode.getHttpCode());

    }

    /**
     * Handle handleThrowable
     *
     * @param ex      Throwable
     * @return the ApiError object
     */
    @ExceptionHandler(Throwable.class)
    protected ResponseEntity<Object> handleThrowable(Throwable ex) {
        AppLogManager.error(ex);
//        apiError.setMessage(ex.getMessage());
//        apiError.setDebugMessage(ExceptionUtils.getStackTrace(ex));

        return buildResponseEntity(new ApiStatus(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error"));

    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        AppLogManager.error(ex);
        return buildResponseEntity(new ApiStatus(statusCode.value(), "Internal Server Error"));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, new EmptyJsonResponse());

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(apiStatus.getCode()));
    }

    public ResponseEntity<Object> buildResponseEntity(ApiStatus apiStatus, Object data, int httpCode) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(apiStatus, data == null ? new EmptyJsonResponse() : data);

        return new ResponseEntity<>(apiResponse, HttpStatusCode.valueOf(httpCode));
    }

}
