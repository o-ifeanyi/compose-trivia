package android.ifeanyi.aitrivia.core.resourse

sealed class Resource<T>(var data: T?, var message: String?) {
    class Success<T>(data: T) : Resource<T>(data = data, message = null)

    class Error<T>(message: String) : Resource<T>(data = null, message = message)
}
