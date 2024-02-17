package com.ufape.shaypado.util

import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

fun Uri.createTempPdfFileFromUri(context: Context): File? {
    try {
        var fileName = getFileNameFromUri(context, this)
            ?.substringBefore('.')
            ?.replace(Regex("[^a-zA-Z0-9]"), "")
            ?.replace(" ", "")
        if (fileName.isNullOrBlank()) {
            fileName = "arquivoSemNome"
        }
        fileName = "${fileName}.pdf"
        val tempFile = File(context.cacheDir, fileName)
        val inputStream: InputStream? = context.contentResolver.openInputStream(this)
        val outputStream = FileOutputStream(tempFile)

        if (inputStream != null) {
            val buffer = ByteArray(4 * 1024) // 4 KB buffer size
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                outputStream.write(buffer, 0, read)
            }

            outputStream.flush()
            outputStream.close()
            inputStream.close()

            return tempFile
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return null
}

private fun getFileNameFromUri(context: Context, uri: Uri): String? {
    var fileName: String? = null
    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        if (it.moveToFirst()) {
            val displayNameIndex = it.getColumnIndexOrThrow("_display_name")
            fileName = it.getString(displayNameIndex)
        }
    }
    return fileName
}