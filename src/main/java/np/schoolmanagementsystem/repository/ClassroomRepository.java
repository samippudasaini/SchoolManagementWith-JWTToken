package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findByid(Long id);
}
