package seg3102.project.contracts.testStubs.services

import seg3102.project.domain.prescription.services.CpsoCheckValid
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

class CpsoCheckValidStub : CpsoCheckValid {
    override fun checkValid(prescriberId: UUID, accountRepository: AccountRepository): Boolean {
        val prescriber = accountRepository.findPrescriber(prescriberId)
        return prescriber?.licenceNumber != null
    }
}