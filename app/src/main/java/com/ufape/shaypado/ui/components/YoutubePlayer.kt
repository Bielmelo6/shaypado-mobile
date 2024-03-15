package com.ufape.shaypado.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun YoutubePlayer(
    videoUrl : String?
) {
    fun extractYouTubeVideoId(url: String?): String? {
        if (url == null) return null
        val pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|v=)([-_a-zA-Z0-9]{11})".toRegex()
        val matchResult = pattern.find(url)
        return matchResult?.value
    }

    val videoId = extractYouTubeVideoId(videoUrl)

    if (videoId != null) {
        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                YouTubePlayerView(context).apply {
                    findViewTreeLifecycleOwner()?.lifecycle?.addObserver(this)
                    addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                        override fun onReady(youTubePlayer: YouTubePlayer) {
                            youTubePlayer.loadVideo(videoId, 0f)
                        }
                    })
                }
            },
        )
    }else {
        AppText(
            text = "O vídeo do exercício não pode ser carregado",
        )
    }
}