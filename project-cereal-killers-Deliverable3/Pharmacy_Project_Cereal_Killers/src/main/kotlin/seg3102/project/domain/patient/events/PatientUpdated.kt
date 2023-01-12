package seg3102.project.domain.patient.events
import seg3102.project.domain.common.DomainEvent

import java.util.*

class PatientUpdated (
    val patientID:String,
    val occuredOn: Date
): DomainEvent