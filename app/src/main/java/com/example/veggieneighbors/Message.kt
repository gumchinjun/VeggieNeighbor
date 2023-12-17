package com.example.logreg

data class Message(
    var message: String?,
    var sendId: String?
){
    constructor():this("","")
}