package seg3102.project.application.dto.queries

import java.util.*

class PrescriberCreateDto(
    val uid: UUID,
    val email: String,
    val password: String,
    val name: String,
    val priority: String,
    val isSignedIn: Boolean,
    var role: String,
    var licenceNumber:String,
    var title:String,
    var address:String,
    var telephoneNumber:String
){}