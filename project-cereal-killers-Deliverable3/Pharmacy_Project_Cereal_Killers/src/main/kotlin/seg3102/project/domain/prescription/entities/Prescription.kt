package seg3102.project.domain.prescription.entities

import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.user.entities.Prescriber
import java.util.*
class Prescription(
    val id: UUID,
    val patient: Patient,
    val prescriber: Prescriber,
    var prescriptionFills: MutableList<UUID> = ArrayList(),

    //start date
    val date: String,
    var endDate: Date,
    val medicineDIN: UUID,
    val medicineName: String,
    val medicineStrength: String,
    var medicineAmount: Int,
    var methodOfAdministration: String,
    var frequencyOfAdministration: String,
    var optionConsideration: String,

    //varaible for if the drug can be refilled
    var refillable: Boolean,

    //how many times a medicine can be refilled
    var timesCanBeFilled: Int,

    //how many times a medicine has been picked up
    var totalTimesPickedUp: Int,

    val authorization: Boolean,
    var isVerified: Boolean)
{
    fun pickOnce(){
        totalTimesPickedUp++
        timesCanBeFilled--
        if(timesCanBeFilled<=0)
            refillable = false
        endDate = java.sql.Timestamp(System.currentTimeMillis())
    }
}