package seg3102.project.domain.medicine.facade

import seg3102.project.application.dto.queries.MedicineCreateDto
interface MedicineFacade{
    fun createMedicine(medicineInfo: MedicineCreateDto): Boolean
}