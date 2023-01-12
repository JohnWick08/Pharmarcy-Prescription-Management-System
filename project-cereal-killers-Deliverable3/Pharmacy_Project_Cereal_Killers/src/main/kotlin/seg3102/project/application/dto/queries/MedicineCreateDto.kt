package seg3102.project.application.dto.queries

import java.util.*

data class MedicineCreateDto (
    val name: String,
    val drugIdentificationNum: UUID,
    var stock: Int,
    var numberOfPrescriptionMade:Int,

    val alternativeMedicine: UUID ) {
       
}  