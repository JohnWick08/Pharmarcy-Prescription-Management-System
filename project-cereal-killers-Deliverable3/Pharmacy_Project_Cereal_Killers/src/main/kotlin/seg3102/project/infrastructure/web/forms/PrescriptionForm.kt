package seg3102.project.infrastructure.web.forms

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.format.annotation.DateTimeFormat
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.user.entities.Prescriber
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name ="prescriptions")
class PrescriptionForm (
    @Id
    var id : UUID?=null,
    var patientid: String?=null,
    var prescriberid: UUID?=null,
    var startdate: Date?=null,
    var enddate: Date?=null,
    var medicinedin: String?=null,
    var medicinename: String?=null,
    var medicinestrength: String?=null,
    var medicineamount: Int?=null,
    var methodofadministration: String?=null,
    var frequencyofadministration: String?=null,
    var optionalconsiderations: String?=null,
    var refillable: Boolean?=null,
    var refillablecount: Int?=0,
    var totaltimespickedup: Int?=0,
    var authorizationfiled: Boolean?=null,
    var isverified: Boolean?=null

){
}