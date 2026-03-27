package np.schoolmanagementsystem.controller;


import jakarta.validation.Valid;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_TEACHER)


public class TeacherController {

    @Autowired
    private TeacherService teacherService;

//    public TeacherController(TeacherService teacherService) {
//        this.teacherService = teacherService;
//    }

    @PostMapping(REGISTER_TEACHER)
    public ResponseEntity<TeacherDto> teacherRegistration( @Valid @RequestBody TeacherDto teacherDto) {

        return ResponseEntity.ok(teacherService.teacherRegistration(teacherDto));
    }

    @PutMapping(UPDATE_TEACHER_BY_ID)
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        TeacherDto updateTeacherDto = teacherService.teacherUpdate(teacherDto, id);
        return ResponseEntity.ok(updateTeacherDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_TEACHER_BY_ID)
    public ResponseEntity<String> teacherDelete(@PathVariable Long id) {
        TeacherDto deleteTeacher = teacherService.teacherDelete(id);
        return  ResponseEntity.ok("delete teacher successfully") ;
    }

    @GetMapping(GET_TEACHER_BY_ID)
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable Long teacherId) {
        TeacherDto teacherDto = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacherDto);
    }

    @PostMapping(LOGIN_TEACHER)
    public String teacherLogin(@RequestBody TeacherDto teacherDto) {
        return teacherService.verify(teacherDto);
    }
    @GetMapping("/by-username/{userName}")
    public ResponseEntity<TeacherDto> getTeacherByUsername(@PathVariable String userName) {
        TeacherDto teacherDto = teacherService.getTeacherByUsername(userName);
        return ResponseEntity.ok(teacherDto);
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/approve/{pendingTeacherId}")
    public ResponseEntity<TeacherDto> approvePendingTeacher(@PathVariable Long pendingTeacherId) {
        return ResponseEntity.ok(teacherService.approvePendingTeacher(pendingTeacherId));
    }


}


