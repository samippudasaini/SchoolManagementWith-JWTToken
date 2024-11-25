package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Mapper.FeeMapper;
import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.repository.FeeRepository;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;


@Data
@Component
public class FeeServiceImpl implements FeeService {
    private final FeeRepository feeRepository;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public FeeDto getFeeById(Long feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee id found"));
        return FeeMapper.mapToFeeDto(fee);
    }

    @Override
    public List<FeeDto> getAllFees() {
        return List.of();
    }

    @Override
    public FeeDto saveFee(FeeDto feeDto) {
        Fee fee = FeeMapper.mapToFee(feeDto);
        fee.setFeeId(feeDto.getFeeId());
        fee.setTotalFee(feeDto.getTotalFee());
        fee.setPaidFee(feeDto.getPaidFee());
        fee.setFeeType(feeDto.getFeeType());
        fee.setDescription(feeDto.getDescription());
        fee.setDueDate(feeDto.getDueDate());
        fee.setFineAmount(feeDto.getFineAmount());
//        fee.setDiscountAmount(feeDto.getDiscountAmount());

//        initialize default discount amount is Zero
        fee.setDiscountAmount(0L);

//        initialize default Fine amount is Zero
        fee.setFineAmount(0L);

        LocalDate paidDate = feeDto.getPaidDate() != null ? feeDto.getPaidDate() : LocalDate.now();

//        Auto calculate remaning fee
        Long remainingFee = feeDto.getTotalFee() - feeDto.getPaidFee();

        if (feeDto.getTotalFee().equals(feeDto.getPaidFee())) {
//            LocalDate paymentDate=feeDto.getPaidDate();
            LocalDate dueDate = feeDto.getDueDate();

            if (dueDate != null && paidDate
                    .isBefore(dueDate.minusMonths(3))) {

//                provide 10% discount
                Long discountAmount = (long) (feeDto.getTotalFee() * 0.1);
                fee.setDiscountAmount(discountAmount);

//                remainingFee -= discountAmount;
            }
        }

//        its for Fine Amount
//        if(feeDto.getTotalFee().equals(feeDto.getPaidFee())){

        LocalDate dueDate = feeDto.getDueDate();

        if (dueDate != null && paidDate
                .isAfter(dueDate)) {
            Long fineAmount = (long) (feeDto.getTotalFee() * 0.1);
            fee.setFineAmount(fineAmount);
        }
//        }


        fee.setRemainingFee(remainingFee);
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


        Fee fee = FeeMapper.mapToFee(feeDto);


        LocalDate paidDate = feeDto.getPaidDate() != null ? feeDto.getPaidDate() : LocalDate.now();

        Long remainingFee = feeDto.getTotalFee() - feeDto.getPaidFee();


        if (feeDto.getTotalFee().equals(feeDto.getPaidFee())) {
//            LocalDate paymentDate=feeDto.getPaidDate();
            LocalDate dueDate = feeDto.getDueDate();

            if (dueDate != null && paidDate
                    .isBefore(dueDate.minusMonths(3))) {

//                provide 10% discount
                Long discountAmount = (long) (feeDto.getTotalFee() * 0.1);
                fee.setDiscountAmount(discountAmount);

//                remainingFee -= discountAmount;
            }
        }

        LocalDate dueDate = feeDto.getDueDate();

        if (dueDate != null && paidDate
                .isAfter(dueDate)) {
            Long fineAmount = (long) (feeDto.getTotalFee() * 0.1);
            fee.setFineAmount(fineAmount);
        }

        fee.setRemainingFee(remainingFee);
        Fee updatedFee = feeRepository.save(fee);
        return FeeMapper.mapToFeeDto(updatedFee);

    }
}
