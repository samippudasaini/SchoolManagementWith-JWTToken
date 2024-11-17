package np.schoolmanagementsystem.service;

import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Student;
import org.springframework.stereotype.Service;


import java.util.List;
@Service

public interface StudentService {
    //    StudentDto addStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto, Long studentId, HttpSession session);

    StudentDto getStudentById(Long id);

    StudentDto deleteStudentById(Long id, HttpSession session);

    List<StudentDto> getAllStudents();

    StudentDto studentRegistration(StudentDto studentDto);

    boolean studentLogin(String userName, String password);

}
