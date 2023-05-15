package com.technosales.vehiclechallanmobile.utils.helper

import com.technosales.vehiclechallanmobile.utils.GeneralUtils
import retrofit2.HttpException
import java.io.IOException

object Helper {

    fun getErrorMessage(
        throwable: Throwable,
        defaultMessage: String = "Something went wrong!!"
    ): String {
        var errorMessage = defaultMessage
        when (throwable) {
            is IOException -> errorMessage = "No Internet Connection!!"
            is HttpException -> {

                try {

                    var apiError = GeneralUtils.convertErrors(throwable.response()?.errorBody())

                    var errorEntries = apiError?.errors?.entries
                    errorEntries?.let {
                        it.forEach { message ->
                            errorMessage = message.value[0]
                        }
                    }

//                val gson = Gson()
//                val minimalResource: MinimalResource =
//                    gson.fromJson(
//                        throwable.response()?.errorBody()?.charStream(),
//                        MinimalResource::class.java
//                    )
//                errorMessage = minimalResource.message
                } catch (e: Exception) {
                    errorMessage = e.localizedMessage
                }
            }
            else -> errorMessage += throwable.localizedMessage
        }
        return errorMessage
    }
}
