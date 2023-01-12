package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

interface UnRegisterAgent {
    fun unregisterAgent(agentInfo: PharmacistCreateDto, repository: AccountRepository)
    fun unregisterAgent(agentInfo: PrescriberCreateDto, repository: AccountRepository)
}