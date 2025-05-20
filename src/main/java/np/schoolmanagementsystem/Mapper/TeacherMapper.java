package np.schoolmanagementsystem.Mapper;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import np.schoolmanagementsystem.dto.TeacherDto;
//import np.schoolmanagementsystem.entity.Classroom;
//import np.schoolmanagementsystem.entity.Subject;
//import np.schoolmanagementsystem.entity.Teacher;
//
//import java.util.ArrayList;
//
//public class TeacherMapper {
//    public static Teacher mapToTeacher(TeacherDto teacherDto) {
//        Classroom classroom = new Classroom();
////        classroom.setClassroomId(teacherDto.getClassroomId());
//
//        Subject subject = new Subject();
////        subject.setSubjectId(teacherDto.getSubjectId());
//
//        Teacher teacher = new Teacher(
//                teacherDto.getTeacherId(),
//                teacherDto.getFirstName(),
//                teacherDto.getMiddleName(),
//                teacherDto.getLastName(),
//                teacherDto.getEmail(),
//                teacherDto.getPhone(),
//                teacherDto.getUserName(),
//                teacherDto.getPassword(),
//                teacherDto.getRole(),
//                teacherDto.getGrade(),
//                teacherDto.getSubject()
//
//        );
//        return teacher;
//    }
//    public static TeacherDto mapToTeacherDto(Teacher teacher) {
//        TeacherDto teacherDto = new TeacherDto(
//                teacher.getTeacherId(),
//                teacher.getFirstName(),
//                teacher.getMiddleName(),
//                teacher.getLastName(),
//                teacher.getEmail(),
//                teacher.getPhone(),
////                teacher.getSubjectName(),
//                teacher.getGrade(),
//                teacher.getUserName(),
//                teacher.getPassword(),
//                teacher.getSubject(),
//                teacher.getRole()
////                teacher,
////                teacher
//        );
//        return teacherDto;
//    }
//}

//
//import np.schoolmanagementsystem.dto.ClassroomDto;
//import np.schoolmanagementsystem.dto.SubjectDto;
//import np.schoolmanagementsystem.dto.TeacherDto;
//import np.schoolmanagementsystem.entity.Classroom;
//import np.schoolmanagementsystem.entity.Subject;
//import np.schoolmanagementsystem.entity.Teacher;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class TeacherMapper {
//
//    public static Teacher mapToTeacher(TeacherDto teacherDto) {
//        Teacher teacher = new Teacher();
//
//        teacher.setTeacherId(teacherDto.getTeacherId());
//        teacher.setFirstName(teacherDto.getFirstName());
//        teacher.setMiddleName(teacherDto.getMiddleName());
//        teacher.setLastName(teacherDto.getLastName());
//        teacher.setEmail(teacherDto.getEmail());
//        teacher.setPhone(teacherDto.getPhone());
//        teacher.setUserName(teacherDto.getUserName());
//        teacher.setPassword(teacherDto.getPassword());
//        teacher.setRole(teacherDto.getRole());
//
//        // Map List<ClassroomDto> to List<Classroom>
//        if (teacherDto.getGrade() != null) {
//            List<Classroom> classrooms = teacherDto.getGrade().stream()
//                    .map(dto -> {
//                        Classroom c = new Classroom();
//                        c.setClassroomId(dto.getClassroomId());
//                        c.setGrade(dto.getGrade());
//                        return c;
//                    })
//                    .collect(Collectors.toList());
//            teacher.setGrade(classrooms);
//        }
//
//        // Map List<SubjectDto> to List<Subject>
//        if (teacherDto.getSubject() != null) {
//            List<Subject> subjects = teacherDto.getSubject().stream()
//                    .map(dto -> {
//                        Subject s = new Subject();
//                        s.setSubjectId(dto.getSubjectId());
//                        s.setSubjectName(dto.getSubjectName());
//                        return s;
//                    })
//                    .collect(Collectors.toList());
//            teacher.setSubject(subjects);
//        }
//        return teacher;
//    }
//
//    public static TeacherDto mapToTeacherDto(Teacher teacher) {
//        TeacherDto teacherDto = new TeacherDto();
//
//        teacherDto.setTeacherId(teacher.getTeacherId());
//        teacherDto.setFirstName(teacher.getFirstName());
//        teacherDto.setMiddleName(teacher.getMiddleName());
//        teacherDto.setLastName(teacher.getLastName());
//        teacherDto.setEmail(teacher.getEmail());
//        teacherDto.setPhone(teacher.getPhone());
//        teacherDto.setUserName(teacher.getUserName());
//        teacherDto.setPassword(teacher.getPassword());
//        teacherDto.setRole(teacher.getRole());
//
//        // Map List<Classroom> to List<ClassroomDto>
//        if (teacher.getGrade() != null) {
//            List<ClassroomDto> classrooms = teacher.getGrade().stream()
//                    .map(entity -> new ClassroomDto(entity.getClassroomId(), entity.getGrade()))
//                    .collect(Collectors.toList());
//            teacherDto.setGrade(classrooms);
//        }
//
//        // Map List<Subject> to List<SubjectDto>
//        if (teacher.getSubject() != null) {
//            List<SubjectDto> subjects = teacher.getSubject().stream()
//                    .map(entity -> new SubjectDto(entity.getSubjectId(), entity.getSubjectName()))
//                    .collect(Collectors.toList());
//            teacherDto.setSubject(subjects);
//        }
//
//        return teacherDto;
//    }

