package com.example.wspolicy.models

data class Wanteds(
    val data : List<Wanted>,
    val status : Boolean
)

data class Wanted(
    val id : String,
    val status: String,
    val first_name : String,
    val last_name : String,
    val last_location : String,
    val nicknames : String,
    val description : String,
    val photo : String
)
