package rest.TicketBooking.exceptions.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import rest.TicketBooking.exceptions.SuperAdminDeletionException;
import rest.TicketBooking.model.ApiError;

public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SuperAdminDeletionException.class)
    public ResponseEntity<Object> handleSuperAdminDeletionException(SuperAdminDeletionException ex, WebRequest request) {
        String message = ex.getMessage();
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, message, request.getDescription(false));
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
