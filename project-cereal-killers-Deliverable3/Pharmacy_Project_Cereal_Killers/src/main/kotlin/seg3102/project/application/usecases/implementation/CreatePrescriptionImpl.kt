package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PatientCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionCreateDto
import seg3102.project.application.usecases.CreatePrescription
import seg3102.project.domain.user.facade.UserFacade

class CreatePrescriptionImpl (
    var userfacade: UserFacade
): CreatePrescription{
    override fun createPrescription(prescriptionInfo: PrescriptionCreateDto):Boolean{
        return userfacade.createPrescription(prescriptionInfo);

    }
}