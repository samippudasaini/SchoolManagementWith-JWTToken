package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.StaffDto;
import np.schoolmanagementsystem.entity.Staff;

public class StaffMapper {
    public static Staff mapToStaff(StaffDto staffDto) {
        Staff staff = new Staff(
                staffDto.getStaffId(),
                staffDto.getFirstName(),
                staffDto.getMiddleName(),
                staffDto.getLastName(),
                staffDto.getEmail(),
                staffDto.getPhone(),
                staffDto.getPosition(),
                staffDto.getHireDate(),
                staffDto.getSalary(),
                staffDto.getUserName(),
                staffDto.getPassword(),
                staffDto.getRole()
        );
        return staff;
    }

    public static StaffDto mapToStaffDto(Staff staff) {
        StaffDto staffDto = new StaffDto(
                staff.getStaffId(),
                staff.getFirstName(),
                staff.getMiddleName(),
                staff.getLastName(),
                staff.getEmail(),
                staff.getPhone(),
                staff.getPosition(),
                staff.getHireDate(),
                staff.getSalary(),
                staff.getUserName(),
                staff.getPassword(),
                staff.getRole()
        );
        return staffDto;
    }
}
