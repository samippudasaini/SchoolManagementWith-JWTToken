package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;

import java.util.ArrayList;


public class StudentMapper {
    public static Student mapToStudent(StudentDto studentDto) {

        Classroom classroom = new Classroom();

        Student student = new Student();
                student.setFirstName(studentDto.getFirstName());
                student.setMiddleName(studentDto.getMiddleName());
                student.setLastName(studentDto.getLastName());
                student.setEmail(studentDto.getEmail());
                student.setPhone_no(studentDto.getPhone_no());
                student.setGender(studentDto.getGender());
                student.setAddress(studentDto.getAddress());
                student.setEnrollmentDate(studentDto.getEnrollmentDate());
                student.setGrade(studentDto.getGrade());
                student.setUserName(studentDto.getUserName());
                student.setPassword(studentDto.getPassword()); // Or encoded
                student.setParentContact(studentDto.getParentContact());
                student.setRole(studentDto.getRole());
//                classroom.setClassroomId(studentDto.getClassroomId());



        return student;
    }

//    public static StudentDto mapToStudentDto(Student student) {
//        StudentDto studentDto = new StudentDto(
//                student.getStudentId(),
//                student.getFirstName(),
//                student.getMiddleName(),
//                student.getLastName(),
//                student.getEmail(),
//                student.getPhone_no(),
//                student.getGender(),
//                student.getAddress(),
//                student.getEnrollmentDate(),
//                student.getGrade(),
//                student.getUserName(),
//                student.getPassword(),
//                student.getParentContact(),
//                student.getRole(),
////                to access grade in student register
//                student.getClassroom().getClassroomId()
//
//
//
//        );
//        return studentDto;
//    }

    public static StudentDto mapToStudentDto(Student student) {
        return new StudentDto(
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
                student.getUserName(),
                student.getPassword(),
                student.getParentContact(),
                student.getRole(),
                student.getClassroom() != null ? student.getClassroom().getClassroomId() : null
        );
    }
}
