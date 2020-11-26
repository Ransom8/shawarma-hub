package com.example.shawarmahub.utils

import java.util.*



object randomStringGenerator {
    @JvmStatic
    fun main(args: Array<String>) {
        println(generateString())
    }

    fun generateString(): String {
        val uuid: String = UUID.randomUUID().toString()
        return uuid.replace("-","")
    }
}