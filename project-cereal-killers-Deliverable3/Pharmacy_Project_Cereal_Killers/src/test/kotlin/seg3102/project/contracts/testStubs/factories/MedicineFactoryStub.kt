package seg3102.project.contracts.testStubs.factories

import seg3102.project.application.dto.queries.MedicineCreateDto
import seg3102.project.domain.medicine.entities.Medicine
import seg3102.project.domain.medicine.factories.MedicineFactory
import java.util.*

class MedicineFactoryStub:MedicineFactory {
    override fun createMedicine(medicineInfo: MedicineCreateDto): Medicine {
        return Medicine(medicineInfo.name,
            medicineInfo.drugIdentificationNum,
            medicineInfo.stock,
            medicineInfo.alternativeMedicine,
            medicineInfo.numberOfPrescriptionMade
        )

    }
}