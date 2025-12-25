package com.example.patternbasics

data class Result(val sportKey: Int,
                  val sportName: String,
                  val results: List<String>?,
                  val isWarning: Boolean = false)