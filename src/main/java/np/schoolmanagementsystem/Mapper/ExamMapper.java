package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.ExamRequestDto;
import np.schoolmanagementsystem.dto.ResponseDto;
import np.schoolmanagementsystem.dto.DetailedResponse;
import np.schoolmanagementsystem.entity.Exam;

public class ExamMapper {
    public static Exam mapToExam(ExamRequestDto examRequestDto) {
        Exam exam = new Exam(
               examRequestDto.getId(),
                examRequestDto.getSubjectId(),
                examRequestDto.getClassroomId(),
                examRequestDto.getExamDate(),
                examRequestDto.getExamType(),
                examRequestDto.getFullMarks(),
                examRequestDto.getPassMark()
        );
        return exam;
    }
    public static ExamRequestDto mapToExamRequestDto(Exam exam) {
        ExamRequestDto examRequestDto = new ExamRequestDto(
                exam.getExamId(),
                exam.getClassroomId(),
                exam.getSubjectId(),
                exam.getExamDate(),
                exam.getExamType(),
                exam.getFullMarks(),
                exam.getPassMark()
        );
        return examRequestDto;
    }

    public static ResponseDto mapToExamResponseDto(Exam exam) {
        ResponseDto examResponseDto = new ResponseDto(
                exam.getExamId(),
                exam.getSubjectId(),
                exam.getClassroomId(),
                exam.getExamDate()
        );
        return examResponseDto;
    }

    public static DetailedResponse mapToDetailedResponse(Exam exam) {
        DetailedResponse detailedExamResponse = new DetailedResponse();
        detailedExamResponse.setId(exam.getExamId());
        detailedExamResponse.setSubjectId(exam.getSubjectId());
        detailedExamResponse.setClassroomId(exam.getClassroomId());
        detailedExamResponse.setExamDate(exam.getExamDate());
        detailedExamResponse.setExamType(exam.getExamType());
        detailedExamResponse.setFullMarks(exam.getFullMarks());
        detailedExamResponse.setPassMark(exam.getPassMark());

        return detailedExamResponse;
    }
}
