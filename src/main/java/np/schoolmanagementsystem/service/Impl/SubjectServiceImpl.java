package np.schoolmanagementsystem.service.Impl;

import np.schoolmanagementsystem.CustomExcaption.CustomRuntimeException;
import np.schoolmanagementsystem.dto.SubjectDto;
import np.schoolmanagementsystem.entity.Subject;
import np.schoolmanagementsystem.repository.SubjectRepository;
import np.schoolmanagementsystem.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service


public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectDto addSubject(SubjectDto subjectDto) {
        Optional<Subject> existSubject = subjectRepository.findById(subjectDto.getSubjectId());
        if (existSubject.isPresent()) {
            throw new CustomRuntimeException("Subject already exists");
        }

        Subject subject = new Subject();
        subject.setSubjectId(subjectDto.getSubjectId());
        subject.setSubjectName(subjectDto.getSubjectName());

        Subject saveSubject = subjectRepository.save(subject);

        return new SubjectDto(saveSubject.getSubjectId(),
        saveSubject.getSubjectName());
    }

    @Override
    public List<SubjectDto> getAllSubjects() {
//        return subjectRepository.findAll();
       List<Subject> subjects = subjectRepository.findAll();
       return subjects.stream()
               .map(subject ->new  SubjectDto(
                       subject.getSubjectId(),
                       subject.getSubjectName()

               ))
               .collect(Collectors.toList());
    }

    @Override
    public SubjectDto getSubjectById(String  SubjectId) {
        Optional<Subject> existSubject = subjectRepository.findById(SubjectId);
        if (existSubject.isPresent()) {
            Subject subject = existSubject.get();
            return new SubjectDto(subject.getSubjectName(), subject.getSubjectId());
        }
        return null;
    }


    public SubjectDto updateSubject(SubjectDto subjectDto, String id) {
        if (subjectRepository.existsById(id)) {
            Subject subject = subjectRepository.findById(id).get();
            subject.setSubjectName(subjectDto.getSubjectName());
            subject =
                    subjectRepository.save(subject);
            subjectDto.setSubjectId(subject.getSubjectId());
            return subjectDto;
        }
        return null;
    }

    @Override
    public SubjectDto deleteSubject(@PathVariable String id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new CustomRuntimeException("Subject not found"));
        subjectRepository.delete(subject);

        return null;
    }
}
