package np.schoolmanagementsystem.CustomExcaption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MasterCustomException {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Map<String, Object>> exception(Exception ex) {
//        Map<String, Object> response = new HashMap<>();
//        response.put("timestamp", LocalDateTime.now());
//        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//        response.put("error", "Internal Server Error");
//        response.put("messsage", ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//
//    }
}
