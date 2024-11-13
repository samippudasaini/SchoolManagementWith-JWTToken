package np.schoolmanagementsystem.dto;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClassroomDto {

    private Long id;
    private Long room_No;
    private String grade;
}
