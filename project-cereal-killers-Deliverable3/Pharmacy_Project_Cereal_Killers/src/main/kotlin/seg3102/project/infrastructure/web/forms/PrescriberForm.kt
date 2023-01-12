package seg3102.project.infrastructure.web.forms

import java.util.*
import javax.persistence.*

@Entity
@Table(name ="users")
class PrescriberForm (
    @Id
    val uid: UUID?=null,
    var email: String ?= null,
    var password: String ?= "000000",
    var name: String ?=null,
    var priority: String ?= "3",
    var issignedin: Boolean ?=false,
    var role: String ?= "Prescriber",
    var licencenumber:String?=null,
    var title:String ?=null,
    var address:String ?=null,
    var telephonenumber:String ?=null

){
}