package com.example.wspolicy.models

data class Departments(
    val data: List<DepartmentItem>,
    val success: Boolean
)

data class DepartmentItem(
    val id: String,
    val address: String,
    val name: String,
    val boss : String,
    val phone : String,
    val email : String,
    val description : String,
    val coords : String
)