package softuni.adoptdontshop.Service.Impl;

import org.springframework.stereotype.Service;
import softuni.adoptdontshop.Model.Entity.MedicalRecord;
import softuni.adoptdontshop.Model.Enum.MedicalRecordEnum;
import softuni.adoptdontshop.Repository.MedicalRecordRepository;
import softuni.adoptdontshop.Service.MedicalRecordService;

@Service
public class MedicalRecordServiceImpl implements MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalRecordServiceImpl(MedicalRecordRepository medicalRecordRepository) {
        this.medicalRecordRepository = medicalRecordRepository;
    }

    @Override
    public MedicalRecord findMedicalRecord(MedicalRecordEnum medicalRecordEnum) {
        return medicalRecordRepository
                .findByName(medicalRecordEnum)
                .orElse(null);
    }
}
