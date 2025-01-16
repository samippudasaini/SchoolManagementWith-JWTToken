package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_FEE)
public class FeeController {

    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ADD_FEE)
    public ResponseEntity<String> saveFee(@RequestBody FeeDto feeDto) {

        return ResponseEntity.ok("Fee saved successfully");
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_FEE_BY_ID)

    public ResponseEntity<FeeDto> deleteFee(@PathVariable Long feeId) {
        return new ResponseEntity<>(feeService.deleteFee(feeId), HttpStatus.NO_CONTENT);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(FEE_UPDATE)
    public ResponseEntity<FeeDto> updateFee(@PathVariable Long feeId, @RequestBody FeeDto feeDto) {
        FeeDto updatefeeDto = feeService.updateFee(feeId, feeDto);
        return new ResponseEntity<>(updatefeeDto, HttpStatus.OK);
    }
}
