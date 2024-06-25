package com.sushydev.article_r_code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.firebase.firestore.FirebaseFirestore
import com.sushydev.article_r_code.repo.ArticleRepository
import kotlinx.coroutines.launch

class HistoriqueArticle : Fragment() {

    private lateinit var listView: ListView
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loadArticle()
        return inflater.inflate(R.layout.historique_creation_article, container, false)

    }
    override fun onStart() {
        super.onStart()
        listView = view?.findViewById(R.id.listViewArticle)!!
    }

    private fun loadArticle(){
        db = FirebaseFirestore.getInstance()
        val articleRepo: ArticleRepository = ArticleRepository(db)
        lifecycleScope.launch {
        articleRepo.getArticles().collect {
                articles ->
            listView.adapter = ArticleAdapter(this@HistoriqueArticle.requireContext(), articles)
            }
        }
    }

}