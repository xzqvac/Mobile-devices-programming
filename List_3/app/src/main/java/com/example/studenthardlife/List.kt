package com.example.studenthardlife

data class List(val name: String, val index: Int) {
    var id: Int = 0

    constructor(id: Int, name: String, index: Int) : this(name, index) {
        this.id = id
    }
}
