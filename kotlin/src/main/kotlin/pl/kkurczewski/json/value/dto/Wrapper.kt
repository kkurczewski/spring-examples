package pl.kkurczewski.json.value.dto

import com.fasterxml.jackson.annotation.JsonCreator

data class Wrapper @JsonCreator constructor(val first: ValueObject,
                                            val second: ValueObject)