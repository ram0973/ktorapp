package ram0973

import configureLoginRouting
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.contentnegotiation.*
import ram0973.features.register.configureRegisterRouting
import ram0973.plugins.configureRouting


fun main() {
    embeddedServer(CIO, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) { json() }
        configureRouting()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
