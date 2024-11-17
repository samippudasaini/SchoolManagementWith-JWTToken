package np.schoolmanagementsystem.service.Impl;

import jakarta.servlet.http.HttpSession;
import lombok.Data;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.StudentMapper;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.repository.StudentRepository;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data


public class StudentServiceImpl implements StudentService {

    private final ClassroomRepository classroomRepository;
    private StudentRepository studentRepository;

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
    public StudentDto updateStudent(StudentDto studentDto, Long studentId, HttpSession session) {

        Student student = StudentMapper.mapToStudent(studentDto);
        Student updateStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);

    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public StudentDto deleteStudentById(Long id, HttpSession session) {

        String rollName = (String) session.getAttribute("Role");
        if (Role.ADMIN.name().equals(rollName)) {
            Student student = studentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            studentRepository.delete(student);

            return StudentMapper.mapToStudentDto(student);
        } else {
            throw new RuntimeException("Only Admin can delete student.");
        }
    }

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> StudentMapper.mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto studentRegistration(StudentDto studentDto) {

        Optional<Student> existingStudent = studentRepository.findByEmail(studentDto.getEmail());
        if (existingStudent.isPresent()) {
            throw new RuntimeException("Student already exists");
        }
//        Classroom classroom = classroomRepository.findById(studentDto.getclassroomId())
//                .orElseThrow(()->new RuntimeException("classroom not found"));


        Student student = StudentMapper.mapToStudent(studentDto);
        student.setRole(Role.STUDENT);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);

    }

    @Override
    public boolean studentLogin(String userName, String password) {
        Optional<Student> StudentOptional = studentRepository
                .findByUserName(userName);

        if (StudentOptional.isEmpty()) {
            throw new RuntimeException("Student not found ");
        } else {
            Student student = StudentOptional.get();
            if (!student.getUserName().equals(userName)) {
                throw new RuntimeException("Invalid userName");
            }


            if (!student.getPassword().equals(password)) {
                throw new RuntimeException("Invalid password");
            }
//            return StudentMapper.mapToStudentDto(student);
            return true;
        }

    }
}
