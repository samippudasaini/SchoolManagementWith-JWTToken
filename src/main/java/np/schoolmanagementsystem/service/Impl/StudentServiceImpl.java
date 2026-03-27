package np.schoolmanagementsystem.service.Impl;


import jakarta.servlet.http.HttpSession;
import lombok.*;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.Auth.MyUserDetailsService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Enum.StudentStatus;
import np.schoolmanagementsystem.Mapper.StudentMapper;
import np.schoolmanagementsystem.dto.LoginResponse;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.repository.StudentRepository;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    @Autowired
    private final ClassroomRepository classroomRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;


    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    private Object http;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, ClassroomRepository classroomRepository) {
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }


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

    public masterResponse<StudentDto> studentRegistration(StudentDto studentDto) {
    Student existingStudent = studentRepository.findByEmail(studentDto.getEmail());
    if (existingStudent != null) {
        throw new CustomRuntimeException("Student already exists");
    }
    Optional<Classroom> existingClassroom = classroomRepository.findById(studentDto.getClassroomId());
    if (existingClassroom.isEmpty()) {
        throw new CustomRuntimeException("Classroom not found");
    }


    studentDto.setPassword(encoder.encode(studentDto.getPassword()));

    Student student = StudentMapper.mapToStudent(studentDto);

    Classroom classroom = existingClassroom.get();
    student.setClassroom(classroom);
    student.setGrade(classroom.getGrade());
    student.setRole(Role.STUDENT);

//    student.setClassroom(existingClassroom.get());
//    student.setRole(Role.STUDENT);
    student.setStatus(StudentStatus.PENDING);
    Student savedStudent = studentRepository.save(student);

    // Prepare the response
    masterResponse response = new masterResponse();
    response.setCode(200);
    response.setMessage("Student registration successful");
    response.setStatus(true);
    response.setData(savedStudent);

    return response;
}

    @Override
    public boolean studentLogin(String userName, String password) {
        Student student = studentRepository.findByUserName(userName);

        if (student == null ) {
            throw new CustomRuntimeException("Student does not exist");
        }
        if (!student.getStatus().equals(StudentStatus.APPROVED)) {
            throw new CustomRuntimeException("Student is not approved");
        }

        if (!student.getUserName().equals(userName)) {
            throw new CustomRuntimeException("Invalid userName");
        }

        if (!encoder.matches(password, student.getPassword())) {
            throw new CustomRuntimeException("Invalid password");
        }


        if (student.getRole() == null) {
            throw new CustomRuntimeException("Student role is not assigned");
        }

        return true;
        }

    @Override
    public LoginResponse verify(StudentDto studentDto) {
//        Role role = studentDto.getRole();

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                studentDto.getUserName(),
                                studentDto.getPassword()
                        )
                );

        Student student = studentRepository.findByUserName(studentDto.getUserName());

        if (student == null) {
            throw new CustomRuntimeException("Student not found");
        }

        if (!student.getStatus().equals(StudentStatus.APPROVED)) {
            throw new CustomRuntimeException("Student is not approved");
        }

        if (!encoder.matches(studentDto.getPassword(), student.getPassword())) {
            throw new CustomRuntimeException("Invalid password");
        }

        if (student.getRole() == null) {
            throw new CustomRuntimeException("Student role is not assigned");
        }

        String token = jwtService.generateToken(student.getUserName(), student.getRole());

        StudentDto studentDtoResponse = StudentMapper.mapToStudentDto(student);

        return new LoginResponse(token, studentDtoResponse);
    }



//    for admin control

    @Override
    public String approveStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomRuntimeException("Student not found"));
        student.setStatus(StudentStatus.APPROVED);
        studentRepository.save(student);
        return "Student approved successfully.";
    }

    @Override
    public String rejectStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new CustomRuntimeException("Student not found"));
        studentRepository.delete(student);
        return "Student rejected and removed successfully.";
    }

    @Override
    public List<Student> getPendingStudents() {
        return studentRepository.findByStatus(StudentStatus.PENDING);
    }

    @Override
    public Student getStudentByUserName(String userName) {
        return studentRepository.findByUserName(userName);
    }

//    for fee fetch
@Override
public StudentDto getStudentDtoByUsername(String userName) {
    Student student = studentRepository.findByUserName(userName);
    if (student == null) {
        return null;
    }
    return StudentMapper.mapToStudentDto(student);
}


}

