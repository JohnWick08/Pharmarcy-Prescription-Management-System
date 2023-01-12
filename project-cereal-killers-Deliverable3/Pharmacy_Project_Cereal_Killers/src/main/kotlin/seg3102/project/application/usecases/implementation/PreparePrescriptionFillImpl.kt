package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.application.usecases.PreparePrescriptionFill
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.facade.UserFacade
import java.util.*

class PreparePrescriptionFillImpl (
    var userfacade: UserFacade
        ): PreparePrescriptionFill {
    override fun preparePrescriptionFill(
        prescriber: PrescriberCreateDto, prescriptionFillInfo: PrescriptionFillCreateDto
    ): Boolean{
        return userfacade.preparePrescriptionFill(prescriber.uid,prescriptionFillInfo)
    }
}