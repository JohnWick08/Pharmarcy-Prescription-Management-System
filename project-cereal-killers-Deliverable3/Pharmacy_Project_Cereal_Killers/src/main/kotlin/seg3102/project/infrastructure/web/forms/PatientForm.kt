package seg3102.project.infrastructure.web.forms

import javax.persistence.*

@Entity
@Table(name ="patients")
class PatientForm(
    @Id
    var identificationnumber: String ="1",
    var firstname: String?=null,
    var lastname: String?=null,
    var address:String?=null,
    var gender:String?=null,
    val dateofbirth: String?=null,
    val languagepreference: String?=null,
    var drugallergies:String?=null,
    var currentmedications: String?=null,
    var insurancenumber: String?=null

){
}
