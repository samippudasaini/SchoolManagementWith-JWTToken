package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/teacher")


public class TeacherController {

    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    public ResponseEntity<TeacherDto> teacherRegistration(@RequestBody TeacherDto teacherDto) {

        return ResponseEntity.ok(teacherService.teacherRegistration(teacherDto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        TeacherDto updateTeacherDto = teacherService.teacherUpdate(teacherDto, id);
        return ResponseEntity.ok(updateTeacherDto);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TeacherDto> teacherDelete(@PathVariable Long id) {
        TeacherDto deleteTeacher = teacherService.teacherDelete(id);
        return ResponseEntity.ok(deleteTeacher);
    }

    @GetMapping("/get/{teacherId}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long teacherId) {
        TeacherDto teacherDto = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacherDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> teacherLogin(@RequestBody TeacherDto teacherDto) {
        String userName = teacherDto.getUserName();
        String password = teacherDto.getPassword();
        if (teacherService.teacherLogin(userName, password)) {
            return ResponseEntity.ok("success login");
        }
        return ResponseEntity.ok("fail");
    }
}


