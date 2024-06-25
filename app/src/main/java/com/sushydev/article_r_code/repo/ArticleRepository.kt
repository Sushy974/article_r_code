package com.sushydev.article_r_code.repo

import com.google.firebase.firestore.FirebaseFirestore
import com.sushydev.article_r_code.Article
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

data class ArticleRepository(
    var db: FirebaseFirestore
) {

    fun add(article: Article) {
        db.collection("articles")
            .add(article)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }
    fun getArticles(): Flow<List<Article>> = callbackFlow {
        val subscription = db.collection("articles")
            .addSnapshotListener { snapshot, exception ->
                if (exception != null) {
                    close(exception)
                    return@addSnapshotListener
                }
                val articles = snapshot?.toObjects(Article::class.java)
                if (articles != null) {
                    trySend(articles)
                }
            }

        awaitClose { subscription.remove() }
    }
}