package com.wcx.project.clonevoice

import android.content.Context
import android.media.MediaRecorder
import android.net.Uri
import com.wcx.project.clonevoice.api.ICloneVoiceManager
import com.wcx.project.clonevoice.api.VoiceSample
import java.io.File
import java.util.UUID

/**
 * Recorder implements ICloneVoiceManager directly so consumers can use it as a manager.
 * Methods:
 * - startRecording(): returns a String id (file name without extension)
 * - stopRecording(): stops recording and returns Uri to file or null on error
 * - listSamples(): lists recorded samples
 * - deleteSample(id): deletes sample by id (filename without extension)
 */
class Recorder(private val context: Context) : ICloneVoiceManager {
    private var recorder: MediaRecorder? = null
    private var outFile: File? = null

    override fun startRecording(): String {
        val fileName = "voice_${UUID.randomUUID()}.m4a"
        val dir = File(context.filesDir, "recordings")
        if (!dir.exists()) dir.mkdirs()
        outFile = File(dir, fileName)

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(outFile!!.absolutePath)
            prepare()
            start()
        }
        // Return id as filename without extension
        return outFile!!.nameWithoutExtension
    }

    override fun stopRecording(): Uri? {
        try {
            recorder?.stop()
            recorder?.release()
            recorder = null
            return outFile?.let { Uri.fromFile(it) }
        } catch (e: Exception) {
            recorder = null
            return null
        }
    }

    override fun listSamples(): List<VoiceSample> {
        val dir = File(context.filesDir, "recordings")
        if (!dir.exists()) return emptyList()
        return dir.listFiles()?.map {
            VoiceSample(it.nameWithoutExtension, it.name, Uri.fromFile(it))
        } ?: emptyList()
    }

    override fun deleteSample(id: String): Boolean {
        val dir = File(context.filesDir, "recordings")
        if (!dir.exists()) return false
        val file = dir.listFiles()?.firstOrNull { it.nameWithoutExtension == id } ?: return false
        return try {
            file.delete()
        } catch (t: Throwable) {
            false
        }
    }
}
