package np.schoolmanagementsystem.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectDto {
//    Long subjectId;
String subjectId;
    String subjectName;

    public SubjectDto(String subjectName, String subjectId) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
    }
}
