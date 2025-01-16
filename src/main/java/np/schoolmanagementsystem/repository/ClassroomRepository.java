package np.schoolmanagementsystem.repository;

import np.schoolmanagementsystem.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Optional<Classroom> findById(Long ClassroomId);

}
