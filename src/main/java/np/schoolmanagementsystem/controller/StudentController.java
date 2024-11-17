package np.schoolmanagementsystem.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId,
                                                    @RequestBody StudentDto studentDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String role = (String) session.getAttribute("Role");
        if ("ADMIN".equals(role)) {
            StudentDto updatedStudentDto = studentService.updateStudent(studentDto, studentId, session);
            return new ResponseEntity<>(updatedStudentDto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Long id, HttpSession session) {
        StudentDto studentDto = studentService.deleteStudentById(id, session);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @PostMapping("/{register}")
    public ResponseEntity<StudentDto> studentRegistration(@RequestBody StudentDto studentDto) {

        return new ResponseEntity<>(studentService.studentRegistration(studentDto), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<String> studentLogin(@RequestBody StudentDto studentDto, HttpSession session) {

        String userName = studentDto.getUserName();
        String password = studentDto.getPassword();


        if (studentService.studentLogin(userName, password)) {
            //        session create
            session.setAttribute("id", studentDto.getStudentId());
            session.setAttribute("role", studentDto.getRole());
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);

    }

}
