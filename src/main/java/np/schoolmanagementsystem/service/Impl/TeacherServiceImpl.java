package np.schoolmanagementsystem.service.Impl;

import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Mapper.TeacherMapper;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.TeacherRepository;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service
@NoArgsConstructor
public class TeacherServiceImpl implements TeacherService {

  private  TeacherRepository teacherRepository;

  @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherDto teacherRegistration(TeacherDto teacherDto) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(teacherDto.getTeacherId());
        if (existingTeacher.isPresent()) {
            throw new RuntimeException("teacher already exists");
        }

        Teacher teacher = TeacherMapper.mapToTeacher(teacherDto);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDto(savedTeacher);

    }

    @Override
    public boolean teacherLogin(String userName, String password) {

      Optional<Teacher> existingTeacher = teacherRepository
              .findByuserName(userName);
      if (existingTeacher.isEmpty()) {
          throw new RuntimeException("teacher does not exist");
      }
      else {
          Teacher teacher = existingTeacher.get();
          if (!teacher.getUserName().equals(userName)) {
              throw new RuntimeException("teacher does not match");
          }
          if (!teacher.getPassword().equals(password)) {
              throw new RuntimeException("teacher does not match");
          }

      }

      return true;
    }

    @Override
    public TeacherDto teacherUpdate(TeacherDto teacherDto, Long teacherId) {
        Teacher teacher=TeacherMapper.mapToTeacher(teacherDto);
       Teacher updateTeacher=teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDto(updateTeacher);
    }

    @Override
    public TeacherDto teacherDelete( @PathVariable Long Id) {
        Teacher teacher=teacherRepository.findById(Id)
                .orElseThrow(()-> new RuntimeException("teacher not found"));
        teacherRepository.delete(teacher);

        return TeacherMapper.mapToTeacherDto(teacher);
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        Teacher teacher=teacherRepository.findById(teacherId)
                .orElseThrow(()-> new RuntimeException("teacher not found"));

        return TeacherMapper.mapToTeacherDto(teacher);

    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return List.of();
    }

}
