package seg3102.project.contracts.testStubs.repositories


import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import java.util.*


class PrescriptionFillRepositoryStub : PrescriptionFillRepository {
    private val prescriptionFills: MutableMap<UUID, PrescriptionFill> = HashMap()
    override fun save(prescriptionFill: PrescriptionFill): PrescriptionFill {
        prescriptionFills[prescriptionFill.id] = prescriptionFill
        return prescriptionFill
    }
    override fun find(id: UUID): PrescriptionFill? =prescriptionFills[id]

}