package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service

public class FeeMapper {

    @Autowired
    private static StudentRepository studentRepository;
    @Autowired
    public FeeMapper(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static Fee mapToFee(FeeDto dto) {
        Fee fee = new Fee();

        fee.setFeeId(dto.getFeeId());
        fee.setTotalFee(dto.getTotalFee());
        fee.setPaidFee(dto.getPaidFee());
        fee.setRemainingFee(dto.getRemainingFee());
        fee.setFeeType(dto.getFeeType());
        fee.setPaidDate(dto.getPaidDate());
        fee.setDescription(dto.getDescription());
        fee.setDueDate(dto.getDueDate());
        fee.setDiscountAmount(dto.getDiscountAmount());
        fee.setFineAmount(dto.getFineAmount());

        fee.setStudentId(dto.getStudentId());
        fee.setStudentName(dto.getStudentName());
        fee.setStudentEmail(dto.getStudentEmail());

        if (dto.getStudentId() != null) {
            studentRepository.findById(dto.getStudentId()).ifPresent(fee::setStudent);
        }
        return fee;
    }

    public static FeeDto mapToFeeDto(Fee fee) {
        FeeDto dto = new FeeDto();
        dto.setFeeId(fee.getFeeId());
        dto.setStudentId(fee.getStudentId());
        dto.setTotalFee(fee.getTotalFee());
        dto.setPaidFee(fee.getPaidFee());
        dto.setRemainingFee(fee.getRemainingFee());
        dto.setDiscountAmount(fee.getDiscountAmount());
        dto.setFineAmount(fee.getFineAmount());
        dto.setDueDate(fee.getDueDate());
        dto.setPaidDate(fee.getPaidDate());
        dto.setStudentEmail(fee.getStudentEmail());

        // Fetch student name (pseudo)
        if (fee.getStudentId() != null) {
            Optional<Student> studentOpt = studentRepository.findById(fee.getStudentId());
            studentOpt.ifPresent(student -> dto.setStudentName(student.getFullName()));
            studentOpt.ifPresent(student -> dto.setStudentEmail(student.getEmail()));


        }
        return dto;
    }


}


