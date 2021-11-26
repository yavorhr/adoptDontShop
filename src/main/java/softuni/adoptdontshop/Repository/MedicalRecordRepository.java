package softuni.adoptdontshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.adoptdontshop.Model.Entity.MedicalRecord;
import softuni.adoptdontshop.Model.Enum.MedicalRecordEnum;

import java.util.Optional;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Long> {

    Optional<MedicalRecord> findByName(MedicalRecordEnum name);
}
