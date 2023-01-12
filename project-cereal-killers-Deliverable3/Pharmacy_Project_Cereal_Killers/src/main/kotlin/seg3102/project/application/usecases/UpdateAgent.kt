package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import java.util.*

interface UpdateAgent {
    fun updateAgent(uid: UUID, info: PrescriberCreateDto):Boolean
    fun updateAgent(uid: UUID, info: PharmacistCreateDto):Boolean
}