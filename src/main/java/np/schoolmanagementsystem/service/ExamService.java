package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.ExamRequestDto;
import np.schoolmanagementsystem.dto.ResponseDto;
import np.schoolmanagementsystem.dto.DetailedResponse;

import java.util.List;

public interface ExamService {
    ResponseDto addExam(ExamRequestDto examRequestDto);
    List<ResponseDto> getAllExams();
    DetailedResponse getExamById(int examId);
    ResponseDto updateExam(ExamRequestDto examRequestDto);
    void deleteExam(int examId);

}
