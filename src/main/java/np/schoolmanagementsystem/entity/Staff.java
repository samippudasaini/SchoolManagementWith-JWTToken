package np.schoolmanagementsystem.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="Staff_table")
public class Staff {
    @Id
    @Column(name="Staff_id")
    private Long stff_id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="middle_Name")
    private String middleName;

    @Column(name="last_Name")
    private String lastName;

    @Column(name="email_Address")
    private String emailAddress;

    @Column(name="pnone_no")
    private Long pnoneNo;

    @Column(name="position")
    private String position;

    @Column(name="hire_Date")
    private  Date hireDate;

    @Column(name="staff_Salary")
    private Double salary;

}
