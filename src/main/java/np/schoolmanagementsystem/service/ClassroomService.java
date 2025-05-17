package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {

    ClassroomDto addClassroom(ClassroomDto classroomDto);

    List<ClassroomDto> getAllClassrooms();

    ClassroomDto getClassroomById(Long ClasroomId);

    ClassroomDto updateClassroom(ClassroomDto classroomDto, Long ClasroomId);

    ClassroomDto deleteClassroom(Long ClasroomId);
    List<String> getAllGrades();

}
