package ram0973.features.register

@kotlinx.serialization.Serializable
data class RegisterRequestRemote(
    val email: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class RegisterResponseRemote(
    val token: String,
)
