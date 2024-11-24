package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Mapper.FeeMapper;
import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.repository.FeeRepository;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.stereotype.Component;
import java.util.List;


@Data
@Component
public class FeeServiceImpl implements FeeService {
    private final FeeRepository  feeRepository ;

    public FeeServiceImpl(FeeRepository feeRepository) {
        this.feeRepository = feeRepository;
    }

    @Override
    public FeeDto getFeeById(Long feeId) {
        Fee fee =feeRepository.findById(feeId)
                .orElseThrow(() -> new RuntimeException("Fee id found"));
        return FeeMapper.mapToFeeDto(fee);
    }

    @Override
    public List<FeeDto> getAllFees() {
        return List.of();
    }

    @Override
    public FeeDto saveFee(FeeDto feeDto) {
        Fee fee =FeeMapper.mapToFee(feeDto);
        fee.setFeeId(feeDto.getFeeId());
        fee.setTotalFee(feeDto.getTotalFee());
        fee.setPaidFee(feeDto.getPaidFee());
        fee.setFeeType(feeDto.getFeeType());
        fee.setDescription(feeDto.getDescription());
        Long remainingFee= feeDto.getTotalFee()-feeDto.getPaidFee();
        fee.setRemainingFee(remainingFee);
        Fee savedFee=feeRepository.save(fee) ;
        return FeeMapper.mapToFeeDto(savedFee);
    }

    @Override
    public FeeDto deleteFee(Long feeId) {
        Fee fee = feeRepository.findById(feeId)
                .orElseThrow(()->new RuntimeException("fee id not found"));
        feeRepository.delete(fee);
        return FeeMapper.mapToFeeDto(fee);
    }

    @Override
    public FeeDto updateFee(Long feeId, FeeDto feeDto) {

        Fee fee =FeeMapper.mapToFee(feeDto);
        Long remainingFee= feeDto.getTotalFee()-feeDto.getPaidFee();
        fee.setRemainingFee(remainingFee);
        Fee updatedFee=feeRepository.save(fee) ;
        return FeeMapper.mapToFeeDto(updatedFee);

    }
}
