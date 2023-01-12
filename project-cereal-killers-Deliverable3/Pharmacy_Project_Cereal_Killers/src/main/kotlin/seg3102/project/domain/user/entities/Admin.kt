package seg3102.project.domain.user.entities

import seg3102.project.application.dto.queries.PharmacistCreateDto
import seg3102.project.application.dto.queries.PrescriberCreateDto
import seg3102.project.domain.user.facade.UserFacade
import seg3102.project.domain.user.factories.AccountFactory
import seg3102.project.domain.user.repositories.AccountRepository
import java.util.*

class Admin(
    uid: UUID,
    email: String,
    password: String,
    name: String,
    priority: String,
    isSignedIn: Boolean,
):User(
    uid,email,password,name,priority,isSignedIn
){

    /*
        The registerAgent() method takes a Dto as parameter and create a instance of an agent using the
        factory, then stores the returned agent into the repository
     */
    fun registerAgent(agentInfo: PharmacistCreateDto, factory:AccountFactory, repository:AccountRepository):UUID{
        val newAgent = factory.createPharmacistAccount(agentInfo)
        repository.savePharmacist(newAgent)
        return newAgent.uid
    }

    fun registerAgent(agentInfo: PrescriberCreateDto, factory:AccountFactory, repository:AccountRepository):UUID{
        val newAgent = factory.createPrescriberAccount(agentInfo)
        repository.savePrescriber(newAgent)
        return newAgent.uid
    }

    /*
        The unregisterAgent() method is used by an admin to deativates an agent account
        by setting the priority to 6, which means garbage.
     */
    fun unregisterAgent(id:UUID, repository:AccountRepository){
        val agent = repository.findPharmacist(id)
        if(agent!=null)
            agent.priority= "6"
        val agent2 = repository.findPrescriber(id)
        if(agent2!=null)
            agent2.priority = "6"
    }
}
