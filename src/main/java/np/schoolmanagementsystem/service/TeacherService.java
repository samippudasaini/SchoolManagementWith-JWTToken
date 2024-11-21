package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    TeacherDto teacherRegistration(TeacherDto teacherDto);

    boolean teacherLogin(String email, String password);

    TeacherDto teacherUpdate(TeacherDto teacherDto, Long teacherId);

    TeacherDto teacherDelete(Long id);

    TeacherDto getTeacherById(Long teacherId);

    List<TeacherDto> getAllTeachers();

    String verify(TeacherDto teacherDto);
}
