package com.lexwilliam.data.model.common

import com.lexwilliam.domain.model.common.From
import com.lexwilliam.domain.model.common.To

data class PropRepo(
    val from: FromRepo?,
    val to: ToRepo?
)