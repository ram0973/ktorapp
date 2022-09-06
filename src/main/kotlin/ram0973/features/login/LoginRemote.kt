package ram0973.features.login

@kotlinx.serialization.Serializable
data class LoginRequestRemote(
    val email: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class LoginResponseRemote(
    val token: String,
)
