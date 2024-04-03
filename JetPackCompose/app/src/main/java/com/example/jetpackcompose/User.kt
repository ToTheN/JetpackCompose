package com.example.jetpackcompose

import java.time.LocalDateTime

data class User(
    val name:String,
    val userId:Int,
    val timestamp : LocalDateTime
)