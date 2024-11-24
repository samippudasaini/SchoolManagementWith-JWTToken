package np.schoolmanagementsystem.CustomExcaption;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  {

  @ExceptionHandler(ResourceNotFoundException.class)

  public ResponseEntity<Map<String,Object>> resourceNotFound(ResourceNotFoundException ex){
    Map<String,Object> response = new HashMap<>();

    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.NOT_FOUND.value());
    response.put("error","Not Found");
    response.put("message",ex.getMessage());

    return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);

  }
  @ExceptionHandler(CustomRuntimeException.class)

  public ResponseEntity<Map<String,Object>> customException(CustomRuntimeException ex){
    Map<String,Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
    response.put("message",ex.getMessage());
    return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(CustomIlligalArgumentException.class)
  public ResponseEntity<Map<String,Object>> customException(CustomIlligalArgumentException ex){
    Map<String,Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error","Bad Request");
    return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
  }
//  @ExceptionHandler(Exception.class)
//  public ResponseEntity<Map<String,Object>> exception(Exception ex){
//    Map<String,Object> response = new HashMap<>();
//    response.put("timestamp", LocalDateTime.now());
//    response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
//    response.put("error","Internal Server Error");
//    response.put("messsage",ex.getMessage());
//    return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
//
//  }
}
