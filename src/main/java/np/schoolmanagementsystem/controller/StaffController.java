package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController

@RequestMapping(BASE_URL_STAFF)
public class StaffController {

    @Autowired
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ADD_STAFF)
    public ResponseEntity<StaffDto> registerStaff(@RequestBody StaffDto staffDto) {
        return new ResponseEntity<>(staffService.registerStaff(staffDto), HttpStatus.CREATED);
    }


    @PostMapping(LOGIN_STAFF)
    public ResponseEntity<masterResponse> loginStaff(@RequestBody StaffDto staffDto)
    {
//        String userName = staffDto.getUserName();
//        String password = staffDto.getPassword();
//        return staffService. verify(staffDto);
        masterResponse masterResponse = staffService.verify(staffDto);
        return new ResponseEntity<>(masterResponse, HttpStatus.OK);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_STAFF_BY_ID)
    public ResponseEntity<StaffDto> deleteStaff(@PathVariable Long staffId) {
        StaffDto staffDto = staffService.deleteStaff(staffId);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);

    }
}
