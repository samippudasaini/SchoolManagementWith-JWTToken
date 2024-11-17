package np.schoolmanagementsystem.service;

import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.StaffDto;

import java.util.List;

public interface StaffService {
    StaffDto registerStaff(StaffDto staffDto);

    List<StaffDto> getAllStaff();

    StaffDto getStaffById(int id);

    void updateStaff(StaffDto staffDto);

    StaffDto deleteStaff(Long staffId);

    boolean loginStaff(String username, String password, HttpSession session);

}
