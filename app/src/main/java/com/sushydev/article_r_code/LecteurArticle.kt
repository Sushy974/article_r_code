package com.sushydev.article_r_code

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class LecteurArticle : Fragment(){

    private lateinit var scanButton: FloatingActionButton
    private lateinit var textTitle: TextView
    private lateinit var textDescription: TextView
    private lateinit var textDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.lecture_article, container, false)
    }
    override fun onStart() {
        super.onStart()
        scanButton = view?.findViewById(R.id.floatingActionButtonScanQr)!!
        textTitle = view?.findViewById(R.id.textViewTitle)!!
        textDescription = view?.findViewById(R.id.textViewDescription)!!
        textDate = view?.findViewById(R.id.textViewDatePeremtion)!!
        scanButton.setOnClickListener {
            scanQrCode()
        }
    }

    private fun scanQrCode(){
        val integrator = IntentIntegrator(this.activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan un qr code pour lire un article")
        integrator.setCameraId(0) // Use a specific camera of the device
        integrator.setBeepEnabled(true)
        integrator.captureActivity = CustomScanActivity::class.java
        integrator.setBarcodeImageEnabled(true)
        integrator.initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        val result: IntentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result.contents != null) {
            val article = Article.fromJson(result.contents)
            textTitle.text = article?.titre
            textDescription.text = article?.commentaire
            textDate.text = article?.datePeremption
        } else {
            super.onActivityResult(requestCode, resultCode, data)
            Toast.makeText(view?.context, "Impossible de lire ce QR Code", Toast.LENGTH_SHORT).show()
        }
    }
}