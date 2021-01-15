package com.example.campusmate.fileIO

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class RFile (val context: Context) {
    public fun getFileContents(pathToFile : String) : String? {
        val path = context.filesDir
        val file = File(path, pathToFile)
        if (file.isFile) {
            return FileInputStream(file).bufferedReader().use { it.readText() }
        }
        Log.e("RFile", "Failed to open file $pathToFile")
        return null
    }

    public fun getFileAsMap(pathToFile: String) : Map<String, String>? {
        val fileContent = getFileContents(pathToFile)
        if (fileContent != null) {
            return jacksonObjectMapper().readValue(fileContent!!)
        }
        return null
    }

    public fun saveStringToFile (pathToFile: String, string: String) : Boolean {
        var file : File = File(context.filesDir, pathToFile)
        file.parentFile.mkdirs()
        FileOutputStream(file).use { it.write(string.toByteArray()) }
        return true
    }

    public fun saveMapToFile(pathToFile: String, map: Map<String,String>) : Boolean {
        val string = jacksonObjectMapper().writeValueAsString(map)
        return saveStringToFile(pathToFile, string)
    }

}