package np.schoolmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

//@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;

    private Long totalFee;
    private Long paidFee;
    private Long remainingFee;
    private String feeType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date paidDate;

    private String description;

    @ManyToOne()
//    @JoinColumn(name="studentId")
    private Student student;


    public Fee(Long feeId, Long totalFee, Long paidFee, Long remainingFee, String feeType, Date paidDate,
               String description, Student student) {
        this.feeId = feeId;
        this.totalFee = totalFee;
        this.paidFee = paidFee;
        this.remainingFee = remainingFee;
        this.feeType = feeType;
        this.paidDate = paidDate;
        this.description = description;
        this.student = student;
    }
}
