package np.schoolmanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import np.schoolmanagementsystem.entity.Student;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeeDto {
    private Long feeId;
    private Long totalFee;
    private Long paidFee;
    private Long remainingFee;
    private String feeType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate paidDate;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")

    private LocalDate dueDate;
    private Long discountAmount;
    private Long fineAmount;
    private Long studentId;

    public FeeDto(Long feeId, Long totalFee, Long paidFee,Long remainingFee, String feeType, String description,
                 LocalDate dueDate, Long discountAmount,  Long fineAmount, Long studentId,LocalDate paidDate) {
        this.feeId = feeId;
        this.totalFee = totalFee;
        this.paidFee = paidFee;
        this.remainingFee = remainingFee;
        this.feeType = feeType;
        this.paidDate=paidDate;
        this.description = description;
        this.dueDate=dueDate;
        this.discountAmount=discountAmount;
        this.fineAmount=fineAmount;
        this.studentId = studentId;
    }
}
