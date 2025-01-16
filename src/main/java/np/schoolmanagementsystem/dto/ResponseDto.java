package np.schoolmanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto extends BaseDto {
    private Integer examId;
    private Integer subjectId;
    private Integer classroomId;
    private Date examDate;
}
