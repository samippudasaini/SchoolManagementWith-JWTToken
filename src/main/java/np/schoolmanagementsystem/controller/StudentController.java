package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }
    @PostMapping
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto)
    {
        return new ResponseEntity<>(studentService.addStudent(studentDto), HttpStatus.CREATED);
    }
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@RequestBody StudentDto studentDto, @PathVariable Long studentId)
    {
        StudentDto updatedStudentDto = studentService.updateStudent(studentDto, studentId);
        return new ResponseEntity<>(updatedStudentDto, HttpStatus.OK);
    }

@GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId){
    StudentDto studentDto=studentService.getStudentById(studentId);
    return new ResponseEntity<>(studentDto, HttpStatus.OK);
}
@DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id){
      StudentDto studentDto=  studentService.deleteStudentById(id);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
}

@PostMapping("/{register}")
    public ResponseEntity<StudentDto> studentRegistration(@RequestBody StudentDto studentDto)
{

        return new ResponseEntity<>(studentService.studentRegistration(studentDto), HttpStatus.CREATED);
}
}
