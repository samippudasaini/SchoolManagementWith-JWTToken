package np.schoolmanagementsystem.service.Impl;

import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.dto.PendingTeacherDto;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.entity.PendingTeacher;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.PendingTeacherRepository;
import np.schoolmanagementsystem.repository.TeacherRepository;
import np.schoolmanagementsystem.service.PendingTeacherService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PendingTeacherServiceImpl implements PendingTeacherService {

    @Autowired
    private PendingTeacherRepository pendingTeacherRepository;

    @Autowired
    private TeacherRepository teacherRepository;


//    private PasswordEncoder encoder;
private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public TeacherDto registerPendingTeacher(TeacherDto teacherDto) {
        Optional<PendingTeacher> existing = pendingTeacherRepository.findByEmail(teacherDto.getEmail());
        if (existing.isPresent()) {
            throw new CustomRuntimeException("Teacher already registered and pending approval.");
        }

        PendingTeacher pending = new PendingTeacher();
        pending.setFirstName(teacherDto.getFirstName());
        pending.setMiddleName(teacherDto.getMiddleName());
        pending.setLastName(teacherDto.getLastName());
        pending.setEmail(teacherDto.getEmail());
        pending.setPhone(teacherDto.getPhone());
        pending.setUserName(teacherDto.getUserName());
        pending.setPassword(encoder.encode(teacherDto.getPassword()));
        pending.setRole(Role.TEACHER);

        pendingTeacherRepository.save(pending);
        return teacherDto;
    }


    @Override
    public List<PendingTeacherDto> getAllPendingTeachers() {
        return pendingTeacherRepository.findAll().stream().map(this::mapToDto).toList();
    }


    @Override
    public void approveTeacher(Long id) {
        PendingTeacher pending = pendingTeacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Teacher teacher = new Teacher();
        teacher.setFirstName(pending.getFirstName());
        teacher.setMiddleName(pending.getMiddleName());
        teacher.setLastName(pending.getLastName());
        teacher.setEmail(pending.getEmail());
        teacher.setPhone(pending.getPhone());
        teacher.setUserName(pending.getUserName());
        teacher.setPassword(pending.getPassword());
        teacher.setRole(pending.getRole());

        teacherRepository.save(teacher);
        pendingTeacherRepository.delete(pending);

    }

    @Override
    public void rejectTeacher(Long id) {
        PendingTeacher pending = pendingTeacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        pendingTeacherRepository.delete(pending);

    }
    private PendingTeacherDto mapToDto(PendingTeacher teacher) {
        PendingTeacherDto dto = new PendingTeacherDto();
        BeanUtils.copyProperties(teacher, dto);
        return dto;
    }
}
