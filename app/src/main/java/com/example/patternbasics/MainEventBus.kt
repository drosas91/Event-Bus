package com.example.patternbasics

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

private lateinit var eventBus: EventBus
private val job = Job()
private val scope = CoroutineScope(Dispatchers.IO + job)

fun main() {
    initEventBus()
    runBlocking {
        /*setupSubscriber(scope)
        setupSubscriberTwo(scope)
        setupPublisher()*/

        setupSubscriberResults(scope)
        setupSubscriberError(scope)
        setupSubscriberAnalytics(scope)
        setupPublishers()
    }
}

suspend fun setupPublishers() {
    scope.launch {
        getAdEventsInRealtime().forEach { adEvent ->
            delay(someTime() * 2)
            eventBus.publish(adEvent)
        }
    }
    
    getResultEventsInRealtime().forEach { resultEvent ->
        delay(someTime())
        eventBus.publish(resultEvent)
    }
}

fun setupSubscriberResults(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.ResultSuccess> { event ->
            println("Result: ${event.sportName}")
        }
    }
}

fun setupSubscriberError(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.ResultError> { event ->
            println("Code: ${event.code}, Message:  ${event.msg}")
        }
    }
}

fun setupSubscriberAnalytics(scope: CoroutineScope) {
    scope.launch {
        eventBus.subscribe<SportEvent.AdEvent> { event ->
            println("Add click. Send data to server...")
        }
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
        delay(someTime())
        eventBus.publish(it)
    }
}

fun someTime(): Long = Random.nextLong(500, 2_000)