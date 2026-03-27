package np.schoolmanagementsystem.service.Impl;
import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.Enum.Role;
import np.schoolmanagementsystem.dto.PendingTeacherDto;
import np.schoolmanagementsystem.entity.PendingTeacher;
import np.schoolmanagementsystem.entity.Teacher;
import np.schoolmanagementsystem.repository.ClassroomRepository;
import np.schoolmanagementsystem.repository.PendingTeacherRepository;
import np.schoolmanagementsystem.repository.SubjectRepository;
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

    @Autowired
    private ClassroomRepository classroomRepository;

    @Autowired
    private SubjectRepository subjectRepository;

private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public PendingTeacherDto registerPendingTeacher(PendingTeacherDto dto) {

        Optional<PendingTeacher> existing = pendingTeacherRepository.findByEmail(dto.getEmail());
        if (existing.isPresent()) {
            throw new CustomRuntimeException("Teacher already registered and pending approval.");
        }

        PendingTeacher pending = new PendingTeacher();
        pending.setFirstName(dto.getFirstName());
        pending.setMiddleName(dto.getMiddleName());
        pending.setLastName(dto.getLastName());
        pending.setEmail(dto.getEmail());
        pending.setPhone(dto.getPhone());
        pending.setUserName(dto.getUserName());
        pending.setPassword(encoder.encode(dto.getPassword()));
        pending.setRole(dto.getRole() != null ? dto.getRole() : Role.TEACHER);


        pending.setGrade(String.join(",", dto.getGrade()));
        pending.setSubjectName(String.join(",", dto.getSubject()));

        pendingTeacherRepository.save(pending);
        dto.setId(pending.getId());
        return dto;
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

        // Convert comma-separated string to List<String>
        List<String> gradeList = pending.getGrade() != null
                ? List.of(pending.getGrade().split(","))
                : List.of();
        teacher.setGrade(String.valueOf(gradeList));

        List<String> subjectList = pending.getSubjectName() != null
                ? List.of(pending.getSubjectName().split(","))
                : List.of();
        teacher.setSubjectName(String.valueOf(subjectList));

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
        dto.setGrade(
                teacher.getGrade() != null ? List.of(teacher.getGrade().split(",")) : List.of()
        );

        dto.setSubject(
                teacher.getSubjectName() != null ? List.of(teacher.getSubjectName().split(",")) : List.of()
        );

        return dto;
    }
}
