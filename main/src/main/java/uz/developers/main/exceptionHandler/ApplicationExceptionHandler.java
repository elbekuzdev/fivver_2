package uz.developers.main.exceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.developers.main.dto.ResponseDto;

import javax.validation.UnexpectedTypeException;

@ControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, InvalidFormatException.class, UnexpectedTypeException.class, BadCredentialsException.class})
    public ResponseDto methodValidException(Exception e){
        return new ResponseDto(-1, e.getMessage(), null);
    }
}