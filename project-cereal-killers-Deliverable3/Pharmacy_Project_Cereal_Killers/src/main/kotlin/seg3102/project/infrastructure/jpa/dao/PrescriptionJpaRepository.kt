package seg3102.project.infrastructure.jpa.dao
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import seg3102.project.infrastructure.web.forms.PrescriberForm
import seg3102.project.infrastructure.web.forms.PrescriptionForm

@Repository
interface PrescriptionJpaRepository:CrudRepository<PrescriptionForm, String> {
}