import seg3102.project.domain.common.DomainEvent
import java.util.*

class NewMedicineAdded(val id: UUID,
                       val occuredOn: Date,
                       val itemId: UUID): DomainEvent {

                }

            
//import seg3x02.auctionsystem.domain.common.DomainEvent