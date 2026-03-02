package com.example.m203

import android.content.Context
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

class FileUtil(val context : Context) {

    fun readFile(fileName: String) : String {
        return context.openFileInput(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    fun writeFile(fileName: String, text: String) {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(text.toByteArray())
        }
    }

    fun readFromAssets(fileName: String) : String {
        return context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
    }

    fun readFromRaw(fileName: String) : String {
        val resId = context.resources.getIdentifier(fileName, "raw", context.packageName)
        return context.resources.openRawResource(resId)
            .bufferedReader()
            .use { it.readText() }
    }

    fun readJsonFromAssets(fileName: String) : List<User> {
        val json = readFromAssets(fileName)
        return Json.decodeFromString(json)
    }

    fun writeUsersToJsonFile(fileName: String, users: List<User>) {
        val json = Json.encodeToString(users)
        writeFile(fileName, json)
    }

    fun readFromCache(fileName: String) : String {
        return context.cacheDir.resolve(fileName).bufferedReader().use { it.readText() }
    }
    fun writeToCache(fileName: String, text: String) {
        context.cacheDir.resolve(fileName).bufferedWriter().use { it.write(text) }
    }

    fun readFromExternalStorage(fileName: String) : String {
        val file = context.getExternalFilesDir(null)?.resolve(fileName)
        return file?.bufferedReader().use { it?.readText() } ?: ""
    }

    fun writeToExternalStorage(fileName: String, text: String) {
        val file = context.getExternalFilesDir(null)?.resolve(fileName)
        file?.bufferedWriter().use { it?.write(text) }
    }

    fun readFromFilDir(fileName: String) : String {
        val file = context.getFileStreamPath(fileName)
        return file.bufferedReader().use { it.readText() }
    }

    fun writeToFileDir(fileName: String, text: String) {
        val file = context.getFileStreamPath(fileName)
        file.bufferedWriter().use { it.write(text) }
    }



}