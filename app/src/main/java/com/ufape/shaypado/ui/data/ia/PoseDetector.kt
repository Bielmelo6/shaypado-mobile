package com.ufape.shaypado.ui.data.ia

import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseDetection
import com.google.mlkit.vision.pose.PoseDetector
import com.google.mlkit.vision.pose.defaults.PoseDetectorOptions


class MLKitPoseClassifier {

    private var classifier: PoseDetector? = null
    private var pose: Pose? = null

    private fun setupClassifier() {
        val options = PoseDetectorOptions.Builder()
            .setDetectorMode(PoseDetectorOptions.STREAM_MODE)
            .build()

        classifier = PoseDetection.getClient(options)
    }

    fun classify(inputImage: InputImage, rotation: Int): Pose? {
        if (classifier == null) {
            setupClassifier()
        }

        classifier!!.process(inputImage)
            .addOnSuccessListener {
                pose = it
            }
            .addOnFailureListener {
                pose = null
                Log.d("MLKitPoseClassifier", "Failed")
            }

        return pose
    }
}


class MLKitAnalyzer(
    private val classifier: MLKitPoseClassifier,
    private val onResult: (Pose?) -> Unit
) : ImageAnalysis.Analyzer {
    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image
        if (mediaImage != null) {
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            val results = classifier.classify(image, imageProxy.imageInfo.rotationDegrees)
            onResult(results)
        }

        imageProxy.close()
    }
}
