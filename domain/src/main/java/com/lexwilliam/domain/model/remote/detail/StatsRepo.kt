package com.lexwilliam.domain.model.remote.detail

data class Stats(
    val completed: Int,
    val dropped: Int,
    val on_hold: Int,
    val plan_to_watch: Int,
    val request_cache_expiry: Int,
    val request_cached: Boolean,
    val request_hash: String,
    val scores: ReviewScore,
    val total: Int,
    val watching: Int
)

data class Scores(
    val `1`: X1,
    val `2`: X2,
    val `3`: X3,
    val `4`: X4,
    val `5`: X5,
    val `6`: X6,
    val `7`: X7,
    val `8`: X8,
    val `9`: X9,
    val `10`: X10
)

data class X1(
    val percentage: Double,
    val votes: Int
)

data class X2(
    val percentage: Double,
    val votes: Int
)

data class X3(
    val percentage: Double,
    val votes: Int
)

data class X4(
    val percentage: Double,
    val votes: Int
)

data class X5(
    val percentage: Double,
    val votes: Int
)

data class X6(
    val percentage: Double,
    val votes: Int
)

data class X7(
    val percentage: Double,
    val votes: Int
)

data class X8(
    val percentage: Double,
    val votes: Int
)

data class X9(
    val percentage: Double,
    val votes: Int
)

data class X10(
    val percentage: Double,
    val votes: Int
)