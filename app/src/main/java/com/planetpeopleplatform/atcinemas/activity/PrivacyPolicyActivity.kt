package com.planetpeopleplatform.atcinemas.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import com.planetpeopleplatform.atcinemas.R

class PrivacyPolicyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        val myWebView = WebView(this)
        setContentView(myWebView)
        myWebView.loadUrl(getString(R.string.atcinemas_privacy_policies_url_string))
    }
}