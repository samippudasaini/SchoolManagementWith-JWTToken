package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Mapper.FeeMapper;
import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.FeeRepository;
import np.schoolmanagementsystem.repository.StudentRepository;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;



@Data
@Component
public class FeeServiceImpl implements FeeService {

    @Autowired
    private final FeeRepository feeRepository;

    @Autowired
    private StudentRepository studentRepository;

//    public FeeServiceImpl(FeeRepository feeRepository) {
//        this.feeRepository = feeRepository;
//    }

    @Autowired
    private FeeMapper feeMapper;

    @Override
    public FeeDto getFeeById(Long feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee id found"));
        return FeeMapper.mapToFeeDto(fee);
    }

    @Override
    public List<FeeDto> getAllFees() {
        List<Fee> fees = feeRepository.findAll();
        return fees.stream()
                .map(FeeMapper::mapToFeeDto)
                .collect(Collectors.toList());
    }
//public List<FeeDto> getAllFees() {
//    List<Fee> fees = feeRepository.findAll();
//
//    return fees.stream().map(fee -> {
//        FeeDto dto = new FeeDto();
//        dto.setFeeId(fee.getFeeId());
//        dto.setTotalFee(fee.getTotalFee());
//        dto.setPaidFee(fee.getPaidFee());
//        dto.setRemainingFee(fee.getRemainingFee());
//        dto.setFeeType(fee.getFeeType());
//        dto.setPaidDate(fee.getPaidDate());
//        dto.setDescription(fee.getDescription());
//        dto.setDueDate(fee.getDueDate());
//        dto.setDiscountAmount(fee.getDiscountAmount());
//        dto.setFineAmount(fee.getFineAmount());
//
//        // ✅ Set student details from the Student object
//        if (fee.getStudent() != null) {
//            dto.setStudentId(fee.getStudent().getStudentId());
//            dto.setStudentName(fee.getStudent().getUserName()); // or getFullName()
//            dto.setStudentEmail(fee.getStudent().getEmail());
//        }
//
//        return dto;
//    }).collect(Collectors.toList());
//}

    @Override
    public FeeDto saveFee(FeeDto feeDto) {
        Fee fee = FeeMapper.mapToFee(feeDto);

        applyFeeCalculations(fee, feeDto);

        Fee savedFee = feeRepository.save(fee);
        return FeeMapper.mapToFeeDto(savedFee);
    }




    @Override
    public FeeDto deleteFee(Long feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("fee id not found"));
        feeRepository.delete(fee);
        return FeeMapper.mapToFeeDto(fee);
    }

    @Override
    public FeeDto updateFee(Long feeId, FeeDto feeDto) {
        Fee existing = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee ID not found"));

        Fee fee = FeeMapper.mapToFee(feeDto);

        // Ensure ID stays the same for update
        fee.setFeeId(feeId);

        applyFeeCalculations(fee, feeDto);

        Fee updatedFee = feeRepository.save(fee);
        return FeeMapper.mapToFeeDto(updatedFee);
    }

    // ✅ DRY: Move logic to reusable private method
    private void applyFeeCalculations(Fee fee, FeeDto feeDto) {
        LocalDate paidDate = feeDto.getPaidDate() != null ? feeDto.getPaidDate() : LocalDate.now();
        LocalDate dueDate = feeDto.getDueDate();

        fee.setPaidDate(paidDate);
        fee.setRemainingFee(feeDto.getTotalFee() - feeDto.getPaidFee());

        // Default amounts
        fee.setDiscountAmount(0L);
        fee.setFineAmount(0L);

        // ✅ Early Payment Discount
        if (feeDto.getTotalFee().equals(feeDto.getPaidFee())
                && dueDate != null
                && paidDate.isBefore(dueDate.minusMonths(3))) {

            Long discountAmount = (long) (feeDto.getTotalFee() * 0.1);
            fee.setDiscountAmount(discountAmount);
        }

        // ✅ Late Payment Fine
        if (dueDate != null && paidDate.isAfter(dueDate)) {
            Long fineAmount = (long) (feeDto.getTotalFee() * 0.1);
            fee.setFineAmount(fineAmount);
        }
    }

    @Override
    public Page<FeeDto> getFeesPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return feeRepository.findAll(pageable)
                .map(fee -> {
                    FeeDto dto = FeeMapper.mapToFeeDto(fee);
                    studentRepository.findById(fee.getStudentId())
                            .ifPresent(student -> dto.setStudentName(student.getFullName()));
                    return dto;
                });
    }

    @Override
    public List<FeeDto> getFeesByStudentId(Long studentId) {
        List<Fee> fees = feeRepository.findByStudent_StudentId(studentId);
        return fees.stream().map(FeeMapper::mapToFeeDto).collect(Collectors.toList());
    }



    public List<FeeDto> getFeesByStudentEmail(String email) {
        List<Fee> fees = feeRepository.findByStudent_Email(email);
        return fees.stream().map(FeeMapper::mapToFeeDto).collect(Collectors.toList());
    }


}
