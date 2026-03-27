package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.PendingTeacherDto;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.PendingTeacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PendingTeacherService {
    PendingTeacherDto registerPendingTeacher(PendingTeacherDto dto);
    List<PendingTeacherDto> getAllPendingTeachers();
    void approveTeacher(Long id);
    void rejectTeacher(Long id);
}
