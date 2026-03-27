package np.schoolmanagementsystem.controller;


import jakarta.annotation.security.PermitAll;
import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.repository.StaffRepository;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowCredentials = "true")
@RequestMapping(BASE_URL_STAFF)
public class StaffController {


    private final StaffService staffService;
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @PreAuthorize("hasRole('ADMIN')")
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
        return  ResponseEntity.status(masterResponse.getCode()).body(masterResponse);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_STAFF_BY_ID)
    public ResponseEntity<StaffDto> deleteStaff(@PathVariable Long staffId) {
        StaffDto staffDto = staffService.deleteStaff(staffId);
        return new ResponseEntity<>(staffDto, HttpStatus.OK);

    }
    @GetMapping("/get-staff/{staffId}")
    public StaffDto getStaffById(@PathVariable Long staffId) {
       return staffService.getStaffById(staffId);
    }

//    public ResponseEntity<Object> fetchUsers() {
//        List<Users> users = usersRepo.fetchUsers();
//        users.forEach(
//                u -> {
//                    UserProfile userProfile = userProfileRepo.findByUserId(u.getUserid());
//                    if (userProfile != null && userProfile.getUserImage() != null) {
//                        u.setProfileImage(Base64.getDecoder().decode(userProfile.getUserImage()));
//                    }
//                }
//        );
//        return ResponseEntity.ok(users);

    @GetMapping
    public ResponseEntity<Object> getAllStaff() {
        List<Staff> staffs = staffRepository.fetchAllStaff();
        return  ResponseEntity.ok(staffs);

    }


    //  for check
    @GetMapping("/test-password")
    @PermitAll
    public String testPasswordEndpoint() {
        String rawPassword = "98082";
        String encodedPassword = "$2a$12$vNBjP5CixOAEAc/m3XPtreAQbjvItsnMF5VEVj5YaDhL9JLlHDdqW";

        boolean matches = passwordEncoder.matches(rawPassword, encodedPassword);
        System.out.println("Password matches? " + matches);
        return "Check console for result: " + matches;
    }


}
