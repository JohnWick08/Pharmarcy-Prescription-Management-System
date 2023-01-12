package seg3102.project.domain.prescription.repositories

import seg3102.project.domain.prescription.entities.Prescription
import java.util.*

interface PrescriptionRepository {
    fun save(prescription: Prescription): Prescription
    fun find(id: UUID): Prescription?
}