package seg3102.project.application.usecases.implementation

import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import seg3102.project.application.usecases.PickUpMedcine
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.user.entities.Pharmacist

class PickUpMedicineImpl (
    var pharmacist: Pharmacist
        ): PickUpMedcine {
    override fun pickUpMedicine(prescriptionFill: PrescriptionFillCreateDto, summary:String, repository: PrescriptionFillRepository):Boolean{
        return pharmacist.pickUpMedicine(prescriptionFill.id, summary,repository);
    }

}