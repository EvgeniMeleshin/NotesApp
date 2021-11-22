package ru.example.notes

class Model {

    private var header: String = ""
    private var content: String = ""

    fun addContent(content: String) {
        this.content = content
    }

    fun getContent(): String {
        return content
    }

    fun addHeader(header: String) {
        this.header = header
    }

    fun getHeader(): String {
        return header
    }

}