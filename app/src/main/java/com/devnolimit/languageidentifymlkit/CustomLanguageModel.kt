package com.devnolimit.languageidentifymlkit

import android.util.Log
import com.google.mlkit.nl.languageid.LanguageIdentification
import com.google.mlkit.nl.languageid.LanguageIdentificationOptions

object CustomLanguageModel {

    private val languageModel = LanguageIdentification.getClient(
        LanguageIdentificationOptions.Builder()
            .setConfidenceThreshold(0.028f)
            .build()
    )

    fun predictLanguage(text: String, onSuccess: String.() -> Unit) {
       languageModel.identifyLanguage(text).addOnSuccessListener {
            onSuccess(it.orEmpty())
        }.addOnFailureListener {
            Log.e("LanguageModelError", "Error is --> ${it.localizedMessage}")
        }
    }



    fun predictMultipleLanguage(text: String, onSuccess: String.() -> Unit){
        languageModel.identifyPossibleLanguages(text).addOnSuccessListener { predictLanguages ->
            StringBuilder().apply {
                predictLanguages.forEach { predictLanguage ->
                    append("Language:- ${predictLanguage.languageTag}, Confident:- ${predictLanguage.confidence} \n")
                }
            }.toString().also {
                onSuccess(it)
            }
        }.addOnFailureListener {
            Log.e("LanguageModelError", "Error is --> ${it.localizedMessage}")
        }
    }

}