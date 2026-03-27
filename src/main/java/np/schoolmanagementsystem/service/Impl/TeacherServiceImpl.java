package np.schoolmanagementsystem.service.Impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import np.schoolmanagementsystem.Auth.JWTService;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.Mapper.TeacherMapper;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.entity.PendingTeacher;
import np.schoolmanagementsystem.entity.Subject;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.repository.PendingTeacherRepository;
import np.schoolmanagementsystem.repository.SubjectRepository;
import np.schoolmanagementsystem.repository.TeacherRepository;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private  TeacherRepository teacherRepository;

    @Autowired
    private PendingTeacherRepository pendingTeacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);


    public TeacherDto teacherRegistration(TeacherDto teacherDto) {
        // Check if teacher with same email or username exists
        Optional<Teacher> existingTeacherByEmail = teacherRepository.findByEmail(teacherDto.getEmail());
        if (existingTeacherByEmail.isPresent()) {
            throw new CustomRuntimeException("Teacher with this email already exists");
        }

        Optional <Teacher> existingTeacherByUserName = teacherRepository.findByUserName(teacherDto.getUserName());
        if (existingTeacherByUserName.isPresent()) {
            throw new CustomRuntimeException("Teacher with this username already exists");
        }

        teacherDto.setPassword(encoder.encode(teacherDto.getPassword()));

        Teacher teacher = TeacherMapper.mapToTeacher(teacherDto);
        teacher.setRole(Role.TEACHER);
        // Ensure teacherId is null so JPA treats this as new entity
        teacher.setTeacherId(null);



        Teacher savedTeacher = teacherRepository.save(teacher);
        return TeacherMapper.mapToTeacherDto(savedTeacher);
    }

    @Override
    public boolean teacherLogin(String userName, String password) {

     Optional<Teacher> existingTeacher = teacherRepository
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
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream()
                .map(TeacherMapper::mapToTeacherDto)
                .toList();
    }
    @Override
    public String verify(TeacherDto dto) {
        Teacher teacher = teacherRepository.findByUserName(dto.getUserName())
                .orElseThrow(() -> new CustomRuntimeException("User not found."));

        if (!BCrypt.checkpw(dto.getPassword(), teacher.getPassword())) {
            throw new CustomRuntimeException("Invalid credentials.");
        }

        return "Login successful";
        // Replace with JWT token generation if needed
    }
    public TeacherDto getTeacherByUsername(String userName) {
        Teacher teacher = teacherRepository.findByUserName(userName)
                .orElseThrow(() -> new CustomRuntimeException("Teacher not found"));
        return TeacherMapper.mapToTeacherDto(teacher);
    }


//    add after pending teacher work fine

    @Override
    public TeacherDto approvePendingTeacher(Long pendingTeacherId) {
        PendingTeacher pending = pendingTeacherRepository.findById(pendingTeacherId)
                .orElseThrow(() -> new CustomRuntimeException("Pending teacher not found"));

        // Create and populate Teacher
        Teacher teacher = new Teacher();
        teacher.setFirstName(pending.getFirstName());
        teacher.setMiddleName(pending.getMiddleName());
        teacher.setLastName(pending.getLastName());
        teacher.setEmail(pending.getEmail());
        teacher.setPhone(pending.getPhone());
        teacher.setUserName(pending.getUserName());
        teacher.setPassword(pending.getPassword());
        teacher.setRole(Role.TEACHER); // Set default role

        // Directly copy grade and subjectName from PendingTeacher
        teacher.setGrade(String.valueOf(Collections.singletonList(pending.getGrade())));
        teacher.setSubjectName(String.valueOf(Collections.singletonList(pending.getSubjectName())));

        // Save and remove from pending
        Teacher saved = teacherRepository.save(teacher);
        pendingTeacherRepository.delete(pending);

        return TeacherMapper.mapToTeacherDto(saved);
    }

}
