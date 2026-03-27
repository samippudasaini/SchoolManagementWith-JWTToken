//package np.schoolmanagementsystem.Mapper;
//import np.schoolmanagementsystem.dto.*;
//import np.schoolmanagementsystem.entity.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
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
//        if (teacherDto.getGrade() != null) {
//            List<Classroom> classrooms = teacherDto.getGrade().stream()
//                    .map(dto -> {
//                        Classroom c = new Classroom();
////                        c.setClassroomId(dto.getClassroomId());
//                        c.setGrade(c.getGrade());
//                        return c;
//                    })
//                    .collect(Collectors.toList());
//            teacher.setGrade(classrooms);
//        }
//
//        if (teacherDto.getSubjectName() != null) {
//            List<Subject> subjects = teacherDto.getSubjectName().stream()
//                    .map(dto -> {
//                        Subject s = new Subject();
////                        s.setSubjectId(dto.getSubjectId());
//                        s.setSubjectName(s.getSubjectName());
//                        return s;
//                    })
//                    .collect(Collectors.toList());
//            teacher.setSubject(subjects);
//        }
//
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
//        if (teacher.getGrade() != null) {
////            List<ClassroomDto> classrooms = teacher.getGrade().stream()
////                    .map(entity -> new ClassroomDto(
////                            entity.getClassroomId(),
////                            entity.getGrade()
////                    ))
////                    .collect(Collectors.toList());
////            teacherDto.setGrade(classrooms);
////        }
//            List<String> gradeNames = teacher.getGrade().stream()
//                    .map(Classroom::getGrade)
//                    .collect(Collectors.toList());
//            teacherDto.setGrade(gradeNames); // ✅ now matches List<String>
//        }
//
//        if (teacher.getSubject() != null) {
////            List<SubjectDto> subjects = teacher.getSubject().stream()
////                    .map(entity -> new SubjectDto(
////                            String.valueOf(entity.getSubjectId()), // Convert Long to String
////                            entity.getSubjectName()
////                    ))
////                    .collect(Collectors.toList());
////            teacherDto.setSubject(subjects);
//
//            List<String> subjectNames = teacher.getSubject().stream()
//                    .map(Subject::getSubjectName)
//                    .collect(Collectors.toList());
//            teacherDto.setSubjectName(subjectNames); // ✅ now matches List<String>
//        }
//
//        return teacherDto;
//    }
//}
//
//


package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.Teacher;

import java.util.Collections;
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

        // Direct mapping of string lists (no entity mapping needed)
        teacher.setGrade(String.valueOf(teacherDto.getGrade()));
        teacher.setSubjectName(String.valueOf(teacherDto.getSubjectName()));

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

        if (teacherDto.getGrade() != null) {
            List<String> grades = List.of(teacher.getGrade().split(","));
            teacherDto.setGrade(grades);
        }

        if (teacher.getSubjectName() != null) {
            List<String> subjects = List.of(teacher.getSubjectName().split(","));
            teacherDto.setSubjectName(subjects);
        }



        // Direct mapping of string lists
        teacherDto.setGrade(Collections.singletonList(teacher.getGrade()));
        teacherDto.setSubjectName(Collections.singletonList(teacher.getSubjectName()));

        return teacherDto;
    }
}

