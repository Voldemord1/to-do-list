package tech.artos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.artos.model.Task;

@Repository
public interface TaskJpaRepository extends JpaRepository<Task, Long> {

}
