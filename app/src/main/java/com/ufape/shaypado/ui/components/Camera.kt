package com.ufape.shaypado.ui.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Cameraswitch
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import com.ufape.shaypado.ui.data.ia.MLKitAnalyzer
import com.ufape.shaypado.util.buildBitmap

@Composable
fun Camera(
    context: Context,
    onBackButton: () -> Unit = {},
    mlKitAnalyzer: MLKitAnalyzer? = null,
    onPicture: (Bitmap) -> Unit = {}
) {
    var shouldShowImageDialog by remember { mutableStateOf(false) }
    var shouldOpenGallery by remember { mutableStateOf(false) }
    var selectedBitmap by remember { mutableStateOf<Bitmap?>(null) }

    //Permission related
    val hasPermission = ContextCompat.checkSelfPermission(
        context, Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    var cameraPermissionGranted by remember { mutableStateOf(hasPermission) }

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        cameraPermissionGranted = isGranted
    }


    //Camera Settings
    val controller by remember {
        mutableStateOf(LifecycleCameraController(context).apply {
            setEnabledUseCases(
                CameraController.IMAGE_CAPTURE or CameraController.IMAGE_ANALYSIS
            )
            cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
            mlKitAnalyzer?.let {
                setImageAnalysisAnalyzer(
                    ContextCompat.getMainExecutor(context), it
                )
            }
        })
    }

    fun toggleCamera() {
        controller.cameraSelector =
            if (controller.cameraSelector == CameraSelector.DEFAULT_FRONT_CAMERA) {
                CameraSelector.DEFAULT_BACK_CAMERA
            } else {
                CameraSelector.DEFAULT_FRONT_CAMERA
            }
    }

    fun takePicture() {
        controller.takePicture(ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageCapturedCallback() {
                override fun onCaptureSuccess(image: ImageProxy) {
                    super.onCaptureSuccess(image)
                    selectedBitmap = image.toBitmap()
                    shouldShowImageDialog = true
                    image.close()
                }

                override fun onError(exception: ImageCaptureException) {
                    super.onError(exception)
                    Log.e("Camera", "Error taking picture", exception)
                }
            })
    }

    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                selectedBitmap = uri.buildBitmap(context.contentResolver)
                shouldShowImageDialog = true
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }


    //Check for permissions
    LaunchedEffect(Unit) {
        if (!hasPermission) {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    if (!cameraPermissionGranted) {
        Row {
            IconButton(
                modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                onClick = {
                    onBackButton()
                }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Switch Camera"
                )
            }
            Text("Permissão de câmera negada")
        }
    } else {

        if (shouldShowImageDialog) {
            DialogWithImage(onDismissRequest = {
                selectedBitmap = null
                shouldShowImageDialog = false
            }, onConfirmation = {
                onPicture(
                    selectedBitmap!!
                )

                shouldShowImageDialog = false
            }, bitmap = selectedBitmap!!
            )
        }

        LaunchedEffect(shouldOpenGallery) {
            if (shouldOpenGallery) {
                pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                shouldOpenGallery = false
            }
        }

        Scaffold { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                CameraPreview(
                    controller = controller, modifier = Modifier.fillMaxSize()
                )

                IconButton(
                    modifier = Modifier.padding(start = 8.dp, top = 8.dp),
                    onClick = {
                        onBackButton()
                    }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Switch Camera"
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButton(
                        onClick = {
                            toggleCamera()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Cameraswitch,
                            contentDescription = "Switch Camera"
                        )
                    }

                    IconButton(
                        onClick = {
                            takePicture()
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhotoCamera,
                            contentDescription = "Open Gallery"
                        )
                    }

                    IconButton(
                        onClick = {
                            shouldOpenGallery = true
                        },
                    ) {
                        Icon(
                            imageVector = Icons.Default.Photo, contentDescription = "Open Gallery"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DialogWithImage(
    onDismissRequest: () -> Unit = {},
    onConfirmation: () -> Unit = {},
    bitmap: Bitmap,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    bitmap = bitmap.asImageBitmap(),
                    contentDescription = "Selected Image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(160.dp)
                )
                Text(
                    text = "Deseja usar essa imagem?",
                    modifier = Modifier.padding(16.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = { onConfirmation() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text("Confirmar")
                    }
                }
            }
        }
    }

}

@Composable
fun CameraPreview(
    controller: LifecycleCameraController, modifier: Modifier = Modifier
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    AndroidView(
        factory = {
            PreviewView(it).apply {
                this.controller = controller
                controller.bindToLifecycle(lifecycleOwner)
            }
        }, modifier = modifier
    )
}