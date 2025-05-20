package np.schoolmanagementsystem.Auth;

import np.schoolmanagementsystem.CustomExcaption.ResourceNotFoundException;
import np.schoolmanagementsystem.entity.Staff;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.StaffRepository;
import np.schoolmanagementsystem.repository.StudentRepository;

import np.schoolmanagementsystem.repository.TeacherRepository;
import np.schoolmanagementsystem.service.StaffService;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StaffRepository staffRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Student student = studentRepository.findByUserName(userName);
        if (student != null) {
            return new StudentPrincipal(student);
        }


        Optional<Teacher> teacher = teacherRepository.findByUserName(userName);
        if (teacher.isPresent()) {
            return new TeacherPrincipal(teacher.get());
        }


        Staff staff = staffRepository.findByUserName(userName);
        if (staff != null) {
            return new StaffPrincipal(staff);
        }
        throw new ResourceNotFoundException("User not found.");

    }
}
