package com.ufape.shaypado.util

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

fun Bitmap.toTempFile(context : Context): File {
    val bos = ByteArrayOutputStream()
    this.compress(Bitmap.CompressFormat.PNG, 50, bos)
    val bitmapData: ByteArray = bos.toByteArray()
    val outputDir: File = context.cacheDir
    val outputFile = File.createTempFile("prefix", ".png", outputDir)
    var fos: FileOutputStream? = null
    try {
        fos = FileOutputStream(outputFile)
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }
    try {
        fos?.write(bitmapData)
        fos?.flush()
        fos?.close()
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return outputFile
}

fun Uri.buildBitmap(contentResolver: ContentResolver): Bitmap {
    return if (Build.VERSION.SDK_INT < 28) {
        MediaStore.Images.Media.getBitmap(
            contentResolver,
            this
        )
    } else {
        val source = ImageDecoder.createSource(contentResolver, this)
        ImageDecoder.decodeBitmap(source)
    }
}