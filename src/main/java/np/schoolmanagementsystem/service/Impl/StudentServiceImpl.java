package np.schoolmanagementsystem.service.Impl;//package np.schoolmanagementsystem.service.Impl;
//
//public class StudentServiceImpl {
//}

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.Auth.MyUserDetailsService;
import np.schoolmanagementsystem.Auth.StudentPrincipal;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StudentMapper;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.repository.StudentRepository;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data




public class StudentServiceImpl implements StudentService {
    private final ClassroomRepository classroomRepository;
    private StudentRepository studentRepository;

@Autowired
  private JWTService jwtService;

@Autowired
AuthenticationManager authenticationManager;

@Autowired
private MyUserDetailsService  myUserDetailsService;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }


//    @Override
//    public StudentDto addStudent(StudentDto studentDto) {
//            Student student = StudentMapper.mapToStudent(studentDto);
//            Student savedStudent = studentRepository.save(student);
//            return StudentMapper.mapToStudentDto(savedStudent);
//    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long studentId) {

        Student student = StudentMapper.mapToStudent(studentDto);
        Student updateStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);

    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomRuntimeException("Student not found"));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public StudentDto deleteStudentById(Long id, HttpSession session) {

            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new CustomRuntimeException("Student not found"));
            studentRepository.delete(student);

            return StudentMapper.mapToStudentDto(student);

    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }



    public StudentDto studentRegistration(StudentDto studentDto) {

        Student existingStudent = studentRepository.findByEmail(studentDto.getEmail());
        if (existingStudent !=null) {
            throw new CustomRuntimeException("Student already exists");
        }
        studentDto.setPassword(encoder.encode(studentDto.getPassword()));

        Student student = StudentMapper.mapToStudent(studentDto);
        student.setRole(Role.STUDENT);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);



    }

    @Override
    public boolean studentLogin(String userName, String password) {
        Student StudentOptional = studentRepository
                .findByUserName(userName);

        if (StudentOptional==null) {
            throw new CustomRuntimeException("Student not found ");
        } else {
            Student student = StudentOptional.get();
            if (!student.getUserName().equals(userName)) {
                throw new CustomRuntimeException("Invalid userName");
            }


            if (!student.getPassword().equals(password)) {
                throw new CustomRuntimeException("Invalid password");
            }
            return true;
        }

    }

    @Override
    public String verify(StudentDto studentDto) {


//       String Entitytype=null;
//
//        Role role = studentDto.getRole();


//        MyUserDetailsService userDetailsService = (MyUserDetailsService) myUserDetailsService;

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(studentDto
                        .getUserName(), studentDto.getPassword()));
       if( authentication.isAuthenticated()){
//           if(userDetailsService instanceof StudentPrincipal)
//           {
//               Entitytype="Student";
//
//           }

            return jwtService.generateToken(studentDto.getUserName());
            }

       return "fail";
    }

}

