package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;


public class FeeMapper {
    public static Fee mapToFee(FeeDto feeDto) {
        Fee fee = new Fee(
        feeDto.getFeeId(),
        feeDto.getTotalFee(),
        feeDto.getPaidFee(),
        feeDto.getRemainingFee(),
        feeDto.getFeeType(),
        feeDto.getPaidDate(),
        feeDto.getDescription(),
        feeDto.getStudentId() != null ? new Student(feeDto.getStudentId()) : null
        );
        return fee;
    }
    public static FeeDto mapToFeeDto(Fee fee){
        FeeDto feeDto = new FeeDto(
                fee.getFeeId(),
                fee.getTotalFee(),
                fee.getPaidFee(),
                fee.getRemainingFee(),
                fee.getFeeType(),
                fee.getDescription(),
                fee.getStudent() != null ? fee.getStudent().getStudentId() : null,
                fee.getPaidDate()
                );
        return feeDto;
    }

}
