package seg3102.project.domain.prescription.services

import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

interface CpsoCheckValid {
    fun checkValid(prescriberID: UUID, accountRepository: AccountRepository) : Boolean
}