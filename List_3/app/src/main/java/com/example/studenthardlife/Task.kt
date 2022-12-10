package com.example.studenthardlife

data class Task(val name: String, val index: Int) {
    var id: Int = 0

    constructor(id: Int, name: String, index: Int) : this(name, index) {
        this.id = id
    }
}
