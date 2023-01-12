package seg3102.project.infrastructure.web.forms

import javax.persistence.*
import java.util.UUID

@Entity
@Table(name ="users")
class PharmacistForm(
    @Id
    val uid: UUID?=null,
    var email: String?=null,
    var password: String?="000000",
    var name:String?=null,
    var priority:String?="2",
    val issignedin: Boolean?=false,
    val role: String?="2",
    var licencenumber:String?=null,
    var title: String?=null,
    var address: String?=null,
    var telephonenumber: String?=null

){
}
