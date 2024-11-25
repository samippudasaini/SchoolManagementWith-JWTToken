package np.schoolmanagementsystem.CustomAdmin;

import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class SeedingData implements CommandLineRunner {
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    private StaffRepository staffRepository;
    @Override
    public void run(String... args) throws Exception {

        Staff exestingstaff = staffRepository.findByRole(Role.ADMIN);
        if (exestingstaff == null) {
            Staff staff = new Staff();
            staff.setFirstName("samip");
            staff.setLastName("pudasaini");
            staff.setEmail("samip@gmail.com.com");
            staff.setUserName("samip22");
            staff.setPhone(9808246445L);
            staff.setPassword(encoder.encode("@@98082"));
            staff.setRole(Role.ADMIN);
            staffRepository.save(staff);
        }
    }
}
