package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.domain.user.factories.AccountFactory
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

interface RegisterAgent {
    fun registerAgent(agentInfo: PharmacistCreateDto, factory: AccountFactory, repository: AccountRepository): UUID
    fun registerAgent(agentInfo: PrescriberCreateDto, factory:AccountFactory, repository:AccountRepository):UUID

}