package seg3102.project.domain.prescription.events

import seg3102.project.domain.common.DomainEvent
import java.util.*

class PrescriptionFillSummaryUpdated (val id: UUID,
                                      val occuredOn: Date,
                                      val userId: UUID): DomainEvent {
}