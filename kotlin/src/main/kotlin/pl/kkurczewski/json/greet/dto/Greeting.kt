package pl.kkurczewski.json.greet.dto

/**
 * Data classes de/serialization depends on:
 * [jackson-module-kotlin] defined in [build.gradle]
 */
data class Greeting(val greeting: String)