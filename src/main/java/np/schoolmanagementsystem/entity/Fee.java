package np.schoolmanagementsystem.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;
    private Long studentId;
    private String studentName;
    private String studentEmail;
    private Long totalFee;
    private Long paidFee;
    private Long remainingFee;
    private String feeType;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate paidDate;

    private String description;

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name="due_date")
    private LocalDate dueDate;
    private Long discountAmount;
    private Long fineAmount;





    @ManyToOne()
//    @JoinColumn(name="studentId")
    private Student student;


//    public Fee(Long feeId, Long totalFee, Long paidFee, Long remainingFee, String feeType, LocalDate paidDate,
//               String description,LocalDate dueDate,Long discountAmount,Long fineAmount, Student student) {
//        this.feeId = feeId;
//        this.totalFee = totalFee;
//        this.paidFee = paidFee;
//        this.remainingFee = remainingFee;
//        this.feeType = feeType;
//        this.paidDate = paidDate;
//        this.description = description;
//        this.dueDate = dueDate;
//        this.discountAmount = discountAmount;
//        this.fineAmount = fineAmount;
//        this.student = student;
//    }


}
