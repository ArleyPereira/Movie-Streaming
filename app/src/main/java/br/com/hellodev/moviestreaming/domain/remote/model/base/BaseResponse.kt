package br.com.hellodev.moviestreaming.domain.remote.model.base

import br.com.hellodev.moviestreaming.core.enums.result.ResultStatus
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val resultStatus: ResultStatus = ResultStatus.UNKNOWN,
    val page: Int? = null,
    val totalPages: Int? = null,
    val totalResults: Int? = null,
    val status: Int? = null,
    val message: String? = null,
    val results: T? = null
)

