package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fee")
public class FeeController {

    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }

    @PostMapping("/savefee")
    public ResponseEntity<FeeDto> saveFee(@RequestBody FeeDto feeDto) {

        return new ResponseEntity<>(feeService.saveFee(feeDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{feeId}")

    public ResponseEntity<FeeDto> deleteFee(@PathVariable Long feeId) {
        return new ResponseEntity<>(feeService.deleteFee(feeId),HttpStatus.NO_CONTENT);
    }

    @PutMapping("/feeupdate/{feeId}")
    public ResponseEntity<FeeDto> updateFee(@PathVariable Long feeId,@RequestBody FeeDto feeDto) {
        FeeDto updatefeeDto=feeService.updateFee(feeId,feeDto);
        return new ResponseEntity<>(updatefeeDto,HttpStatus.OK);
    }


}
