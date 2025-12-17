package com.example.epreuvebaptiste.manager

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
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
    fun triggerVibration() {
        try {
            val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                // Nouvelle méthode (Android 12+) ouais prendre en compte les
                // versions comment vous dire que c'est pas moi qui est pensé (ia)
                val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                vibratorManager.defaultVibrator
            } else {
                // Ancienne méthode
                @Suppress("DEPRECATION")
                context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            }

            // vibre  50ms
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
            } else {
                @Suppress("DEPRECATION")
                vibrator.vibrate(50)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}