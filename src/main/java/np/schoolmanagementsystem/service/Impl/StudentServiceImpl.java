package np.schoolmanagementsystem.service.Impl;


import jakarta.servlet.http.HttpSession;
import lombok.*;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.Auth.MyUserDetailsService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StudentMapper;
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
        Role role = studentDto.getRole();

        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(studentDto
                        .getUserName(), studentDto.getPassword()));
        if (authentication.isAuthenticated()) {

            return jwtService.generateToken(studentDto.getUserName(), role);
        }

        return "fail";
    }
}

