package np.schoolmanagementsystem.dto;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto extends BaseDto {
    private Integer id;
    private Integer examId;
    private Integer subjectId;
    private Integer classroomId;
    private Date examDate;
    private Integer passMark;
    private String routine;
    private String base64Routine;
    private Integer fullMarks;
    private String examType;

}
