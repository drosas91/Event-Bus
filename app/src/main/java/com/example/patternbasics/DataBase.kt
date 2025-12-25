package com.example.patternbasics

fun getEventsInRealtime() = listOf(
    Result(1, "Fútbol", listOf("Italia", "Perú", "Corea del Sur")),
    Result(2, "Levantamiento de Pesas", listOf("Mongolia", "Alemania", "Turquía")),
    Result(3, "Gimnasia Rítmica", listOf("Rusia", "USA", "Francia")),
    Result(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    Result(5, "Béisbol", null, true),
    Result(6, "Rugby", listOf("Sudáfrica", "Qatar", "Rumanía")),
    Result(7, "Tenis", listOf("España", "México", "Colombia"))
)

fun getResultEventsInRealtime() = listOf(
    SportEvent.ResultSuccess(1, "Fútbol", listOf("Italia", "Perú", "Corea del Sur")),
    SportEvent.ResultSuccess(2, "Levantamiento de Pesas", listOf("Mongolia", "Alemania", "Turquía")),
    SportEvent.ResultError(10, "Error de red."),
    SportEvent.ResultSuccess(3, "Gimnasia Rítmica", listOf("Rusia", "USA", "Francia")),
    SportEvent.ResultSuccess(4, "Polo Acuático", listOf("España", "Vietnam", "USA")),
    SportEvent.ResultSuccess(5, "Béisbol", null, true),
    SportEvent.ResultError(20, "Error de permisos."),
    SportEvent.ResultSuccess(6, "Rugby", listOf("Sudáfrica", "Qatar", "Rumanía")),
    SportEvent.ResultSuccess(7, "Tenis", listOf("España", "México", "Colombia"))
)

fun getAdEventsInRealtime() = listOf(
    SportEvent.AdEvent,
    SportEvent.AdEvent
)