//package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.*;
import np.schoolmanagementsystem.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherMapper {

    public static Teacher mapToTeacher(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();

        teacher.setTeacherId(teacherDto.getTeacherId());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setMiddleName(teacherDto.getMiddleName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setPhone(teacherDto.getPhone());
        teacher.setUserName(teacherDto.getUserName());
        teacher.setPassword(teacherDto.getPassword());
        teacher.setRole(teacherDto.getRole());

        if (teacherDto.getGrade() != null) {
            List<Classroom> classrooms = teacherDto.getGrade().stream()
                    .map(dto -> {
                        Classroom c = new Classroom();
                        c.setClassroomId(dto.getClassroomId());
                        c.setGrade(dto.getGrade());
                        return c;
                    })
                    .collect(Collectors.toList());
            teacher.setGrade(classrooms);
        }

        if (teacherDto.getSubject() != null) {
            List<Subject> subjects = teacherDto.getSubject().stream()
                    .map(dto -> {
                        Subject s = new Subject();
                        s.setSubjectId(dto.getSubjectId());
                        s.setSubjectName(dto.getSubjectName());
                        return s;
                    })
                    .collect(Collectors.toList());
            teacher.setSubject(subjects);
        }

        return teacher;
    }

    public static TeacherDto mapToTeacherDto(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setTeacherId(teacher.getTeacherId());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setMiddleName(teacher.getMiddleName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setEmail(teacher.getEmail());
        teacherDto.setPhone(teacher.getPhone());
        teacherDto.setUserName(teacher.getUserName());
        teacherDto.setPassword(teacher.getPassword());
        teacherDto.setRole(teacher.getRole());

        if (teacher.getGrade() != null) {
            List<ClassroomDto> classrooms = teacher.getGrade().stream()
                    .map(entity -> new ClassroomDto(entity.getClassroomId(), entity.getGrade()))
                    .collect(Collectors.toList());
            teacherDto.setGrade(classrooms);
        }

        if (teacher.getSubject() != null) {
            List<SubjectDto> subjects = teacher.getSubject().stream()
                    .map(entity -> new SubjectDto(entity.getSubjectId(), entity.getSubjectName()))
                    .collect(Collectors.toList());
            teacherDto.setSubject(subjects);
        }

        return teacherDto;
    }
}


