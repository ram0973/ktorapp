package ram0973.features.cache

import ram0973.features.register.RegisterRequestRemote

data class TokenCache(
    val email: String,
    val token: String
)

object InMemoryCache {
    val userList: MutableList<RegisterRequestRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}