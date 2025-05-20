package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.PendingTeacherDto;
import np.schoolmanagementsystem.dto.TeacherDto;

import java.util.List;

public interface PendingTeacherService {
    TeacherDto registerPendingTeacher(TeacherDto teacherDto);
    List<PendingTeacherDto> getAllPendingTeachers();
    void approveTeacher(Long id);
    void rejectTeacher(Long id);
}
