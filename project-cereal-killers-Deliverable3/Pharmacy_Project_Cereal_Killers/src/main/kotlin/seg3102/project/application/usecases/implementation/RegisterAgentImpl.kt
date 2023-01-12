package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.usecases.RegisterAgent
import seg3102.project.domain.user.entities.Admin
import seg3102.project.domain.user.factories.AccountFactory
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

class RegisterAgentImpl(
    var admain:Admin
) : RegisterAgent {
    override fun registerAgent(agentInfo: PharmacistCreateDto, factory: AccountFactory, repository: AccountRepository): UUID{
        return admain.registerAgent(agentInfo,factory,repository);
    }
    override fun registerAgent(agentInfo: PrescriberCreateDto, factory: AccountFactory, repository: AccountRepository): UUID{
        return admain.registerAgent(agentInfo,factory,repository);
    }
}