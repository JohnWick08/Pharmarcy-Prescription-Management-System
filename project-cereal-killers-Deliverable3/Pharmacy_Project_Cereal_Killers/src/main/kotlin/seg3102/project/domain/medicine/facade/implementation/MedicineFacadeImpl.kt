package seg3102.project.domain.medicine.facade.implementation

import seg3102.project.domain.medicine.facade.MedicineFacade
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.medicine.factories.MedicineFactory
import seg3102.project.domain.medicine.events.NewMedicine
import seg3102.project.application.dto.queries.MedicineCreateDto
import seg3102.project.application.services.DomainEventEmitter
import java.util.Date

class MedicineFacadeImpl(
    private val medicineRepository: MedicineRepository,
    private val medicineFactory: MedicineFactory,
    private var eventEmitter: DomainEventEmitter
): MedicineFacade {

    override fun createMedicine(medicineInfo: MedicineCreateDto): Boolean {
        val medicineId = medicineInfo.drugIdentificationNum
        val existPatient = medicineRepository.find(medicineId)
        if (existPatient != null) {
            return false
        }
        val newMedicine = medicineFactory.createMedicine(medicineInfo)
        medicineRepository.save(newMedicine)
        eventEmitter.emit(
            NewMedicine(
                medicineInfo.drugIdentificationNum,
                Date()
            )
        )
        return true
    }
}