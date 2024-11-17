package np.schoolmanagementsystem.service.Impl;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StaffMapper;
import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.repository.StaffRepository;
import np.schoolmanagementsystem.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public StaffDto registerStaff(StaffDto staffDto) {

        Optional<Staff> existingstaff = staffRepository.findByEmail(staffDto.getEmail());
        if (existingstaff.isPresent()) {
            throw new RuntimeException("Staff already exists");
        }
        Staff staff = StaffMapper.mapToStaff(staffDto);
//        set roll
        staff.setRole(Role.ADMIN);
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
                .orElseThrow(() -> new RuntimeException("Staff not found"));
        staffRepository.delete(staff);

        return StaffMapper.mapToStaffDto(staff);
    }

    @Override
    public boolean loginStaff(String username, String password, HttpSession session) {
        Optional<Staff> staff = staffRepository.findByUserName(username);
        Staff staff1 = staff.get();
        if (!staff.isPresent()) {
            throw new RuntimeException("Staff not found");
        } else {
            if (!staff1.getUserName().equals(username)) {
                throw new RuntimeException("Wrong username or password");
            }
            if (!staff1.getPassword().equals(password)) {
                throw new RuntimeException("Wrong password");
            }
        }

        session.setAttribute("Id", staff1.getStaffId());
        session.setAttribute("Role", staff1.getRole().name());
        return true;
    }
}
