package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.Subject;
import np.schoolmanagementsystem.entity.Teacher;

import java.util.ArrayList;

public class TeacherMapper {
    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        Classroom classroom = new Classroom();
        classroom.setClassroomId(teacherDto.getClassroomId());

        Subject subject = new Subject();
        subject.setSubjectId(teacherDto.getSubjectId());

        Teacher teacher = new Teacher(
                teacherDto.getTeacherId(),
                teacherDto.getFirstName(),
                teacherDto.getMiddleName(),
                teacherDto.getLastName(),
                teacherDto.getEmail(),
                teacherDto.getPhone(),
                teacherDto.getSubjectName(),
                teacherDto.getGrade(),
                teacherDto.getUserName(),
                teacherDto.getPassword(),
                teacherDto.getRole(),
                subject,
                classroom



        );
        return teacher;
    }
    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto(
                teacher.getTeacherId(),
                teacher.getFirstName(),
                teacher.getMiddleName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getPhone(),
                teacher.getSubjectName(),
                teacher.getGrade(),
                teacher.getUserName(),
                teacher.getPassword(),
                teacher.getRole(),
                teacher.getClassroom() != null ? teacher.getClassroom().getClassroomId() : null,
                teacher.getSubjects() != null ? teacher.getSubjects().getSubjectId(): null
        );
        return teacherDto;
    }
}
