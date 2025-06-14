package com.example.petshop.audio

import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import com.example.petshop.model.AudioEpisode
import java.util.concurrent.TimeUnit

/**
 * Clase para gestionar la reproducción de audio en la aplicación
 */
class AudioPlayerManager(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var currentEpisode: AudioEpisode? = null
    private var isPlaying = false
    private var currentPosition = 0
    private val handler = Handler(Looper.getMainLooper())
    private var progressCallback: ((Float) -> Unit)? = null

    // Runnable para actualizar el progreso
    private val updateProgressRunnable = object : Runnable {
        override fun run() {
            mediaPlayer?.let { player ->
                if (player.isPlaying) {
                    val duration = player.duration.toFloat()
                    val currentPos = player.currentPosition.toFloat()
                    val progress = if (duration > 0) currentPos / duration else 0f
                    progressCallback?.invoke(progress)
                }
                handler.postDelayed(this, 1000) // actualizar cada segundo
            }
        }
    }

    /**
     * Prepara un episodio para reproducción
     */
    fun prepareEpisode(episode: AudioEpisode) {
        if (mediaPlayer != null) {
            releaseMediaPlayer()
        }

        currentEpisode = episode
        mediaPlayer = MediaPlayer.create(context, episode.audioRes)
        mediaPlayer?.setOnCompletionListener {
            isPlaying = false
            currentPosition = 0
            progressCallback?.invoke(0f)
        }

        // Iniciar el seguimiento del progreso
        handler.post(updateProgressRunnable)
    }

    /**
     * Inicia o pausa la reproducción
     */
    fun togglePlayback(): Boolean {
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
                currentPosition = player.currentPosition
                isPlaying = false
            } else {
                player.seekTo(currentPosition)
                player.start()
                isPlaying = true
            }
            return isPlaying
        }
        return false
    }

    /**
     * Avanza 10 segundos
     */
    fun skipForward() {
        mediaPlayer?.let { player ->
            val newPosition = minOf(player.currentPosition + 10000, player.duration)
            player.seekTo(newPosition)
            currentPosition = newPosition
        }
    }

    /**
     * Retrocede 10 segundos
     */
    fun skipBackward() {
        mediaPlayer?.let { player ->
            val newPosition = maxOf(player.currentPosition - 10000, 0)
            player.seekTo(newPosition)
            currentPosition = newPosition
        }
    }

    /**
     * Cambia la posición de reproducción
     */
    fun seekTo(progress: Float) {
        mediaPlayer?.let { player ->
            val newPosition = (progress * player.duration).toInt()
            player.seekTo(newPosition)
            currentPosition = newPosition
        }
    }

    /**
     * Configura el callback para progreso
     */
    fun setProgressCallback(callback: (Float) -> Unit) {
        progressCallback = callback
    }

    /**
     * Libera recursos del MediaPlayer
     */
    fun releaseMediaPlayer() {
        mediaPlayer?.apply {
            if (isPlaying) stop()
            release()
        }
        mediaPlayer = null
        handler.removeCallbacks(updateProgressRunnable)
    }

    /**
     * Verifica si hay un episodio en reproducción
     */
    fun isPlayingEpisode(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    /**
     * Formatea segundos en formato mm:ss
     */
    fun formatTime(seconds: Float): String {
        val mins = TimeUnit.SECONDS.toMinutes(seconds.toLong())
        val remainingSeconds = seconds - TimeUnit.MINUTES.toSeconds(mins)
        return String.format("%02d:%02d", mins, remainingSeconds.toLong())
    }

    /**
     * Obtiene la duración formateada
     */
    fun getFormattedDuration(): String {
        return currentEpisode?.let {
            val mins = it.duration / 60
            val secs = it.duration % 60
            String.format("%02d:%02d", mins, secs)
        } ?: "00:00"
    }
}
