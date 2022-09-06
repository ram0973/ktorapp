import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ram0973.features.cache.InMemoryCache
import ram0973.features.cache.TokenCache
import ram0973.features.login.LoginRequestRemote
import ram0973.features.login.LoginResponseRemote
import java.util.*

fun Application.configureLoginRouting() {
    routing {
        post("/login") {
            val request = call.receive<LoginRequestRemote>()
            val first = InMemoryCache.userList.firstOrNull { it.email == request.email }
            if (first == null || first.password !== request.password) {
                call.respond(HttpStatusCode.BadRequest, "Bad credentials")
            }
            if (InMemoryCache.userList.map { it.email }.contains(request.email)) {
                val token = UUID.randomUUID().toString()
                InMemoryCache.token.add(TokenCache(email = request.email, token = token))
                call.respond(LoginResponseRemote(token = token))
                return@post
            } else {
                call.respond(HttpStatusCode.BadRequest)
            }
        }
    }
}