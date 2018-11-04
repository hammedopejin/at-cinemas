package com.planetpeopleplatform.atcinemas.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.planetpeopleplatform.atcinemas.R

class TermsAndConditionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        val myWebView = WebView(this)
        setContentView(myWebView)
        myWebView.loadUrl(getString(R.string.atcinemas_platform_terms_url_string))
    }
}