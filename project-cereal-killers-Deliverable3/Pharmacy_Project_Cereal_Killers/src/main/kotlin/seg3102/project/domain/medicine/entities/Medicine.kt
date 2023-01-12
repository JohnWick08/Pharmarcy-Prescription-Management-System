package seg3102.project.domain.medicine.entities

import java.util.*

class Medicine(val name: String,
               val drugIdentificationNum: UUID,
               var stock: Int,
               val alternativeMedicine: UUID,
               var numberOfPrescriptionMade:Int) {}
