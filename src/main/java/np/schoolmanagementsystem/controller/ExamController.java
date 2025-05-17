package np.schoolmanagementsystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import np.schoolmanagementsystem.dto.ExamRequestDto;
import np.schoolmanagementsystem.dto.ResponseDto;
import np.schoolmanagementsystem.dto.DetailedResponse;
import np.schoolmanagementsystem.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static np.schoolmanagementsystem.ApiUrls.API.*;

@RestController
@RequestMapping(BASE_URL_EXAM)
public class ExamController {

    @Autowired
    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

@PostMapping(ADD_EXAM)
public ResponseEntity<ResponseDto> addExam(
        @RequestPart("exam") String examJson,
        @RequestPart("routine") MultipartFile routineFile) throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    ExamRequestDto examRequestDto = mapper.readValue(examJson, ExamRequestDto.class);

    if (routineFile != null && !routineFile.isEmpty()) {
        byte[] fileBytes = routineFile.getBytes();
        String base64Encoded = java.util.Base64.getEncoder().encodeToString(fileBytes);

        // Get MIME type of uploaded file
        String mimeType = routineFile.getContentType();

        // Set base64 string with MIME type prefix
        examRequestDto.setRoutine("data:" + mimeType + ";base64," + base64Encoded);
    }

    ResponseDto examResponseDto = examService.addExam(examRequestDto);
    return ResponseEntity.ok(examResponseDto);
}


    @GetMapping(GET_EXAM)
    public ResponseEntity<DetailedResponse> getExamById(@PathVariable int examId) {
        DetailedResponse response = examService.getExamById(examId);
        return ResponseEntity.ok(response);

    }

    @GetMapping(GET_ALL)
    public ResponseEntity<List<ResponseDto>> getAllExams() {
        List<ResponseDto> responseDtos = examService.getAllExams();
        return ResponseEntity.ok(responseDtos);
    }
    @DeleteMapping(DELETE_EXAM)
    public ResponseEntity<String> deleteExam(@PathVariable int examId) {
        examService.deleteExam(examId);
        return ResponseEntity.ok("Deleted Exam");
    }
}
