package seg3102.project.contracts.steps


import seg3102.project.application.dto.queries.*
import seg3102.project.domain.medicine.entities.Medicine
import seg3102.project.domain.medicine.repositories.MedicineRepository
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.patient.repositories.PatientRepository
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.repositories.PrescriptionFillRepository
import seg3102.project.domain.user.entities.Admin
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*
import kotlin.collections.ArrayList


fun createAdmin(accountRepository: AccountRepository) : Admin {
    val adminId = UUID.randomUUID()
    val admin = Admin(
        adminId,
        "admin@admin.com",
        "password",
        "admin",
        "1",
        true
    )
    accountRepository.saveAdmin(admin)
    return admin
}

fun createPharmacist(accountRepository: AccountRepository) : Pharmacist {
    val pharId = UUID.randomUUID()
    val phar = Pharmacist(
        pharId,
        "phar@phar.com",
        "password",
        "pharmacist 1",
        "2",
        true
    )
    accountRepository.savePharmacist(phar)
    return phar
}

fun createPrescriber(accountRepository: AccountRepository) : Prescriber {
    val presId = UUID.randomUUID()
    val pres = Prescriber(
        presId,
        "pres@pres.com",
        "password",
        "prescriber 1",
        "3",
        true
    )
//    pres.licenceNumber = "AAA-BBB-CCC"
//    pres.address = "uottawa"
//    pres.role = "Medical Doctor"
//    pres.title = "Doctor"
//    pres.telephoneNumber = "1234567890"
    accountRepository.savePrescriber(pres)
    return pres
}

fun createMedicine(medicineRepository: MedicineRepository) : Medicine {
    val medDIN = UUID.randomUUID()
    val medicine = Medicine(
        "Tyleno",
        medDIN,
        100,
        UUID.randomUUID(),
        12
    )
    medicineRepository.save(medicine)
    return medicine
}


fun createPatient(patientRepository: PatientRepository) : Patient {
    var myPrescriptions = ArrayList<PrescriptionCreateDto>()
    val patient = Patient(
        "1234567890",
        "Adam",
        "Illness",
        "King Edward Ave",
        "2000/12/12",
        "English",
        "No allergies,",
        "No current Medicine",
        "111111111",
        myPrescriptions
    )
    patientRepository.save(patient)
    return patient
}

fun setPatientInfo() : PatientCreateDto {
    var prescriptions = ArrayList<PrescriptionCreateDto>()
    return PatientCreateDto(
        "12345678",
        "John",
        "Ho",
    "uottawa",
    "1999-09-09",
    "English",
    "No allergies,",
            "No current Medicine",
    "111111111",
        prescriptions)
}

fun setAgentInfo(): PharmacistCreateDto {
    return PharmacistCreateDto(
        UUID.randomUUID(),
    "Agent@agent.com",
    "12345678",
    "pharmacist",
        "2",
        true)
}

fun setPrescriptionInfo(patient: Patient, prescriber: Prescriber): PrescriptionCreateDto{
    var preFills = ArrayList<UUID>()
    var date = Date(2000)
    return PrescriptionCreateDto(
        UUID.randomUUID(),
        patient,
        preFills,
        date,
        prescriber,
        "2000/02/12",
        UUID.randomUUID(),
        "Amoxiling",
        "Niubi",
        10,
        "eat",
        "one day",
        "no",
        false,
        1,
        0,
        true,
        true
    )
}

fun setPrescriptionFillInfo(patient: Patient, prescription: Prescription) : PrescriptionFillCreateDto {
    return PrescriptionFillCreateDto( UUID.randomUUID(),
     prescription,
     "ready to pickup",
     true,
    true,
    "summary",
     patient)

}
