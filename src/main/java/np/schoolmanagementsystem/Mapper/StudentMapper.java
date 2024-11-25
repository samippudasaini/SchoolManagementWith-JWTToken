package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Student;

import java.util.ArrayList;


public class StudentMapper {
    public static Student mapToStudent(StudentDto studentDto) {

        Classroom classroom = new Classroom();
        classroom.setClassroomId(studentDto.getClassroomId());

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
                studentDto.getUserName(),
                studentDto.getPassword(),
                studentDto.getRole(),
                classroom


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
                student.getUserName(),
                student.getPassword(),
                student.getParentContact(),
                student.getRole(),
                student.getClassroom().getClassroomId()



        );
        return studentDto;
    }

}
