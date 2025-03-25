package br.com.hellodev.moviestreaming.data.api

import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import br.com.hellodev.moviestreaming.domain.remote.model.base.BaseResponse
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.json.Json

class ApiRequest(val json: Json) {
    suspend inline operator fun <reified R : Any, D : Any> invoke(
        response: HttpResponse,
        noinline toDomain: (R) -> D
    ): BaseResponse<D> {
        return try {
            if (response.status.value in 200..299) {
                val responseBody = response.body<String>()
                val baseResponse = json.decodeFromString<BaseResponse<R>>(responseBody)
                    .copy(resultStatus = ResultStatus.SUCCESS)

                applyMapperSuccess(baseResponse, toDomain)
            } else {
                val errorBody = response.body<String>()
                val errorResponse = json.decodeFromString<BaseResponse<Unit>>(errorBody)

                if (response.status >= HttpStatusCode.InternalServerError) {
                    BaseResponse(
                        results = null,
                        status = response.status.value,
                        resultStatus = ResultStatus.ERROR,
                        message = "Por favor, tente novamente em alguns instantes."
                    )
                } else {
                    BaseResponse(
                        results = null,
                        status = response.status.value,
                        resultStatus = ResultStatus.ERROR,
                        message = errorResponse.message
                    )
                }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            BaseResponse(
                results = null,
                status = response.status.value,
                resultStatus = ResultStatus.ERROR,
                message = "Por favor, tente novamente em alguns instantes."
            )
        }
    }

    fun <R, D> applyMapperSuccess(
        body: BaseResponse<R>,
        mapper: (R) -> D
    ): BaseResponse<D> {
        return BaseResponse(
            results = body.results?.let { mapper(it) },
            page = body.page,
            totalPages = body.totalPages,
            totalResults = body.totalResults,
            status = body.status,
            resultStatus = body.resultStatus,
            message = body.message
        )
    }

}


