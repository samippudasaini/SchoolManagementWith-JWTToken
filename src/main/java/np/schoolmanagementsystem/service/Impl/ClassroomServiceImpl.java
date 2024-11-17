package np.schoolmanagementsystem.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Mapper.ClassroomMapper;
import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClassroomServiceImpl implements ClassroomService {

   @Autowired
    private ClassroomRepository classroomRepository;


    @Override

    public ClassroomDto addClassroom(ClassroomDto classroomDto) {
//        Optional<Classroom> existingclass = classroomRepository.findById(classroomDto.getClassroomId());
//        if (existingclass.isPresent()) {
//            throw new RuntimeException("Classroom already exists");
//        }
        Classroom classroom = ClassroomMapper.mapToClassroom(classroomDto);
        Classroom savedClassroom = classroomRepository.save(classroom);
        return ClassroomMapper.mapToClassroomDto(savedClassroom);
    }

    @Override
    public List<ClassroomDto> getAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream().map(ClassroomMapper::mapToClassroomDto).collect(Collectors.toList());

    }

    @Override

    public ClassroomDto getClassroomById(Long ClassroomId) {
        Classroom classroom = classroomRepository.findById(ClassroomId)
                .orElseThrow(() -> new RuntimeException("classroom not found"));

        return ClassroomMapper.mapToClassroomDto(classroom);
    }

    @Override
    public ClassroomDto updateClassroom(ClassroomDto classroomDto, Long ClassroomId) {
        Optional<Classroom> existsClassroom = classroomRepository.findById(classroomDto.getClassroomId());
        if (!existsClassroom.isPresent()) {
            throw new RuntimeException("Classroom not found");
        }
        Classroom classroom = ClassroomMapper.mapToClassroom(classroomDto);
        Classroom savedClassroom = classroomRepository.save(classroom);

        return ClassroomMapper.mapToClassroomDto(savedClassroom);
    }

    @Override
    public ClassroomDto deleteClassroom(Long ClassroomId) {
        Classroom classroom = classroomRepository.findById(ClassroomId)
                .orElseThrow(() -> new RuntimeException("classroom not found"));
        classroomRepository.delete(classroom);
        return ClassroomMapper.mapToClassroomDto(classroom);
    }
}
