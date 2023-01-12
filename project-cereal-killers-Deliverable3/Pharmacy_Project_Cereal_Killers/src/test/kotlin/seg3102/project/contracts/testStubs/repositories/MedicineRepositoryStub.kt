package seg3102.project.contracts.testStubs.repositories

import seg3102.project.domain.medicine.entities.Medicine
import seg3102.project.domain.medicine.repositories.MedicineRepository
import java.util.*

class MedicineRepositoryStub : MedicineRepository{
    private val medicines: MutableMap<UUID, Medicine> = HashMap()
    override fun save(medicine: Medicine): Medicine {
        medicines[medicine.drugIdentificationNum] = medicine
        return medicine
    }

    override fun find(id: UUID): Medicine? =medicines[id]


}