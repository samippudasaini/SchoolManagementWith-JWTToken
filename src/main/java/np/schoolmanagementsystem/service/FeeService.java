package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.FeeDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FeeService {

    FeeDto getFeeById(Long feeId);
    List<FeeDto> getAllFees();
    FeeDto saveFee(FeeDto feeDto);
    FeeDto deleteFee(Long feeId);
    FeeDto updateFee(Long feeId, FeeDto feeDto);

}
