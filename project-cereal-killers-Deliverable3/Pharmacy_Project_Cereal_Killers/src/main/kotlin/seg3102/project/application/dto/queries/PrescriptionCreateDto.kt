package seg3102.project.application.dto.queries
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.user.entities.Prescriber
import java.util.*
data class PrescriptionCreateDto(
    val id: UUID,
    val patient: Patient,
    var prescriptionFills: MutableList<UUID> = ArrayList(),
    var endDate: Date,
    val prescriber: Prescriber,
    val date: String,
    val medicineDIN: UUID,
    val medicineName: String,
    val medicineStrength: String,
    val medicineAmount: Int,
    val methodOfAdministration: String,
    val frequencyOfAdministration: String,
    val optionConsideration: String,
    val refillable: Boolean,
    val timesCanBeFilled: Int,
    var totalTimesPickedUp: Int,
    val authorization: Boolean,
    var isVerified: Boolean){
}