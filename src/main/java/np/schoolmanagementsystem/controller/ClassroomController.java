package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.CustomExcaption.CustomIlligalArgumentException;
import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.entity.Classroom;
import np.schoolmanagementsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_CLASSROOM)
public class ClassroomController {
//    @Autowired
    private  final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ADD_CLASSROOM)

    public ResponseEntity<ClassroomDto> addClassroom(@RequestBody ClassroomDto classroomDto) {
//        System.out.println(classroomDto);
        if(classroomDto==null){
            throw new CustomIlligalArgumentException("Classroom filed cannot be null");
        }
        return new ResponseEntity<>(classroomService.addClassroom(classroomDto), HttpStatus.CREATED);
    }

    @GetMapping(GET_CLASSROOM_BY_ID)
    public ResponseEntity<ClassroomDto> getClassroom(@PathVariable Long ClassroomId){
        return new ResponseEntity<> (classroomService.getClassroomById(ClassroomId), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(UPDATE_CLASSROOM_BY_ID)
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable Long ClassroomId, @RequestBody ClassroomDto classroomDto)
    {
        ClassroomDto updateclassroomDto=classroomService.updateClassroom(classroomDto,ClassroomId);
        return new ResponseEntity<>(updateclassroomDto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_CLASSROOM_BY_ID)
    public ResponseEntity<String> deleteClassroom(@PathVariable Long id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }


    @GetMapping(GET_ALL_CLASSROOM)
    public ResponseEntity<List<ClassroomDto>> getAllClassrooms(){
        List<ClassroomDto> classrooms=classroomService.getAllClassrooms();
        return ResponseEntity.ok(classrooms);
//        return new ResponseEntity<>(classroomService.getAllClassrooms(), HttpStatus.OK);
    }

//    its for to access grade in frontend
    @GetMapping(GET_ALL_GRADE)
    public ResponseEntity<List<String>> getAllGrades() {
        List<String> grades=classroomService.getAllGrades();
        return ResponseEntity.ok(grades);
    }
}
