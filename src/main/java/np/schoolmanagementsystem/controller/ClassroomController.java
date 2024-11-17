package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.ClassroomDto;
import np.schoolmanagementsystem.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
    private  final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @PostMapping("/add")

    public ResponseEntity<ClassroomDto> addClassroom(@RequestBody ClassroomDto classroomDto) {
        if(classroomDto==null){
            throw new IllegalArgumentException("Classroom filed cannot be null");
        }
        return new ResponseEntity<>(classroomService.addClassroom(classroomDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{ClassroomId}")
    public ResponseEntity<ClassroomDto> getClassroom(@PathVariable Long ClassroomId){
        return new ResponseEntity<> (classroomService.getClassroomById(ClassroomId), HttpStatus.OK);
    }
    @PutMapping("/update/{ClassroomId}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable Long ClassroomId, @RequestBody ClassroomDto classroomDto)
    {
        ClassroomDto updateclassroomDto=classroomService.updateClassroom(classroomDto,ClassroomId);
        return new ResponseEntity<>(updateclassroomDto, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{ClassroomId}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Long ClassroomId){
        classroomService.deleteClassroom(ClassroomId);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<ClassroomDto>> getAllClassrooms(){
        return new ResponseEntity<>(classroomService.getAllClassrooms(), HttpStatus.OK);
    }
}
