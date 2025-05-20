package np.schoolmanagementsystem.controller;


import np.schoolmanagementsystem.dto.SubjectDto;
import np.schoolmanagementsystem.entity.Subject;
import np.schoolmanagementsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_SUBJECT)
public class SubjectController {

    @Autowired
    private  SubjectService subjectService;

//    @Autowired
//    public SubjectController(SubjectService subjectService) {
//        this.subjectService = subjectService;
//    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(ADD_SUBJECT)
    public ResponseEntity<SubjectDto> addSubject(@RequestBody SubjectDto subjectDto) {
        return ResponseEntity.ok(subjectService.addSubject(subjectDto));
    }

    @GetMapping(GET_SUBJECT_BY_ID)
    public ResponseEntity<SubjectDto> getSubjectById(@PathVariable String id) {
        SubjectDto subjectDto = subjectService.getSubjectById(id);
        if(subjectDto != null) {
            return ResponseEntity.ok(subjectDto);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(UPDATE_SUBJECT_BY_ID)
    public ResponseEntity<SubjectDto> updateSubject(@RequestBody SubjectDto subjectDto, @PathVariable String id) {
        SubjectDto subjectDto1 = subjectService.updateSubject(subjectDto, id);
        if(subjectDto1 != null) {
            return ResponseEntity.ok(subjectDto1);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(DELETE_SUBJECT_BY_ID)
    public ResponseEntity<String> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
//        return ResponseEntity.ok().build();

        return  ResponseEntity.ok("Subject deleted successfully.");
    }



    @GetMapping(GET_ALL_SUBJECT)
    public ResponseEntity<List<SubjectDto>> getAllSubject() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }
}
