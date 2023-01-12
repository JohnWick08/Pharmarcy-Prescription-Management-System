package seg3102.project.application.dto.queries

import java.util.*

class PharmacistCreateDto(
    val uid: UUID,
    val email: String,
    val password: String,
    val name: String,
    val priority: String,
    val isSignedIn: Boolean,
){
}