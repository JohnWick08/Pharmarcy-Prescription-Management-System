package seg3102.project.application.usecases

import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.application.dto.queries.PrescriptionFillCreateDto
import java.util.*

interface PreparePrescriptionFill {
    fun preparePrescriptionFill(
        prescriber: PrescriberCreateDto, prescriptionFillInfo: PrescriptionFillCreateDto
    ): Boolean
}