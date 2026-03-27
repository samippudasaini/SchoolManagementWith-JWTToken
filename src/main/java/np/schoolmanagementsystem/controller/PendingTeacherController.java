package np.schoolmanagementsystem.controller;
import np.schoolmanagementsystem.dto.PendingTeacherDto;
import np.schoolmanagementsystem.dto.TeacherDto;
import np.schoolmanagementsystem.service.PendingTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pending-teachers")
public class PendingTeacherController{
    @Autowired
    private PendingTeacherService pendingTeacherService;

    @PostMapping("/register")
    public ResponseEntity<PendingTeacherDto> registerPendingTeacher(@RequestBody PendingTeacherDto dto) {
        PendingTeacherDto saved = pendingTeacherService.registerPendingTeacher(dto);
        return ResponseEntity.ok(saved);

//        return ResponseEntity.ok(pendingTeacherService.registerPendingTeacher(dto));
    }


    @GetMapping
    public List<PendingTeacherDto> getAll() {
        return pendingTeacherService.getAllPendingTeachers();
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<String> approve(@PathVariable Long id) {
        pendingTeacherService.approveTeacher(id);
        return ResponseEntity.ok("Approved");
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        pendingTeacherService.rejectTeacher(id);
        return ResponseEntity.ok("Rejected");
    }
}
