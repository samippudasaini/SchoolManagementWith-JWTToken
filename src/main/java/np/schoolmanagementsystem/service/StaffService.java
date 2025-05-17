package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.dto.masterResponse;

import java.util.List;

public interface StaffService {
    StaffDto registerStaff(StaffDto staffDto);

    List<StaffDto> getAllStaff();

    public StaffDto getStaffById(Long id);

    void updateStaff(StaffDto staffDto);

    StaffDto deleteStaff(Long staffId);

    boolean loginStaff(String username, String password);

    masterResponse verify(StaffDto staffDto);

}
