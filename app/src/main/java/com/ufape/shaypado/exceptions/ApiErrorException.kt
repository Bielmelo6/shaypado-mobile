package com.ufape.shaypado.exceptions

import java.lang.Exception

class ApiErrorException(
    val fieldName : String?,
    override val message: String?
) : Exception()