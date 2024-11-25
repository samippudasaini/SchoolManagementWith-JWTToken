package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StaffMapper;
import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.repository.StaffRepository;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

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
//        set roll
//        staff.setRole(Role.ADMIN);
        Staff savedStaff = staffRepository.save(staff);
        return StaffMapper.mapToStaffDto(savedStaff);
    }

    @Override
    public List<StaffDto> getAllStaff() {
        return List.of();
    }

    @Override
    public StaffDto getStaffById(int id) {
        return null;
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
            if (!staff.getPassword().equals(password)) {
                throw new CustomRuntimeException("Wrong password");
            }
        }
        return true;
    }

    @Override
    public String verify(StaffDto staffDto) {

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(staffDto
                        .getUserName(), staffDto.getPassword()));
        if( authentication.isAuthenticated()){
            return jwtService.generateToken(staffDto.getUserName());
        }

return "fail";
    }
}
