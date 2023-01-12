package seg3102.project.domain.user.events

import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.domain.common.DomainEvent

class checkAllFilled(prescription:PrescriptionCreateDto) : DomainEvent