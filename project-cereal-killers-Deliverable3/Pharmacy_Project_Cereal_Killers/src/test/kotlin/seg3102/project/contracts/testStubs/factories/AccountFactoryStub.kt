package seg3102.project.contracts.testStubs.factories

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.factories.AccountFactory

class AccountFactoryStub : AccountFactory {
    override fun createPharmacistAccount(accountInfo: PharmacistCreateDto): Pharmacist {
        return Pharmacist(accountInfo.uid,
            accountInfo.email,
            accountInfo.password,
            accountInfo.name,
            accountInfo.priority,
            accountInfo.isSignedIn)
    }

    override fun createPrescriberAccount(accountInfo: PrescriberCreateDto): Prescriber {
        return Prescriber(accountInfo.uid,
            accountInfo. email,
            accountInfo. password,
            accountInfo. name,
            accountInfo. priority,
            accountInfo. isSignedIn)
    }
}