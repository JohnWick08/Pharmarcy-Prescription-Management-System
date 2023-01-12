package seg3102.project.infrastructure.Expection
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.NOT_FOUND)
class ResourceNotFoundException(message: String?) : RuntimeException(message) {
    companion object {
        private const val serialVersionUID = 1L
    }
}