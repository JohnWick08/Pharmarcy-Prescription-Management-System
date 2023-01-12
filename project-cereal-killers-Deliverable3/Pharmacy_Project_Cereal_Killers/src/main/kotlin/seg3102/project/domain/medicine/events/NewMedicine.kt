package seg3102.project.domain.medicine.events


import seg3102.project.domain.common.DomainEvent
import java.util.*

class NewMedicine(val id: UUID,
                       val occuredOn: Date): DomainEvent {

                }

            
//import seg3x02.auctionsystem.domain.common.DomainEvent