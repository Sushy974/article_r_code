package com.sushydev.article_r_code
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.fragment.app.Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.sushydev.article_r_code.repo.ArticleRepository


class CreationArticle : Fragment(){

    private lateinit var textTitreArticle: EditText
    private lateinit var textCommentaire: EditText
    private lateinit var textDatePeremtion: EditText
    private lateinit var switchPossedeDatePeremtion: Switch
    private lateinit var boutonSumit: Button
    private lateinit var db: FirebaseFirestore
    private lateinit var qrCodeImageView: ImageView
    private val qrCodeGenerateur = QRCodeGenerateur()


    override fun onStart() {
        super.onStart()
        textTitreArticle = view?.findViewById(R.id.editTextTitreArticle)!!
        textCommentaire = view?.findViewById(R.id.editTextCommentaire)!!
        switchPossedeDatePeremtion = view?.findViewById(R.id.switchDatePeremtion)!!
        textDatePeremtion = view?.findViewById(R.id.editTextDatePeremption)!!
        qrCodeImageView = view?.findViewById(R.id.qrCodeImageView)!!
        boutonSumit = view?.findViewById(R.id.button_submit)!!
        boutonSumit.setOnClickListener {
            creerArticle()
        }
        switchPossedeDatePeremtion.setOnCheckedChangeListener { _, isChecked ->
            textDatePeremtion.isVisible = isChecked
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.creation_article, container, false)
    }


    private fun creerArticle(){
        db = FirebaseFirestore.getInstance()
         val articleRepo: ArticleRepository = ArticleRepository(db)
        if (!verifieSiLesChampSontOk()){
            return
        }
        val datePeremption : String?
        val titre = textTitreArticle.text.toString()
        val commentaire = textCommentaire.text.toString()
        val possedeDatePeremption = switchPossedeDatePeremtion.isChecked
        datePeremption = if(possedeDatePeremption) {
            textDatePeremtion.text.toString()
        }else {
            ""
        }
        val article = Article(titre, commentaire, datePeremption)
        val qrCodeBitmap = qrCodeGenerateur.generateQRCode(article.toJson())
        qrCodeImageView.setImageBitmap(qrCodeBitmap)
        articleRepo.add(article)
    }

    private fun verifieSiLesChampSontOk(): Boolean{
        if(textTitreArticle.text.isEmpty()){
            Toast.makeText(view?.context, "Le titre est obligatoire", Toast.LENGTH_SHORT).show()
            return false
        }else if(textCommentaire.text.isEmpty()){
            Toast.makeText(view?.context, "Le commentaire est obligatoire", Toast.LENGTH_SHORT).show()
            return false
        }else if(textDatePeremtion.text.isEmpty() && switchPossedeDatePeremtion.isChecked){
            Toast.makeText(view?.context, "La date de peremption est obligatoire", Toast.LENGTH_SHORT).show()
            return false
        }else{
            return true
        }
    }





}