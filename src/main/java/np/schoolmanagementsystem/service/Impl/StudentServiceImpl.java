package np.schoolmanagementsystem.service.Impl;

import lombok.Data;
import np.schoolmanagementsystem.Mapper.StudentMapper;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.repository.StudentRepository;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data


public class StudentServiceImpl implements StudentService {

   private  StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public StudentDto addStudent(StudentDto studentDto) {
            Student student = StudentMapper.mapToStudent(studentDto);
            Student savedStudent = studentRepository.save(student);
            return StudentMapper.mapToStudentDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long studentId) {
        Student student=StudentMapper.mapToStudent(studentDto);
        Student updateStudent=studentRepository.save(student);
        return StudentMapper.mapToStudentDto(updateStudent);
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Student student=studentRepository.findById(studentId)
                .orElseThrow(()-> new RuntimeException("Student not found"));

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public StudentDto deleteStudentById(Long id) {
        Student student=studentRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Student not found"));
        studentRepository.delete(student);

        return StudentMapper.mapToStudentDto(student);
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return List.of();
    }

    @Override
    public StudentDto studentRegistration(StudentDto studentDto) {

            Optional<Student> existingStudent = studentRepository.findByEmail(studentDto.getEmail());
            if (existingStudent.isPresent()) {
                throw new RuntimeException("Student already exists");
            }


        Student student=StudentMapper.mapToStudent(studentDto);
        Student savedStudent=studentRepository.save(student);
        return StudentMapper.mapToStudentDto(savedStudent);

    }
}
