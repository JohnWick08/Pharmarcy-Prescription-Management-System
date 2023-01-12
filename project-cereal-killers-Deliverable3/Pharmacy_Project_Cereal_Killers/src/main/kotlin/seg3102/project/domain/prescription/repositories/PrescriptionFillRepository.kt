package seg3102.project.domain.prescription.repositories

import seg3102.project.domain.prescription.entities.PrescriptionFill
import java.util.*

interface PrescriptionFillRepository {
    fun save(prescriptionFill: PrescriptionFill): PrescriptionFill
    fun find(id: UUID): PrescriptionFill?
}