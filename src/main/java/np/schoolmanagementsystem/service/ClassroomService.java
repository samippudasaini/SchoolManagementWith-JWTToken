package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.ClassroomDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {

    ClassroomDto addClassroom(ClassroomDto classroomDto);
    List<ClassroomDto> getAllClassrooms();
    ClassroomDto getClassroomById(Long id);
    ClassroomDto updateClassroom(ClassroomDto classroomDto, Long id);
    ClassroomDto deleteClassroom(Long id);
}
