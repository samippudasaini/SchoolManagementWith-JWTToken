package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StaffMapper;
import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.repository.StaffRepository;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import np.schoolmanagementsystem.Enum.Role;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }
    @Override
    public StaffDto registerStaff(StaffDto staffDto) {

        Optional<Staff> existingstaff = staffRepository.findByEmail(staffDto.getEmail());
        if (existingstaff.isPresent()) {
            throw new CustomRuntimeException("Staff already exists");
        }
        Staff staff = StaffMapper.mapToStaff(staffDto);
        Staff savedStaff = staffRepository.save(staff);
        return StaffMapper.mapToStaffDto(savedStaff);
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return null;
    }

    public StaffDto getStaffById(Long id) {
        Staff staff=staffRepository.findById(id).orElseThrow(()->new RuntimeException("User not found"));
        return StaffMapper.mapToStaffDto(staff);
    }

    @Override
    public void updateStaff(StaffDto staffDto) {

    }

    @Override
    public StaffDto deleteStaff(Long staffId) {
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new CustomRuntimeException("Staff not found"));
        staffRepository.delete(staff);

        return StaffMapper.mapToStaffDto(staff);
    }

    @Override
    public boolean loginStaff(String userName, String password) {
     Staff staff = staffRepository.findByUserName(userName);
//        Staff staff1 = staff.get();
        if (staff == null) {
            throw new CustomRuntimeException("Staff not found");
        } else {
            if (!staff.getUserName().equals(userName)) {
                throw new CustomRuntimeException("Wrong username ");
            }
//            if (!staff.getPassword().equals(password)) {
//                throw new CustomRuntimeException("Wrong password");
//            }

//            change from gpt
            if (!passwordEncoder.matches(password, staff.getPassword())) {
                throw new CustomRuntimeException("Wrong password");
            }
        }
        return true;
    }

    @Override
    public masterResponse verify(StaffDto staffDto) {
        masterResponse response = new masterResponse();

        // Authenticate credentials
        try {
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                            staffDto.getUserName(), staffDto.getPassword()));

            if (authentication.isAuthenticated()) {
                // Fetch actual Staff to get the role
                Staff staff = staffRepository.findByUserName(staffDto.getUserName());
                if (staff == null) {
                    throw new CustomRuntimeException("Staff not found");
                }

                Role role = staff.getRole();  // Now role won't be null
                String token = jwtService.generateToken(staff.getUserName(), role);

                response.setCode(200);
                response.setMessage("Success");
                response.setStatus(true);
                response.setData(token);
            } else {
                response.setCode(401);
                response.setMessage("Failure");
                response.setStatus(false);
                response.setData(null);
            }
        } catch (Exception e) {
            response.setCode(401);
            response.setMessage("Invalid credentials: " + e.getMessage());
            response.setStatus(false);
            response.setData(null);
        }
        return response;
    }

}
