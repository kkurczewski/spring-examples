package pl.kkurczewski.json.value.dto

import com.fasterxml.jackson.annotation.JsonValue

data class ValueObject(@get:JsonValue val value: String)