package np.schoolmanagementsystem.service;

import np.schoolmanagementsystem.dto.SubjectDto;
import np.schoolmanagementsystem.entity.Subject;

import java.util.List;

public interface SubjectService {
    SubjectDto addSubject(SubjectDto subjectDto);

    List<SubjectDto> getAllSubjects();

    SubjectDto getSubjectById(String SubjectId);

    SubjectDto updateSubject(SubjectDto subjectDto, String id);

    SubjectDto deleteSubject(String id);


}
