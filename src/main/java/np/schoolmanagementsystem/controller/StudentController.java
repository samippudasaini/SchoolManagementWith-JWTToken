package np.schoolmanagementsystem.controller;


import jakarta.servlet.http.HttpSession;
import np.schoolmanagementsystem.dto.StudentDto;
import np.schoolmanagementsystem.dto.masterResponse;
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
//        HttpSession session = request.getSession(false);
//        String role = (String) session.getAttribute("Role");
//        if ("ADMIN".equals(role)) {
            StudentDto updatedStudentDto = studentService.updateStudent(studentDto, studentId);
            return new ResponseEntity<>(updatedStudentDto, HttpStatus.OK);
//        }
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
//        if (studentDto.getClassroom() == null) {
//            System.out.println("Classroom in StudentDto is null!");
//        } else {
//            System.out.println("Classroom ID in StudentDto: " + studentDto.getClassroom().getClassroomId());
//        }


        return new ResponseEntity<>(studentService
                .studentRegistration(studentDto), HttpStatus.CREATED);

    }


    @PostMapping(LOGIN_STUDENT)
    public String studentLogin(@RequestBody StudentDto studentDto) {

//        String userName = studentDto.getUserName();
//        String password = studentDto.getPassword();
//
//
//        if (studentService.studentLogin(userName, password)) {
//            //        session create
////            session.setAttribute("id", studentDto.getStudentId());
////            session.setAttribute("role", studentDto.getRole());
//            return ResponseEntity.ok("Login successful.");
//        }
//        else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Failed");
//        }
////        return studentService.verify(studentDto);
            return studentService.verify(studentDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(GET_ALL_STUDENT)
    public ResponseEntity<List<StudentDto>> getAllStudents() {
//        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(studentService.getAllStudents());

    }

}
