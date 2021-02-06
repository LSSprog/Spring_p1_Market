package hw.spring.market.exeptionsHandling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    public ResponseEntity<?> handleResourceNotFoundExc (ResourceNotFoundException e) {
        log.error(e.getMessage());
        MarketError error = new MarketError(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
