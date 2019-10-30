package br.com.wobbu.restcountries.utils

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class TestUtils {

    fun getFileString(path: String): String {
        try {

            val sb = StringBuilder()
            val reader = BufferedReader(
                InputStreamReader(
                    javaClass.classLoader!!.getResourceAsStream(path)
                )
            )
            var line = reader.readLine()
            while (line != null) {
                sb.append(line)
                line = reader.readLine()
            }
            return sb.toString()
        } catch (e: IOException) {
            throw IllegalArgumentException("Could not read from resource at: $path")
        }
    }
}