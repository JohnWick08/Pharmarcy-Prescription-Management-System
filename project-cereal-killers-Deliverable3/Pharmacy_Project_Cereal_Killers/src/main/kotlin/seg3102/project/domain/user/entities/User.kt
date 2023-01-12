package seg3102.project.domain.user.entities

import java.util.*

open class User (
    val uid: UUID,
    var email: String,
    var password: String,
    var name: String,
    var priority: String,
    var isSignedIn: Boolean,
)
{
    fun signIn() {}
    fun signOut() {}

    /*
     * The promptForPasswordUpdate() method
     * parameter:       new password(String)
     * Functionality:   sets the new password
     * returns:         None
     */
    fun promptForPasswordUpdate(newPassword:String){
        password = newPassword
    }
}