package np.schoolmanagementsystem.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.TeacherMapper;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.TeacherRepository;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
@Service

@NoArgsConstructor

public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;


    private  TeacherRepository teacherRepository;

  @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, AuthenticationManager authenticationManager) {
        this.teacherRepository = teacherRepository;
      this.authenticationManager = authenticationManager;
  }

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public TeacherDto teacherRegistration(TeacherDto teacherDto) {
        Optional<Teacher> existingTeacher = teacherRepository.findById(teacherDto.getTeacherId());
        if (existingTeacher.isPresent()) {
            throw new CustomRuntimeException("teacher already exists");
        }

        teacherDto.setPassword(encoder.encode(teacherDto.getPassword()));

        Teacher teacher = TeacherMapper.mapToTeacher(teacherDto);
        teacher.setRole(Role.TEACHER);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDto(savedTeacher);

    }

    @Override
    public boolean teacherLogin(String userName, String password) {

     Teacher existingTeacher = teacherRepository
              .findByUserName(userName);
      if (existingTeacher == null) {
          throw new CustomRuntimeException("teacher does not exist");
      }
      else {
          Teacher teacher = existingTeacher.get();
          if (!teacher.getUserName().equals(userName)) {
              throw new CustomRuntimeException("teacher does not match");
          }
          if (!teacher.getPassword().equals(password)) {
              throw new CustomRuntimeException("teacher does not match");
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
                .orElseThrow(()-> new CustomRuntimeException("teacher not found"));
        teacherRepository.delete(teacher);

        return TeacherMapper.mapToTeacherDto(teacher);
    }

    @Override
    public TeacherDto getTeacherById(Long teacherId) {
        Teacher teacher=teacherRepository.findById(teacherId)
                .orElseThrow(()-> new CustomRuntimeException("teacher not found"));

        return TeacherMapper.mapToTeacherDto(teacher);

    }

    @Override
    public List<TeacherDto> getAllTeachers() {
        return List.of();
    }

    @Override
    public String verify(TeacherDto teacherDto) {
        Role role=teacherDto.getRole();

        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(teacherDto
                        .getUserName(),teacherDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(teacherDto.getUserName(),role);
        }

        return "Fail";
    }

}
