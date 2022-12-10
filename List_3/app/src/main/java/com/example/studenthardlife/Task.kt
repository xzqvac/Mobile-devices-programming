package com.example.studenthardlife

data class Task(val name: String, val description: String) {
    var id: Int = 0

    constructor(id: Int, name: String, description: String) : this(name, description) {
        this.id = id
    }
}
