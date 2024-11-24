package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.SubjectDto;
import np.schoolmanagementsystem.entity.Subject;
import np.schoolmanagementsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.addSubject(subjectDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable String id) {
        SubjectDto subjectDto = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subjectDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable String id) {
        SubjectDto subjectDto1 = subjectService.updateSubject(subjectDto, id);
        return ResponseEntity.ok(subjectDto1);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<SubjectDto> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/getall")
    public ResponseEntity<List<Subject>> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
}
