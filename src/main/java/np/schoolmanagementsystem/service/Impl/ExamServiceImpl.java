package np.schoolmanagementsystem.service.Impl;

import np.schoolmanagementsystem.CustomExcaption.CustomApplicationException;
import np.schoolmanagementsystem.Mapper.ExamMapper;
import np.schoolmanagementsystem.dto.ExamRequestDto;
import np.schoolmanagementsystem.dto.ResponseDto;
import np.schoolmanagementsystem.dto.DetailedResponse;
import np.schoolmanagementsystem.entity.Exam;
import np.schoolmanagementsystem.repository.ExamRepository;
import np.schoolmanagementsystem.service.ExamService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component

public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }


    @Override
    public ResponseDto addExam(ExamRequestDto examRequestDto) {
    Exam exam = ExamMapper.mapToExam(examRequestDto);
    examRepository.save(exam);
        ResponseDto examResponseDto = ExamMapper.mapToExamResponseDto(exam);
        examResponseDto.setCode(200);
        examResponseDto.setStatus(true);
        examResponseDto.setMessage("success");
        return examResponseDto;
    }

    @Override
    public List<ResponseDto> getAllExams() {

        List<Exam> exams = examRepository.findAll();
        if (exams.isEmpty()) {
            throw new CustomApplicationException(404,"not found");
        }

        List<ResponseDto> responseDtos = exams.stream()
                .map(ExamMapper::mapToExamResponseDto).collect(Collectors.toList());
        responseDtos.forEach(responseDto ->{
                responseDto.setCode(200);
                responseDto.setStatus(true);
                responseDto.setMessage("success");
                });
        return responseDtos;
    }

    @Override
    public DetailedResponse getExamById(int examId) {
        Exam exam = examRepository.findByExamId(examId);
        if (exam == null) {
            throw new CustomApplicationException(404,"ExamId not found");
        }
        DetailedResponse detailedExamResponse = ExamMapper.mapToDetailedResponse(exam);
        detailedExamResponse.setCode(200);
        detailedExamResponse.setStatus(true);
        detailedExamResponse.setMessage("success");
        return detailedExamResponse;
    }

    @Override
    public ResponseDto updateExam(ExamRequestDto examRequestDto) {
        Exam exam = examRepository.findByExamId(examRequestDto.getId());

        return ExamMapper.mapToExamResponseDto(exam);
    }

    @Override
    public void deleteExam(int examId) {
        Exam exam = examRepository.findByExamId(examId);
        if(exam == null) {
            throw new CustomApplicationException(404,"ExamId not found");
        }
        examRepository.delete(exam);
    }
}
