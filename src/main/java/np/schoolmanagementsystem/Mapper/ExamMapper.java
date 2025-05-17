package np.schoolmanagementsystem.Mapper;

import np.schoolmanagementsystem.dto.DetailedResponse;
import np.schoolmanagementsystem.dto.ExamRequestDto;
import np.schoolmanagementsystem.dto.ResponseDto;
import np.schoolmanagementsystem.entity.Exam;

import java.util.Base64;

public class ExamMapper {

    public static Exam mapToExam(ExamRequestDto dto) {
        Exam exam = new Exam();
        exam.setExamId(dto.getId());
        exam.setSubjectId(dto.getSubjectId());
        exam.setClassroomId(dto.getClassroomId());
        exam.setExamDate(dto.getExamDate());
        exam.setExamType(dto.getExamType());
        exam.setFullMarks(dto.getFullMarks());
        exam.setPassMark(dto.getPassMark());

        if (dto.getRoutine() != null && !dto.getRoutine().isEmpty()) {
            try {
                String base64Data = dto.getRoutine().split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(base64Data);
                exam.setRoutine(imageBytes);

                // Extract MIME type from data URI prefix
                String mimeType = dto.getRoutine().split(";")[0].split(":")[1];
                exam.setRoutineMimeType(mimeType);
            } catch (Exception e) {
                throw new RuntimeException("Invalid base64 routine format");
            }
        }


        return exam;
    }

    public static ResponseDto mapToExamResponseDto(Exam exam) {
        ResponseDto dto = new ResponseDto();
        dto.setId(exam.getExamId());
        dto.setSubjectId(exam.getSubjectId());
        dto.setClassroomId(exam.getClassroomId());
        dto.setExamDate(exam.getExamDate());
        dto.setExamType(exam.getExamType());
        dto.setFullMarks(exam.getFullMarks());
        dto.setPassMark(exam.getPassMark());

        if (exam.getRoutine() != null && exam.getRoutineMimeType() != null) {
            String base64String = Base64.getEncoder().encodeToString(exam.getRoutine());
            dto.setBase64Routine("data:" + exam.getRoutineMimeType() + ";base64," + base64String);
        }


        return dto;
    }

    public static DetailedResponse mapToDetailedResponse(Exam exam) {
        DetailedResponse dto = new DetailedResponse();
        dto.setId(exam.getExamId());
        dto.setSubjectId(exam.getSubjectId());
        dto.setClassroomId(exam.getClassroomId());
        dto.setExamDate(exam.getExamDate());
        dto.setExamType(exam.getExamType());
        dto.setFullMarks(exam.getFullMarks());
        dto.setPassMark(exam.getPassMark());

        if (exam.getRoutine() != null && exam.getRoutineMimeType() != null) {
            String base64String = Base64.getEncoder().encodeToString(exam.getRoutine());
            dto.setBase64Routine("data:" + exam.getRoutineMimeType()+";base64,"+base64String);
        }

        return dto;
    }
}
