package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Student;


import java.util.List;

public interface StudentService {
//    StudentDto addStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto,Long studentId);
    StudentDto getStudentById(Long id);
    StudentDto deleteStudentById(Long id);
    List<StudentDto> getAllStudents();
    StudentDto studentRegistration(StudentDto studentDto);
    boolean  studentLogin(String userName, String password);

}
