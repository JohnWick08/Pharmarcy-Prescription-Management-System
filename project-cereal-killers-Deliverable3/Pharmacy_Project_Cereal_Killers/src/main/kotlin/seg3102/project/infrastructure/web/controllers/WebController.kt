package seg3102.project.infrastructure.web.controllers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*
import seg3102.project.domain.patient.entities.Patient
import seg3102.project.infrastructure.Expection.ResourceNotFoundException
import seg3102.project.domain.user.entities.Pharmacist
import seg3102.project.domain.user.entities.Prescriber
import seg3102.project.infrastructure.jpa.dao.*
import seg3102.project.infrastructure.web.forms.*
import java.util.*
import javax.servlet.http.HttpSession

@Controller
class WebController {
    @Autowired
    lateinit var PatientJpaRepository: PatientJpaRepository

    @Autowired
    lateinit var PrescriberJpaRepository: PrescriberJpaRepository

    @Autowired
    lateinit var PharmacistJpaRepository: PharmacistJpaRepository

    @Autowired
    private var jdbcTemplate: JdbcTemplate? = null

    @Autowired
    lateinit var precriptionJpaRepository: PrescriptionJpaRepository

    @Autowired
    lateinit var precriptionfillJpaRepository: PrescriptionFillJpaRepository



    @RequestMapping("/")
    fun homePage() : String{
        return "redirect:login"
    }

    @GetMapping("/createPatient")
    fun patientForm(model: Model): String? {
        model.addAttribute("newPatient", PatientForm())
        return "createPatient"
    }
    @PostMapping("/createPatient")
    fun patientForm(@ModelAttribute Patient: PatientForm?, model: Model): String? {
        model.addAttribute("newPatient", Patient)
        //print(Patient.address)
        PatientJpaRepository.save(PatientForm(Patient?.identificationnumber!!,Patient?.firstname,Patient?.lastname,Patient?.address,Patient?.gender,Patient?.dateofbirth,Patient?.languagepreference,Patient?.drugallergies,Patient?.currentmedications,Patient?.insurancenumber))
        return "result"
    }

    @RequestMapping("/findall")
    fun findAll() = PatientJpaRepository.findAll()

    @GetMapping("/createPrescriber")
    fun prescriberForm(model: Model): String? {
        model.addAttribute("newPrescriber", PrescriberForm())
        return "createPrescriber"
    }

    @PostMapping("/createPrescriber")
    fun prescriberForm(@ModelAttribute prescriber: PrescriberForm?, model: Model)  {
        model.addAttribute("newPrescriber", prescriber)
        //print(Patient.address)
        PrescriberJpaRepository.save(PrescriberForm(UUID.randomUUID(),prescriber?.email,prescriber?.password,prescriber?.name,prescriber?.priority,prescriber?.issignedin,prescriber?.role,prescriber?.licencenumber,prescriber?.title,prescriber?.address,prescriber?.telephonenumber))
        //return "result"
        //val sql = "INSERT INTO medicines (drugIdentificationNum, name) VALUES ('${UUID.randomUUID()}', 'hhhh')"
//        val sql = "SELECT * FROM USERS"
//        val result = jdbcTemplate?.queryForMap(sql)!!
//        model.addAttribute("result", result)
    }

    @GetMapping("/createPharmacist")
    fun pharmacistForm(model: Model) : String? {
        model.addAttribute("newPharmacist", PharmacistForm())
        return "createPharmacist";
    }

    @PostMapping("/createPharmacist")
    fun pharmacistFormSubmit(@ModelAttribute Pharmacist: PharmacistForm?, model: Model) {
        model.addAttribute("newPharmacist", Pharmacist)
        PharmacistJpaRepository.save(PharmacistForm(UUID.randomUUID(), Pharmacist?.email, Pharmacist?.password, Pharmacist?.name, Pharmacist?.priority, Pharmacist?.issignedin, Pharmacist?.role, Pharmacist?.licencenumber, Pharmacist?.title, Pharmacist?.address, Pharmacist?.telephonenumber))
        //return "result"
    }


    @GetMapping("/deletePatient")
    fun deletePatient(model: Model): String? {
        model.addAttribute("removePatient", PatientForm())
        return "deletePatient"
    }

    @PostMapping("/deletePatient")
    fun deletePatientSubmit(@ModelAttribute Patient: PatientForm?, model: Model):String? {
        model.addAttribute("removePatient", Patient)
        val sql = "DELETE from patients WHERE identificationnumber='${Patient?.identificationnumber}'"
        jdbcTemplate?.update(sql)
        return "deletePatient"
    }

