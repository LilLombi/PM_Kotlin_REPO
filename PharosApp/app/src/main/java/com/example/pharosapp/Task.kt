package com.example.pharosapp

import java.util.Date

class Task(
    var title: String,
    var description: String,
    var category: Category,
    var datedue: Date,
    var datefinished: Date,
    var progress: Int,
    var finished: Boolean
) {


}