package np.schoolmanagementsystem.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomDto {

    private Long classroomId;
    private Long roomNo;
    private String grade;
}
