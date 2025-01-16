package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_TEACHER)


public class TeacherController {

    @Autowired
    TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping(REGISTER_TEACHER)
    public ResponseEntity<TeacherDto> teacherRegistration(@RequestBody TeacherDto teacherDto) {

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
//        String userName = teacherDto.getUserName();
//        String password = teacherDto.getPassword();
//        if (teacherService.teacherLogin(userName, password)) {
//            return ResponseEntity.ok("success login");
//        }
        return teacherService.verify(teacherDto);
//        return ResponseEntity.ok("fail");
    }
}


