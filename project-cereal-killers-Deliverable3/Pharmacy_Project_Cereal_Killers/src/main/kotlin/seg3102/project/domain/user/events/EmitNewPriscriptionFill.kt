package seg3102.project.domain.user.events

import seg3102.project.domain.common.DomainEvent
import java.util.*

class EmitNewPrescriptionFill(val id: UUID): DomainEvent {
}