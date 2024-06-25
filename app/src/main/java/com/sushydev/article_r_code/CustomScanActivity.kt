package com.sushydev.article_r_code

import android.content.pm.ActivityInfo

import android.os.Bundle
import com.journeyapps.barcodescanner.CaptureActivity

class CustomScanActivity : CaptureActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
