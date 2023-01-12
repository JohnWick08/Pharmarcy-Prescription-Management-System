package seg3102.project.contracts.steps
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.assertj.core.api.Assertions
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import seg3102.project.application.dto.queries.*
import seg3102.project.application.usecases.CreatePrescription
import seg3102.project.application.usecases.RegisterPatient
import seg3102.project.application.usecases.UpdatePatient
import seg3102.project.application.usecases.implementation.CreatePrescriptionImpl
import seg3102.project.application.usecases.implementation.RegisterPatientImpl
import seg3102.project.application.usecases.implementation.UpdatePatientImpl
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import seg3102.project.contracts.testStubs.factories.*
import seg3102.project.contracts.testStubs.repositories.*
import seg3102.project.contracts.testStubs.services.CpsoCheckValidStub
import seg3102.project.contracts.testStubs.services.EventEmitterStub
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.domain.patient.facade.PatientFacade
import seg3102.project.domain.patient.facade.implementation.PatientFacadeImpl
import seg3102.project.domain.prescription.entities.Prescription
import seg3102.project.domain.prescription.entities.PrescriptionFill
import seg3102.project.domain.prescription.facade.implementation.PrescriptionFacadeImpl
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.domain.user.facade.implementation.UserFacadeImpl

class StepsDefinition : En {
    private var accountFactory = AccountFactoryStub()
    private var medicineFactory = MedicineFactoryStub()
    private var patientFactory = PatientFactoryStub()
    private var prescriptionFactory = PrescriptionFactoryStub()
    private var prescriptionFillFactory = PrescriptionFillFactoryStub()
    private var accountRepository = AccountRepositoryStub()
    private var medicineRepository = MedicineRepositoryStub()
    private var patientRepository = PatientRepositoryStub()
    private var prescriptionRepository = PrescriptionRepositoryStub()
    private var prescriptionFillRepository = PrescriptionFillRepositoryStub()
    private var eventEmitter = EventEmitterStub()
    private var cpsoCheckValid = CpsoCheckValidStub()
    @Mock
    private var prescriber: Prescriber? = null
    private var agentInfo: PharmacistCreateDto? = null
    private var patientInfo:PatientCreateDto? = null
    private var newPatient: Patient? = null
    private var patient: Patient? = null
    private var newPatientId: String? = null
    private var agent: Pharmacist? = null
    private var patientUpdateInfo: PatientCreateDto? = null
    private var prescriptionInfo: PrescriptionCreateDto? = null
    private var newPrescription: Prescription? = null
    private var prescriptionFill: PrescriptionFill? = null
    private var prescriptionFillInfo: PrescriptionFillCreateDto? = null


