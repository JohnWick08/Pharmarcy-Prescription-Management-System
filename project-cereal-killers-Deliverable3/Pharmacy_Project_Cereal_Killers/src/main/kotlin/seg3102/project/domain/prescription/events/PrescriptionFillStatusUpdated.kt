package seg3102.project.domain.prescription.events

import seg3102.project.domain.common.DomainEvent
import java.util.*

class PrescriptionFillStatusUpdated (val id: UUID,
                                     val occuredOn: Date,
                                     val userId: String) : DomainEvent{
}