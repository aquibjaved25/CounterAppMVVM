package com.example.assignment2

data class Users(val userId: String, val fullName:String, val userName:String, val email:String, val count:Int)

fun getUserList(): List<Users> {
    val list = ArrayList<Users>()
    (1..40).forEach {
        list.add( Users("userId$it","fullName$it","userName$it","userEmail$it",it))
    }
    return list
}