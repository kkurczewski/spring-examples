package pl.kkurczewski.deser.user.dto

import com.fasterxml.jackson.annotation.JsonUnwrapped

data class User(val name: String,
                val surname: String,
                @field:JsonUnwrapped val address: Address) // doesn't work for Kotlin