package ru.malichenko.market.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.logging.Logger;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {
//    private  static final Logger log1 = LoggerFactory.getLogger(ExceptionControllerAdvice.class);
    @ExceptionHandler
    public ResponseEntity<?> handleResourceCreationException(ResourceCreationException e){
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
        log.error(e.getMessage());
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
