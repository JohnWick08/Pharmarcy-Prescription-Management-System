package seg3102.project.contracts.testStubs.repositories

import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.user.entities.Admin
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

class AccountRepositoryStub : AccountRepository{
    private val admins: MutableMap<UUID, Admin> = HashMap()
    override fun saveAdmin(account: Admin): Admin {
        admins[account.uid] = account
        return account
    }

    private val prescribers: MutableMap<UUID, Prescriber> = HashMap()
    override fun savePrescriber(account: Prescriber): Prescriber {
        prescribers[account.uid] = account
        return account
    }
    override fun findPrescriber(userId: UUID): Prescriber? =prescribers[userId]

    private val pharmacists: MutableMap<UUID, Pharmacist> = HashMap()
    override fun savePharmacist(account: Pharmacist): Pharmacist {
        pharmacists[account.uid] = account
        return account
    }
    override fun findPharmacist(userId: UUID): Pharmacist? =pharmacists[userId]

}