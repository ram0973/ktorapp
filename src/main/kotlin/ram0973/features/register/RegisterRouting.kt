package ram0973.features.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ram0973.features.cache.InMemoryCache
import ram0973.features.cache.TokenCache
import ram0973.features.register.RegisterRequestRemote
import ram0973.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {
    routing {
        post("/register") {
            val receive = call.receive<RegisterRequestRemote>()
            if (!receive.email.isValidEmail()) {
                call.respond(HttpStatusCode.BadRequest, "Email is not valid")
            }

            if (InMemoryCache.userList.map { it.email }.contains(receive.email)) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            }

            val token = UUID.randomUUID().toString()
            InMemoryCache.userList.add(receive)
            InMemoryCache.token.add(TokenCache(email = receive.email, token = token))

            call.respond(RegisterResponseRemote(token = token))
        }
    }
}
