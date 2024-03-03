package zerobase.weather.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 여기에 에러가 온다면 서버의 문제
    @ExceptionHandler(Exception.class)
    public Exception handleAllException(){
        System.out.println("ERROR from GlobalExceptionHandler");
        return new Exception();
    }
}
