package np.schoolmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Enum.Role;

@Entity
@Table(name = "pending_teacher")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingTeacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private Long phone;
    private String userName;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
