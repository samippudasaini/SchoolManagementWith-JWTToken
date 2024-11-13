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
        return new ResponseEntity<>(classroomService.addClassroom(classroomDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ClassroomDto> getClassroom(@PathVariable Long id){
        return new ResponseEntity<> (classroomService.getClassroomById(id), HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable Long id, @RequestBody ClassroomDto classroomDto)
    {
        ClassroomDto updateclassroomDto=classroomService.updateClassroom(classroomDto,id);
        return new ResponseEntity<>(updateclassroomDto, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteClassroom(@PathVariable Long id){
        classroomService.deleteClassroom(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<List<ClassroomDto>> getAllClassrooms(){
        return new ResponseEntity<>(classroomService.getAllClassrooms(), HttpStatus.OK);
    }
}
