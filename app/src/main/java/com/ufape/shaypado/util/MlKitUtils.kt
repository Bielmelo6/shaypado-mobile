package com.ufape.shaypado.util

import android.graphics.Paint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.mlkit.vision.common.PointF3D
import com.google.mlkit.vision.pose.Pose
import com.google.mlkit.vision.pose.PoseLandmark
import java.util.Locale


@Composable
fun PoseGraphic(
    pose: Pose,
    showInFrameLikelihood: Boolean,
    visualizeZ: Boolean,
    rescaleZForVisualization: Boolean,
    poseClassification: List<String>
) {
    val zMin = Float.MAX_VALUE
    val zMax = Float.MIN_VALUE

    val context = LocalContext.current
    val dotRadius = 8.0f
    val inFrameLikelihoodTextSize = 30.0f
    val strokeWidth = 10.0f
    val poseClassificationTextSize = 60.0f

    Canvas(modifier = Modifier.wrapContentSize()) {
        val landmarks = pose.allPoseLandmarks
        if (landmarks.isEmpty()) {
            return@Canvas
        }

        val classificationX = poseClassificationTextSize * 0.5f
        for (i in poseClassification.indices) {
            val classificationY = size.height -
                    (poseClassificationTextSize * 1.5f * (poseClassification.size - i).toFloat())
            drawContext.canvas.nativeCanvas.drawText(
                poseClassification[i],
                classificationX,
                classificationY,
                Paint().apply {
                    color = Color.White.toArgb()
                    textSize = poseClassificationTextSize
                    setShadowLayer(5.0f, 0f, 0f, Color.Black.toArgb())
                }
            )
        }

        for (landmark in landmarks) {
            drawPoint(landmark.position3D, visualizeZ, rescaleZForVisualization, zMin, zMax)
        }

        val nose = pose.getPoseLandmark(PoseLandmark.NOSE)
        val lefyEyeInner = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_INNER)
        val lefyEye = pose.getPoseLandmark(PoseLandmark.LEFT_EYE)
        val leftEyeOuter = pose.getPoseLandmark(PoseLandmark.LEFT_EYE_OUTER)
        val rightEyeInner = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_INNER)
        val rightEye = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE)
        val rightEyeOuter = pose.getPoseLandmark(PoseLandmark.RIGHT_EYE_OUTER)
        val leftEar = pose.getPoseLandmark(PoseLandmark.LEFT_EAR)
        val rightEar = pose.getPoseLandmark(PoseLandmark.RIGHT_EAR)
        val leftMouth = pose.getPoseLandmark(PoseLandmark.LEFT_MOUTH)
        val rightMouth = pose.getPoseLandmark(PoseLandmark.RIGHT_MOUTH)

        val leftShoulder = pose.getPoseLandmark(PoseLandmark.LEFT_SHOULDER)
        val rightShoulder = pose.getPoseLandmark(PoseLandmark.RIGHT_SHOULDER)
        val leftElbow = pose.getPoseLandmark(PoseLandmark.LEFT_ELBOW)
        val rightElbow = pose.getPoseLandmark(PoseLandmark.RIGHT_ELBOW)
        val leftWrist = pose.getPoseLandmark(PoseLandmark.LEFT_WRIST)
        val rightWrist = pose.getPoseLandmark(PoseLandmark.RIGHT_WRIST)
        val leftHip = pose.getPoseLandmark(PoseLandmark.LEFT_HIP)
        val rightHip = pose.getPoseLandmark(PoseLandmark.RIGHT_HIP)
        val leftKnee = pose.getPoseLandmark(PoseLandmark.LEFT_KNEE)
        val rightKnee = pose.getPoseLandmark(PoseLandmark.RIGHT_KNEE)
        val leftAnkle = pose.getPoseLandmark(PoseLandmark.LEFT_ANKLE)
        val rightAnkle = pose.getPoseLandmark(PoseLandmark.RIGHT_ANKLE)

        val leftPinky = pose.getPoseLandmark(PoseLandmark.LEFT_PINKY)
        val rightPinky = pose.getPoseLandmark(PoseLandmark.RIGHT_PINKY)
        val leftIndex = pose.getPoseLandmark(PoseLandmark.LEFT_INDEX)
        val rightIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_INDEX)
        val leftThumb = pose.getPoseLandmark(PoseLandmark.LEFT_THUMB)
        val rightThumb = pose.getPoseLandmark(PoseLandmark.RIGHT_THUMB)
        val leftHeel = pose.getPoseLandmark(PoseLandmark.LEFT_HEEL)
        val rightHeel = pose.getPoseLandmark(PoseLandmark.RIGHT_HEEL)
        val leftFootIndex = pose.getPoseLandmark(PoseLandmark.LEFT_FOOT_INDEX)
        val rightFootIndex = pose.getPoseLandmark(PoseLandmark.RIGHT_FOOT_INDEX)

        drawLine(nose, lefyEyeInner)
        drawLine(lefyEyeInner, lefyEye)
        drawLine(lefyEye, leftEyeOuter)
        drawLine(leftEyeOuter, leftEar)
        drawLine(nose, rightEyeInner)
        drawLine(rightEyeInner, rightEye)
        drawLine(rightEye, rightEyeOuter)
        drawLine(rightEyeOuter, rightEar)
        drawLine(leftMouth, rightMouth)

        drawLine(leftShoulder, rightShoulder)
        drawLine(leftHip, rightHip)

        drawLine(leftShoulder, leftElbow, Color.Green)
        drawLine(leftElbow, leftWrist, Color.Green)
        drawLine(leftShoulder, leftHip, Color.Green)
        drawLine(leftHip, leftKnee, Color.Green)
        drawLine(leftKnee, leftAnkle, Color.Green)
        drawLine(leftWrist, leftThumb, Color.Green)
        drawLine(leftWrist, leftPinky, Color.Green)
        drawLine(leftWrist, leftIndex, Color.Green)
        drawLine(leftIndex, leftPinky, Color.Green)
        drawLine(leftAnkle, leftHeel, Color.Green)
        drawLine(leftHeel, leftFootIndex, Color.Green)

        drawLine(rightShoulder, rightElbow, Color.Yellow)
        drawLine(rightElbow, rightWrist, Color.Yellow)
        drawLine(rightShoulder, rightHip, Color.Yellow)
        drawLine(rightHip, rightKnee, Color.Yellow)
        drawLine(rightKnee, rightAnkle, Color.Yellow)
        drawLine(rightWrist, rightThumb, Color.Yellow)
        drawLine(rightWrist, rightPinky, Color.Yellow)
        drawLine(rightWrist, rightIndex, Color.Yellow)
        drawLine(rightIndex, rightPinky, Color.Yellow)
        drawLine(rightAnkle, rightHeel, Color.Yellow)
        drawLine(rightHeel, rightFootIndex, Color.Yellow)

        if (showInFrameLikelihood) {
            for (landmark in landmarks) {
                drawContext.canvas.nativeCanvas.drawText(
                    String.format(Locale.US, "%.2f", landmark.inFrameLikelihood),
                    landmark.position.x,
                    landmark.position.y,
                    Paint().apply {
                        color = Color.White.toArgb()
                        textSize = inFrameLikelihoodTextSize
                    }
                )
            }
        }
    }
}

private fun DrawScope.drawPoint(
    point: PointF3D,
    visualizeZ: Boolean,
    rescaleZForVisualization: Boolean,
    zMin: Float,
    zMax: Float
) {


    if (visualizeZ && rescaleZForVisualization) {
        // Rescale, remap and transform the point
    }

    drawCircle(
        color = Color.White,
        radius = 8.dp.toPx(),
        center = Offset(point.x, point.y),
    )
}

private fun DrawScope.drawLine(
    startLandmark: PoseLandmark?,
    endLandmark: PoseLandmark?,
    color: Color = Color.White
) {
    if (startLandmark != null && endLandmark != null) {
        val start = startLandmark.position3D
        val end = endLandmark.position3D
        drawLine(
            color = color,
            start = Offset(start.x, start.y),
            end = Offset(end.x, end.y),
            strokeWidth = 10.0f
        )
    }
}

