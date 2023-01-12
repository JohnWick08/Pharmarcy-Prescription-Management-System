package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.usecases.UnRegisterAgent
import seg3102.project.domain.user.entities.Admin
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

class UnRegisterAgentImpl (
    var admain: Admin
        ): UnRegisterAgent{
    override fun unregisterAgent(agentInfo: PharmacistCreateDto, repository: AccountRepository){
        admain.unregisterAgent(agentInfo.uid,repository);
    }
    override fun unregisterAgent(agentInfo: PrescriberCreateDto, repository: AccountRepository){
        admain.unregisterAgent(agentInfo.uid,repository);
    }
}