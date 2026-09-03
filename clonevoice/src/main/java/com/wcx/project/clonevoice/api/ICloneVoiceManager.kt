package com.wcx.project.clonevoice.api

import android.net.Uri

/**
 * Manager interface for voice recording and sample management.
 * Implementations must not perform UI or permission requests — caller handles permissions.
 */
interface ICloneVoiceManager {
    /**
     * Start a new recording session.
     * Returns a String id (opaque identifier for the recording, e.g. file name without extension).
     */
    fun startRecording(): String

    /**
     * Stop the current recording and return the Uri to the recorded file, or null if none.
     */
    fun stopRecording(): Uri?

    /**
     * List stored voice samples.
     */
    fun listSamples(): List<VoiceSample>

    /**
     * Delete a sample by id (the id returned from startRecording/listSamples).
     * Return true if deletion succeeded.
     */
    fun deleteSample(id: String): Boolean
}
