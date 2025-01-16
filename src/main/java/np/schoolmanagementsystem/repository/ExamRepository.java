package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ExamRepository extends JpaRepository<Exam, Integer> {

    Exam findByExamId(int examId);

}
