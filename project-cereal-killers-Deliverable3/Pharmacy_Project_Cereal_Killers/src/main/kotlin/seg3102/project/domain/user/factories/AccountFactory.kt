package seg3102.project.domain.user.factories

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber

interface AccountFactory {
    fun createPharmacistAccount(accountInfo: PharmacistCreateDto): Pharmacist
    fun createPrescriberAccount(accountInfo: PrescriberCreateDto): Prescriber

}