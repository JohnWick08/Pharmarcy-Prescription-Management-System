package seg3102.project.domain.patient.events

import seg3102.project.domain.common.DomainEvent
import java.util.*

class NewPatient(
    val identificationNumber:String,
    val occuredOn: Date
): DomainEvent