package np.schoolmanagementsystem.controller;


import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping("/register")
    public ResponseEntity<StaffDto> registerStaff(@RequestBody StaffDto staffDto) {
        return new ResponseEntity<>(staffService.registerStaff(staffDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginStaff(@RequestBody StaffDto staffDto, HttpSession session) {
        String userName = staffDto.getUserName();
        String password = staffDto.getPassword();
//
//        session.setAttribute("id",staffDto.getStaffId());
//        session.setAttribute("role",staffDto.getRole());

        if (staffService.loginStaff(userName, password, session)) {

            return ResponseEntity.ok("Login Successful as ADMIN.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login failed");
        }
    }

    @DeleteMapping("/delete/{staffId}")
    public ResponseEntity<StaffDto> deleteStaff(@PathVariable Long staffId) {
        StaffDto staffDto = staffService.deleteStaff(staffId);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);

    }
}
