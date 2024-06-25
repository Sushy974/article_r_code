package com.sushydev.article_r_code


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope

class ArticleAdapter(private val context: Context, private val articles: List<Article>) : BaseAdapter() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return articles.size
    }

    override fun getItem(position: Int): Any {
        return articles[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: inflater.inflate(R.layout.list_item_article, parent, false)

        val article = getItem(position) as Article

        val titreTextView = view.findViewById<TextView>(R.id.titreTextView)
        val commentaireTextView = view.findViewById<TextView>(R.id.commentaireTextView)
        val datePeremptionTextView = view.findViewById<TextView>(R.id.datePeremptionTextView)

        titreTextView.text = article.titre
        commentaireTextView.text = article.commentaire
        datePeremptionTextView.text = article.datePeremption

        return view
    }
}
