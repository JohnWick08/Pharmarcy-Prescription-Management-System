package seg3102.project.domain.user.repositories

import seg3102.project.domain.user.entities.Admin
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import java.util.*

interface AccountRepository {
    fun findPharmacist(userId: UUID):Pharmacist?
    fun findPrescriber(userId: UUID): Prescriber?
    fun savePharmacist(account: Pharmacist): Pharmacist
    fun savePrescriber(account: Prescriber): Prescriber

    fun saveAdmin(account: Admin): Admin

}

