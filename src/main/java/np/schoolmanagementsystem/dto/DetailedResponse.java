package np.schoolmanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetailedResponse extends BaseDto {
    private Integer id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date examDate;
    private String examType;
    private Integer fullMarks;
    private Integer passMark;
    private Integer subjectId;
    private Integer classroomId;
    private String base64Routine;

}
