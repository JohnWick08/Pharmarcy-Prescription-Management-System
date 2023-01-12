package seg3102.project.domain.medicine.factories


import seg3102.project.application.dto.queries.MedicineCreateDto
import seg3102.project.domain.medicine.entities.Medicine
interface MedicineFactory {
    fun createMedicine(medicineInfo: MedicineCreateDto): Medicine
}