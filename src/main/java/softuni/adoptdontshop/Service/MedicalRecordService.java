package softuni.adoptdontshop.Service;

import softuni.adoptdontshop.Model.Entity.MedicalRecord;
import softuni.adoptdontshop.Model.Enum.MedicalRecordEnum;

public interface MedicalRecordService {

    MedicalRecord findMedicalRecord (MedicalRecordEnum medicalRecordEnum);
}
