package com.example.epreuvebaptiste.manager

import android.content.Context
import android.media.MediaPlayer
import android.provider.Settings

/**
 * Manager responsable des intéractions sonores.
 */
class SoundManager(private val context: Context) {

    /**
     * Joue le son de notification par défaut du système.
     */
    fun playClickSound() {
        try {
            // récupération su son
            val defaultSoundUri = Settings.System.DEFAULT_NOTIFICATION_URI

            // lecteur
            val mediaPlayer = MediaPlayer.create(context, defaultSoundUri)

            // on joue le son
            mediaPlayer?.start()

            // nettoyage de la mémoire une fois que c'est fini
            mediaPlayer?.setOnCompletionListener { it.release() }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}