package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
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
    public void deleteStaff(int id) {

    }

    @Override
    public boolean loginStaff(String username, String password) {
        Optional<Staff> staff = staffRepository.findByUserName(username);
        if (!staff.isPresent()) {
            throw new RuntimeException("Staff not found");
        }
        else{
            Staff staff1 = staff.get();
            if (!staff1.getUserName().equals(username)) {
                throw new RuntimeException("Wrong username or password");
            }
            if (!staff1.getPassword().equals(password)) {
                throw new RuntimeException("Wrong password");
            }
        }
        return true;
    }
}
