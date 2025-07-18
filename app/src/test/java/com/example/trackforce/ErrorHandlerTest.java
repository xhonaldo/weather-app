package com.example.trackforce;

import static org.junit.Assert.*;

import com.example.trackforce.domain.util.ErrorHandler;


import org.junit.Test;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.HttpException;

public class ErrorHandlerTest {

    private HttpException createHttpException(String jsonError, int code) {
        ResponseBody errorBody = ResponseBody.create(
                MediaType.parse("application/json"), jsonError);

        Response<?> response = Response.error(code, errorBody);

        return new HttpException(response);
    }

    @Test
    public void testGetErrorMessage_BadRequest_WithMessage() {
        String json = "{\"message\":\"Invalid input\"}";
        HttpException ex = createHttpException(json, 400);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Invalid input", msg);
    }

    @Test
    public void testGetErrorMessage_BadRequest_NoMessage() {
        HttpException ex = createHttpException("{}", 400);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Bad Request.", msg);
    }

    @Test
    public void testGetErrorMessage_Unauthorized_WithError() {
        String json = "{\"error\":\"Token expired\"}";
        HttpException ex = createHttpException(json, 401);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Token expired", msg);
    }

    @Test
    public void testGetErrorMessage_Forbidden_WithDetail() {
        String json = "{\"detail\":\"You do not have access\"}";
        HttpException ex = createHttpException(json, 403);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("You do not have access", msg);
    }

    @Test
    public void testGetErrorMessage_NotFound_NoMessage() {
        HttpException ex = createHttpException("{}", 404);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Resource not found.", msg);
    }

    @Test
    public void testGetErrorMessage_ServerError() {
        HttpException ex = createHttpException("{}", 500);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Server error. Please try again later.", msg);
    }

    @Test
    public void testGetErrorMessage_UnexpectedCode_WithMessage() {
        String json = "{\"message\":\"Custom error\"}";
        HttpException ex = createHttpException(json, 418); // 418 I'm a teapot

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Custom error", msg);
    }

    @Test
    public void testGetErrorMessage_UnexpectedCode_NoMessage() {
        HttpException ex = createHttpException("{}", 418);

        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Unexpected error occurred. Code: 418", msg);
    }

    @Test
    public void testGetErrorMessage_IOException() {
        IOException ioException = new IOException("No connection");

        String msg = ErrorHandler.getErrorMessage(ioException);
        assertEquals("No internet connection. Please check your network.", msg);
    }

    @Test
    public void testGetErrorMessage_OtherThrowable() {
        Throwable throwable = new Throwable("Unknown error");

        String msg = ErrorHandler.getErrorMessage(throwable);
        assertEquals("An unexpected error occurred: Unknown error", msg);
    }

    @Test
    public void testParseHttpError_MalformedJson() {
        String malformedJson = "{invalid json}";
        HttpException ex = createHttpException(malformedJson, 400);
        String msg = ErrorHandler.getErrorMessage(ex);
        assertEquals("Bad Request.", msg);
    }
}