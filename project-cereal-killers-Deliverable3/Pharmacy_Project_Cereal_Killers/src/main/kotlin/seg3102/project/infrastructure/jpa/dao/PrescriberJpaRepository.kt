package seg3102.project.infrastructure.jpa.dao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import seg3102.project.infrastructure.web.forms.PatientForm
import seg3102.project.infrastructure.web.forms.PrescriberForm
import java.util.*


@Repository
interface PrescriberJpaRepository: CrudRepository<PrescriberForm, String> {
    fun findByEmail (email:String): Optional<PrescriberForm>

    fun existsByEmail (email:String):Boolean

    @Transactional
    fun deleteByEmail(Email:String)

}