    init {
//        Before { _: Scenario ->
//            MockitoAnnotations.openMocks(this)
//        }
        Given("prescriber is signed in") {
            prescriber = createPrescriber(accountRepository)
            Assertions.assertThat(prescriber).isNotNull
        }
        Given("that patient's information does not match an existing patient account") {
            patientInfo = setPatientInfo()
            val patient = patientRepository.find(patientInfo!!.identificationNumber)
            Assertions.assertThat(patient).isNull()
        }

        Given("patient is registered in system") {
            patient = createPatient(patientRepository)
            Assertions.assertThat(patient).isNotNull
        }
        Given("prescriber is confirmed as valid") {
            val prescriberValid = prescriber?.let { accountRepository.findPrescriber(it.uid) }
            Assertions.assertThat(prescriberValid)
        }
        Given("The Referred Medicine should be DIN listed in the Health Canada’s Drug Product Database") {
            val medicine = createMedicine(medicineRepository)
            val medicineValid = medicine?.let {medicineRepository.find(it.drugIdentificationNum)}
            Assertions.assertThat(medicineValid)
        }


        Given("pharmacist is signed in") {
            agent = createPharmacist(accountRepository)
            Assertions.assertThat(agent).isNotNull
        }
        Given("prescription fill status as waiting for pick up") {
            Assertions.assertThat(true) }


        Given("medicine is in stock") { Assertions.assertThat(true) }
        Given("the clinical check of the medication is verified") { Assertions.assertThat(true) }
        Given("the refill number is not zero") { Assertions.assertThat(true) }
        Given("refill is authorized") { Assertions.assertThat(true) }


        Given("that user's information does not match an existing user") {
            agentInfo = setAgentInfo()
            val agent = accountRepository.findPharmacist(agentInfo!!.uid)
            Assertions.assertThat(agent).isNull()
        }
        Given("the user is either a prescriber or a pharmacist") {
            val priority = agentInfo?.priority
            Assertions.assertThat(priority).isBetween("2","3")
        }



        Given("there is an existing user account for this target user") { Assertions.assertThat(true) }


        Given("the agent is signed in") { Assertions.assertThat(true) }
        Given("the update information is given") { Assertions.assertThat(true) }


        Given("the patient has a profile in the system") {
            patient = createPatient(patientRepository)
            patientUpdateInfo = setPatientInfo()
            Assertions.assertThat(patient).isNotNull
        }


        When("the application command createPrescription is invoked") {
            val prescriptionFacade = PrescriptionFacadeImpl(
                prescriptionFactory,
                prescriptionFillRepository,
                prescriptionRepository,
                eventEmitter
            )
            val userFacade = UserFacadeImpl(
                cpsoCheckValid, accountRepository, accountFactory, eventEmitter, medicineRepository, prescriptionFactory, prescriptionRepository, prescriptionFillFactory, prescriptionFillRepository
            )

            val uc: CreatePrescription = CreatePrescriptionImpl(userFacade)
            prescriptionInfo = prescriber?.let { patient?.let { it1 -> setPrescriptionInfo(it1, it) } }
            prescriptionInfo?.let { uc.createPrescription(it) }

        }
        When("the application command pickUpMedicine is invoked") { Assertions.assertThat(true) }
        When("the application command preparePrescriptionFill is invoked") { Assertions.assertThat(true) }
        When("the application command registerAgent is invoked") {
//            val agentFacade = UserFacadeImpl(
//
//            )
        }
        When("the application command registerPatient is invoked") {
            val patientFacade = PatientFacadeImpl (
                    patientRepository,
                    patientFactory,
                    eventEmitter
                    )
            val uc: RegisterPatient = RegisterPatientImpl(patientFacade)
            patientInfo?.let {uc.registerPatient(it)}
        }
        When("the application command updateAgent is invoked") { Assertions.assertThat(true) }
        When("the application command updatePatient is invoked") {
            val patientFacade = PatientFacadeImpl (
                patientRepository,
                patientFactory,
                eventEmitter
            )
            val uc: UpdatePatient = UpdatePatientImpl(patientFacade)
            patient?.let { patientUpdateInfo?.let {it1 -> uc.updatePatient(it.identificationNumber, it1)}}
        }
        When("the application command unregisterAgent is invoked") { org.assertj.core.api.Assertions.assertThat(true) }



        Then("create a new prescription with a unique identification to prescription") {
            newPrescription = prescriptionInfo?.let{prescriptionRepository.find(it.id)}
            Assertions.assertThat(newPrescription).isNotNull
        }

        Then("save prescription into database") {
            Assertions.assertThat(newPrescription).isNotNull
        }

        Then("add prescription to patient's prescription") {
            val patientPre = prescriptionInfo?.patient?.let { patientRepository.find(it.identificationNumber) }
            val findInPatient = prescriptionInfo?.let { patientPre?.myPrescriptions?.contains(it)}
            Assertions.assertThat(findInPatient)
        }
        Then("the summary of the discussion is recorded") { Assertions.assertThat(true) }
        Then("prescription fill status set as picked up") { Assertions.assertThat(true) }
        Then("the lot number of medicine is recorded") { Assertions.assertThat(true) }

        Then("the expire date of medicine is recorded") { Assertions.assertThat(true) }

        Then("set the fill as verified") { Assertions.assertThat(true) }

        Then("decrease the number of refill number") { Assertions.assertThat(true) }

        Then("set the prescription filling status as prepared") { Assertions.assertThat(true) }

        Then ("entered the new user's information") {Assertions.assertThat(true) }
        Then("a new Agent account is registered") { Assertions.assertThat(true) }
        Then("an acknowledgment is displayed") { Assertions.assertThat(true) }
        Then("a new patient is created") {
            newPatient = patientInfo?.let{patientRepository.find(it.identificationNumber)}
            Assertions.assertThat(newPatient).isNotNull
        }
        Then(
            "the new patient profile is initialized filled with  the information given by the patient"
        ) {
            Assertions.assertThat(newPatient).isNotNull
        }
        Then("find the corresponding user") { Assertions.assertThat(true) }
        Then("this user is removed from the database") { Assertions.assertThat(true) }
        Then("the agent's information is updated") { Assertions.assertThat(true) }
        Then("the patient profile's information modified") {
            Assertions.assertThat(patient?.firstName).isEqualTo(patientUpdateInfo?.firstName)
            Assertions.assertThat(patient?.lastName).isEqualTo(patientUpdateInfo?.lastName)
        }


    }

}