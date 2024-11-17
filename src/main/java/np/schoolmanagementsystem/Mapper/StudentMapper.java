package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;

import java.util.ArrayList;


public class StudentMapper {
    public static Student mapToStudent(StudentDto studentDto) {
        Student student = new Student(
                studentDto.getStudentId(),
                studentDto.getFirstName(),
                studentDto.getMiddleName(),
                studentDto.getLastName(),
                studentDto.getEmail(),
                studentDto.getPhone_no(),
                studentDto.getGender(),
                studentDto.getAddress(),
                studentDto.getEnrollmentDate(),
                studentDto.getGrade(),
                studentDto.getParentContact(),
                studentDto.getPassword(),
                studentDto.getUserName(),
                studentDto.getRole()
        );
        return student;
    }

    public static StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto(
                student.getStudentId(),
                student.getFirstName(),
                student.getMiddleName(),
                student.getLastName(),
                student.getEmail(),
                student.getPhone_no(),
                student.getGender(),
                student.getAddress(),
                student.getEnrollmentDate(),
                student.getGrade(),
                student.getPassword(),
                student.getUserName(),
                student.getParentContact(),
                student.getRole()

        );
        return studentDto;
    }

}
