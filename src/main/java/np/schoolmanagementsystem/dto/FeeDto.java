package np.schoolmanagementsystem.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import np.schoolmanagementsystem.entity.Student;

import java.util.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class FeeDto {
    private Long feeId;
    private Long totalFee;
    private Long paidFee;
    private Long remainingFee;
    private String feeType;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date paidDate;
    private String description;
    private Long studentId;

    public FeeDto(Long feeId, Long totalFee, Long paidFee,Long remainingFee, String feeType, String description, Long studentId,Date paidDate) {
        this.feeId = feeId;
        this.totalFee = totalFee;
        this.paidFee = paidFee;
        this.remainingFee = remainingFee;
        this.feeType = feeType;
        this.paidDate=paidDate;
        this.description = description;
        this.studentId = studentId;
    }


}
