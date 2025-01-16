package np.schoolmanagementsystem.CustomExcaption;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CustomApplicationException extends RuntimeException {
private final int errorCode;
private final String errorMessage;
}
