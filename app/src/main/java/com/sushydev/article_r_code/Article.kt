package com.sushydev.article_r_code

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException


data class Article(
    var titre: String = "",
    var commentaire: String = "",
    var datePeremption: String? = null
) {

    override fun toString(): String {
        return "Article(titre='$titre', commentaire='$commentaire', datePeremption=$datePeremption)"
    }

    fun toJson(): String {
        return Gson().toJson(this)
    }

    companion object {
        fun fromJson(json: String): Article? {
            return try {
                Gson().fromJson(json, Article::class.java)
            } catch (e: JsonSyntaxException) {
                e.printStackTrace()
                null
            }
        }
    }
}
