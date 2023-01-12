package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.usecases.UpdateAgent
import seg3102.project.domain.user.facade.UserFacade
import java.util.*

class UpdateAgentImpl(private var userFacade: UserFacade) : UpdateAgent {
    override fun updateAgent(uid: UUID, info: PrescriberCreateDto): Boolean {
        return userFacade.updateAgent(uid, info)
    }

    override fun updateAgent(uid: UUID, info: PharmacistCreateDto): Boolean {
        return userFacade.updateAgent(uid, info)
    }
}