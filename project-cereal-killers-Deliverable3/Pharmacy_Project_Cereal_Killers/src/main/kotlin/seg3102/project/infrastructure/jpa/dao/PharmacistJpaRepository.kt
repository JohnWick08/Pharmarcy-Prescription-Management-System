package seg3102.project.infrastructure.jpa.dao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import seg3102.project.infrastructure.web.forms.PharmacistForm
import java.util.*

@Repository
interface PharmacistJpaRepository : CrudRepository<PharmacistForm, UUID>{
}