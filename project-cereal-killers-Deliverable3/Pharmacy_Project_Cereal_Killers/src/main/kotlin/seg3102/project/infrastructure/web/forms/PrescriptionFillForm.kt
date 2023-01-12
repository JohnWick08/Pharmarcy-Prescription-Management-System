package seg3102.project.infrastructure.web.forms
import java.util.*
import javax.persistence.*

@Entity
@Table(name ="prescriptionFills")
class PrescriptionFillForm (
    @Id
    val id: UUID?=null,
    var patientid: String?= null,
    var prescriptionid: String?= null,
    var status: String?=null,
    var verified: Boolean ?=null,
    var readyforpickup: Boolean ?=null,
    var summary: String ?=null
    ){

}