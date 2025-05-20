package np.schoolmanagementsystem.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClassroomDto {
    private Long classroomId;
    private Long roomNo;
    private String grade;

    public ClassroomDto(Long classroomId, String grade) {
        this.classroomId = classroomId;
        this.grade = grade;
    }

}
