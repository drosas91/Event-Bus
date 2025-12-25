package com.example.patternbasics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private lateinit var eventBus: EventBus
private val job = Job()
private val scope = CoroutineScope(Dispatchers.IO + job)

fun main() {
    initEventBus()
    runBlocking {
        setupSubscriber(scope)
        setupSubscriberTwo(scope)
        setupPublisher()
    }
}

fun initEventBus() {
    eventBus = EventBus()
}

suspend fun setupSubscriber(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        eventBus.subscribe<Result> { result ->
            println(result.sportName)
        }
    }
}

suspend fun setupSubscriberTwo(coroutineScope: CoroutineScope) {
    coroutineScope.launch {
        eventBus.subscribe<Result> { result ->
            if (result.isWarning) {
                println("WARNING: ${result.sportName}")
            }
        }
    }
}

suspend fun setupPublisher() {
    getEventsInRealtime().forEach {
        delay(500)
        eventBus.publish(it)
    }
}