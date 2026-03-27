package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.FeeDto;
import np.schoolmanagementsystem.dto.PagedResponse;
import np.schoolmanagementsystem.entity.Fee;
import np.schoolmanagementsystem.service.FeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_FEE)
public class FeeController {

    @Autowired
    private final FeeService feeService;

    @Autowired
    public FeeController(FeeService feeService) {
        this.feeService = feeService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ADD_FEE)
    public ResponseEntity<FeeDto> saveFee(@RequestBody FeeDto feeDto) {

        FeeDto savedFee = feeService.saveFee(feeDto);
        return ResponseEntity.ok(savedFee);
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

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//    @GetMapping
//    public ResponseEntity<Page<FeeDto>> getFeesPaginated(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//        return ResponseEntity.ok(feeService.getFeesPaginated(page, size));
//    }

//    @GetMapping
//    public ResponseEntity<PagedResponse<FeeDto>> getFeesPaginated(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Page<FeeDto> feePage = feeService.getFeesPaginated(page, size);
//        return ResponseEntity.ok(
//                new PagedResponse<>(feePage.getContent(), feePage.getTotalElements())
//        );
//    }

//    public static final String GET_ALL_FEE = "/get-all-fee";

    @GetMapping(GET_ALL_FEE)
    public ResponseEntity <List<FeeDto>> getAllFee() {
        return ResponseEntity.ok(feeService.getAllFees());

    }
    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<FeeDto>> getFeesByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(feeService.getFeesByStudentId(studentId));

    }

    @GetMapping("/by-student-email/{email}")
    public ResponseEntity<List<FeeDto>> getFeesByStudentEmail(@PathVariable String email) {
        return ResponseEntity.ok(feeService.getFeesByStudentEmail(email));
    }


}
