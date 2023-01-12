package seg3102.project.domain.medicine.repositories

import seg3102.project.domain.medicine.entities.Medicine
import seg3102.project.domain.prescription.entities.Prescription
import java.math.BigInteger
import java.util.*

interface MedicineRepository{
    
    fun save(medicine: Medicine): Medicine
    fun find(id: UUID): Medicine?
}