    @GetMapping("/updatePatient")
    fun updatePatient(model: Model): String? {
        model.addAttribute("newPatient", PatientForm())
        return "updatePatient"
    }
    @PostMapping("/updatePatient")
    fun updatePatient(@ModelAttribute Patient: PatientForm?, model: Model):String? {
        model.addAttribute("newPatient", Patient)

        val sql ="UPDATE patients SET firstname = '${Patient?.firstname}' , lastname = '${Patient?.lastname}'," +
                "address = '${Patient?.address}' , gender = '${Patient?.gender}', dateofbirth = '${Patient?.dateofbirth}'" +
                ", languagepreference = '${Patient?.languagepreference}', drugallergies = '${Patient?.drugallergies}'," +
                "currentmedications = '${Patient?.currentmedications}',insurancenumber = '${Patient?.insurancenumber}'"+
                "WHERE identificationnumber = '${Patient?.identificationnumber}'"
        jdbcTemplate?.update(sql)
        return "updatePatient"
    }

    @GetMapping("/updatePrescriber")
    fun updatePrescriberForm(model: Model) : String? {
        model.addAttribute("updatePrescriber", PrescriberForm())
        return "updatePrescriber";
    }

    @PostMapping("/updatePrescriber")
    fun updatePrescriber(@ModelAttribute Prescriber: PrescriberForm?, model: Model, session: HttpSession) {
//        val user = session.getAttribute("currentUser") as Prescriber
        model.addAttribute("updatePrescriber", Prescriber)
        val userId = session.getAttribute("currentID") as UUID
        val usersql ="SELECT * from users WHERE uid = '${userId}'"
        val user: MutableMap<String, Any>? = jdbcTemplate?.queryForList(usersql)?.get(0)

        val sql = "UPDATE USERS SET " +
                "email = '${Prescriber?.email}'," +
                "password = '${Prescriber?.password}'," +
                "name = '${Prescriber?.name}'," +
                "title = '${Prescriber?.title}'," +
                "address = '${Prescriber?.address}'," +
                "telephonenumber = '${Prescriber?.telephonenumber}'" +
                "WHERE uid = '${user?.get("uid")}'"

        jdbcTemplate?.update(sql)!!

    }

    //2106
    @GetMapping("/updatePharmacist")
    fun updatePharmacistForm(model: Model) : String? {
        model.addAttribute("updatePharmacist", PrescriberForm())
        return "updatePharmacist";
    }

    @PostMapping("/updatePharmacist")
    fun updatePharmacist(@ModelAttribute Pharmacist: PharmacistForm?, model: Model, session: HttpSession) {
        model.addAttribute("updatePharmacist", Pharmacist)
        val userId = session.getAttribute("currentID") as UUID
        val usersql ="SELECT * from users WHERE uid = '${userId}'"
        val user: MutableMap<String, Any>? = jdbcTemplate?.queryForList(usersql)?.get(0)

        val sql = "UPDATE USERS SET " +
                "email = '${Pharmacist?.email}'," +
                "password = '${Pharmacist?.password}'," +
                "name = '${Pharmacist?.name}' ," +
                "title = '${Pharmacist?.title}'," +
                "address = '${Pharmacist?.address}'," +
                "telephonenumber = '${Pharmacist?.telephonenumber}'" +
                "WHERE UID = '${user?.get("uid")}'"

        jdbcTemplate?.update(sql)!!

    }

    @GetMapping("/pickUpMedicine")
    fun pickUpForm (model: Model) : String? {
        model.addAttribute("pickForm", PickUpForm())
        return "pickUpMedicine"
    }

    @PostMapping("/pickUpMedicine")
    fun pickUp (@ModelAttribute PickUp : PickUpForm?, model: Model) : String {
        model.addAttribute("pickForm", PickUp)
        val sql ="SELECT * from prescriptionfills WHERE id = '${PickUp?.prescriptionFillId}'"
        val prescriptionInfo: MutableMap<String, Any>? = jdbcTemplate?.queryForList(sql)?.get(0)
        val update = "UPDATE prescriptionfills set status = 'picked up' where id = '${prescriptionInfo?.get("id")}'"
        return "pickUpMedicine"

    }


    @GetMapping("/unregister")
    fun unregisterForm(model: Model) : String? {
        model.addAttribute("unregister", UnregisterForm())
        return "unregister";
    }

    @PostMapping("/unregister")
    fun unregister(@ModelAttribute Unregister: UnregisterForm?, model: Model, session: HttpSession) {
        model.addAttribute("unregister", Unregister)
        val sql = "DELETE from users WHERE email='${Unregister?.email}'"


        jdbcTemplate?.update(sql)!!

    }

    @GetMapping("/login")
    fun login(model: Model,session: HttpSession): String? {
        model.addAttribute("loggedin", PrescriberForm())
        session.setAttribute("loggedin", PrescriberForm())
        return "login"
    }


