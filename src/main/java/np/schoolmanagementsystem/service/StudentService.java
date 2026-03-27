package np.schoolmanagementsystem.service;

import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.LoginResponse;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
@Service

public interface StudentService {
    StudentDto updateStudent(StudentDto studentDto, Long studentId);

    StudentDto getStudentById(Long id);

    StudentDto deleteStudentById(Long id, HttpSession session);

    List<StudentDto> getAllStudents();

    masterResponse<?> studentRegistration(StudentDto studentDto);

    boolean studentLogin(String userName, String password);

    LoginResponse verify(StudentDto studentDto);




//    for pending student
    String approveStudent(Long studentId);
    String rejectStudent(Long studentId);

    List<Student> getPendingStudents();
    Student getStudentByUserName(String userName);

    StudentDto getStudentDtoByUsername(String userName);
}
