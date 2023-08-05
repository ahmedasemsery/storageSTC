package stc.assessments.storagestc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stc.assessments.storagestc.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