    @PostMapping("/login")
    fun loginSubmit(@ModelAttribute user: PrescriberForm?, model: Model, session: HttpSession): String? {
        model.addAttribute("loggedin", user)
        session.setAttribute("loggedin", user)
        val sql ="SELECT * from users WHERE email = '${user?.email}'"
        val userInfo: MutableMap<String, Any>? = jdbcTemplate?.queryForList(sql)?.get(0)
        val password = userInfo?.get("password")
        if (password!=null && (password == user?.password)){
//            val sql ="SELECT uid from users WHERE email = '${prescriber?.email}'"
            val userID: UUID? = userInfo?.get("uid") as UUID?
            model.addAttribute("currentID", userID)
            session.setAttribute("currentID", userID)
            val priority = userInfo?.get("priority")
            session.setAttribute("user priority", priority)
            when (priority) {
                "1" -> return "redirect:accountAdmin"
                "2" -> return "redirect:accountPharmacist"
                "3" -> return "redirect:accountPrescriber"
                else -> return "login"
            }
        }

       return "login"
    }

    @GetMapping("/createPrescription")
    fun prescriptionForm(model: Model,session: HttpSession): String? {
        model.addAttribute("newPrescription", PrescriptionForm())
        session.setAttribute("newPrescription",PrescriptionForm())
        return "createPrescription"
    }
    @PostMapping("/createPrescription")
    fun prescriptionForm(@ModelAttribute prescription: PrescriptionForm?, model: Model, session: HttpSession){
        model.addAttribute("newPrescription", prescription)
        session.setAttribute("newPrescription",PrescriptionForm())
        //print(Patient.address)
        var temp: UUID? =null
        if(session.getAttribute("currentID")!=null)
            temp = session.getAttribute("currentID") as UUID?

        precriptionJpaRepository.save(PrescriptionForm(UUID.randomUUID(),prescription?.patientid,temp,
            prescription?.startdate,prescription?.enddate,prescription?.medicinedin,prescription?.medicinename,
            prescription?.medicinestrength,prescription?.medicineamount,prescription?.methodofadministration,
            prescription?.frequencyofadministration,prescription?.optionalconsiderations,prescription?.refillable
            ,prescription?.refillablecount,prescription?.totaltimespickedup,prescription?.authorizationfiled,prescription?.isverified))
    }

    @GetMapping("/accountAdmin")
    fun adminHome (model: Model, session: HttpSession) : String{
        val priority = session.getAttribute("user priority")
        if (priority != "1") {return "login"}
        return "accountAdmin"
    }

    @GetMapping("/accountPrescriber")
    fun prescriberHome (model: Model, session: HttpSession) : String{
        val priority = session.getAttribute("user priority")
        if (priority != "3") {return "login"}
        return "accountPrescriber"
    }

    @GetMapping("/accountPharmacist")
    fun pharmacistHome (model: Model, session: HttpSession) : String{
        val priority = session.getAttribute("user priority")
        if (priority != "2") {return "login"}
        return "accountPharmacist"
    }

    @GetMapping("/logout")
    fun logout (model: Model, session: HttpSession) : String {
        session.removeAttribute("currentID")
        session.removeAttribute("priority")
        return "redirect:login"
    }

    @GetMapping("/createPrescriptionFill")
    fun PrescriptionFill(model: Model, session: HttpSession) : String? {
        model.addAttribute("newPrescriptionFill", PrescriptionFillForm())
        return "createPrescriptionFill";
    }

    @PostMapping("/createPrescriptionFill")
    fun PrescriptionFill(@ModelAttribute prescriptionFill: PrescriptionFillForm?, model: Model, session: HttpSession) {
        model.addAttribute("newPrescriptionFill", prescriptionFill)

        val sql ="SELECT * from prescriptions WHERE id = '${prescriptionFill?.prescriptionid}'"
        val prescriptionInfo: MutableMap<String, Any>? = jdbcTemplate?.queryForList(sql)?.get(0)
        var count:Int = prescriptionInfo?.get("refillablecount") as Int

        if(count!=null&&count>0){
            count--;

            val sql ="UPDATE prescriptions SET refillablecount = '${count}' WHERE id = '${prescriptionFill?.prescriptionid}'"
            jdbcTemplate?.update(sql)!!

            val saveSql = "INSERT INTO prescriptionfills (id, patientID, prescriptionid, status, verified, readyforpickup, summary)" +
                    "VALUES" +
                    "('${UUID.randomUUID()}', '${prescriptionFill?.patientid}', '${prescriptionFill?.prescriptionid}', '${prescriptionFill?.status}', '${prescriptionFill?.verified}', '${prescriptionFill?.readyforpickup}', '${prescriptionFill?.summary}')"

            jdbcTemplate?.update(saveSql)

        //
//            precriptionfillJpaRepository.save(PrescriptionFillForm(UUID.randomUUID(),prescriptionFill?.patientid,prescriptionFill?.prescriptionid,prescriptionFill?.status,prescriptionFill?.verified,prescriptionFill?.readyforpickup,prescriptionFill?.summary))
            //return "result"
        }
    }

    @RequestMapping("/generateDrugReport")
    fun rep(model: Model) :String {
        val sql = "select * from prescriptionfills"
        val res = jdbcTemplate?.queryForList(sql)
        model.addAttribute("report", res)
        return "generateDrugReport"
    }


}