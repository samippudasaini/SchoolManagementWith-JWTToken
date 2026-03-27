package np.schoolmanagementsystem.controller;


import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.LoginResponse;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.dto.masterResponse;
import np.schoolmanagementsystem.entity.Student;
import np.schoolmanagementsystem.payload.APIResponse;
import np.schoolmanagementsystem.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_STUDENT)
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(UPDATE_STUDENT)
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long studentId,
                                                    @RequestBody StudentDto studentDto) {
            StudentDto updatedStudentDto = studentService.updateStudent(studentDto, studentId);
            return new ResponseEntity<>(updatedStudentDto, HttpStatus.OK);

    }

    @GetMapping(GET_STUDENT_BY_ID)
    public ResponseEntity<StudentDto> getStudent(@PathVariable Long studentId) {
        StudentDto studentDto = studentService.getStudentById(studentId);
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_STUDENT)
    public ResponseEntity<String> deleteStudent(@PathVariable Long id, HttpSession session) {
        StudentDto studentDto = studentService.deleteStudentById(id, session);
        return  ResponseEntity.ok("Student deleted successfully.");
    }

    @PostMapping(REGISTER_STUDENT)
    public ResponseEntity<masterResponse<?>> studentRegistration(@RequestBody StudentDto studentDto) {
        return new ResponseEntity<>(studentService
                .studentRegistration(studentDto), HttpStatus.CREATED);

    }

//    @PostMapping(LOGIN_STUDENT)
//    public String studentLogin(@RequestBody StudentDto studentDto) {
//            return studentService.verify(studentDto);
//    }

    @PostMapping(LOGIN_STUDENT)
    public ResponseEntity<APIResponse> studentLogin(@RequestBody StudentDto studentDto) {
        try {
//            String token = studentService.verify(studentDto);
            LoginResponse loginResponse = studentService.verify(studentDto);

            return ResponseEntity.ok(new APIResponse(true, "Login successful", loginResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new APIResponse(false, e.getMessage(), null));
        }
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_ALL_STUDENT)
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

//    admin control

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/approve/{studentId}")
    public ResponseEntity<String> approveStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.approveStudent(studentId));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/reject/{studentId}")
    public ResponseEntity<String> rejectStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(studentService.rejectStudent(studentId));
    }

    @GetMapping("/pending")
    public ResponseEntity<APIResponse> getPendingStudents() {
        List<Student> pendingStudents = studentService.getPendingStudents();
        return ResponseEntity.ok(new APIResponse(true, "Success", pendingStudents));
    }

//    @GetMapping("/username/{userName}")
//    public ResponseEntity<Student> getStudentByUserName(@PathVariable String userName) {
//        Student student = studentService.getStudentByUserName(userName);
//        if (student != null) {
//            return ResponseEntity.ok(student);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }

//    to add for fee fetch to problem aries

    @GetMapping("/username/{userName}")
    public ResponseEntity<StudentDto> getStudentByUserName(@PathVariable String userName) {
        StudentDto studentDto = studentService.getStudentDtoByUsername(userName);
        if (studentDto != null) {
            return ResponseEntity.ok(studentDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
