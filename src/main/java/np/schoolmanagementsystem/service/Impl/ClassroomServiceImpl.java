package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Mapper.ClassroomMapper;
import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@Data
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
     private ClassroomRepository classroomRepository;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }


    @Override

    public ClassroomDto addClassroom(ClassroomDto classroomDto)
    {
        Optional<Classroom> existingclass=classroomRepository.findByid(classroomDto.getId());
        if(existingclass.isPresent()){
            throw new RuntimeException("Classroom already exists");
        }
        Classroom classroom= ClassroomMapper.mapToClassroom(classroomDto);
        Classroom savedClassroom=classroomRepository.save(classroom);
        return ClassroomMapper.mapToClassroomDto(savedClassroom);
    }

    @Override
    public List<ClassroomDto> getAllClassrooms() {
        List<Classroom> classrooms=classroomRepository.findAll();
        return classrooms.stream().map(ClassroomMapper::mapToClassroomDto).collect(Collectors.toList());

    }

    @Override

    public ClassroomDto getClassroomById(Long id) {
        Classroom classroom=classroomRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("classroom not found"));

        return ClassroomMapper.mapToClassroomDto(classroom);
    }

    @Override
    public ClassroomDto updateClassroom(ClassroomDto classroomDto, Long id) {
        Optional<Classroom> existsClassroom=classroomRepository.findByid(classroomDto.getId());
        if(!existsClassroom.isPresent()){
            throw new RuntimeException("Classroom not found");
        }
        Classroom classroom=ClassroomMapper.mapToClassroom(classroomDto);
        Classroom savedClassroom=classroomRepository.save(classroom);

        return ClassroomMapper.mapToClassroomDto(savedClassroom);
    }

    @Override
    public ClassroomDto deleteClassroom(Long id) {
        Classroom classroom=classroomRepository.findByid(id)
                .orElseThrow(()-> new RuntimeException("classroom not found"));
        classroomRepository.delete(classroom);
        return ClassroomMapper.mapToClassroomDto(classroom);
    }
}
