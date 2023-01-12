package seg3102.project.application.services

import seg3102.project.domain.common.DomainEvent

interface DomainEventEmitter {
    fun emit(event: DomainEvent)
}