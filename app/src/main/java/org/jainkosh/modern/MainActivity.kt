package org.jainkosh.modern

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var webView: WebView
    private lateinit var searchBox: EditText
    private val baseUrl = "https://www.jainkosh.org"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)
        searchBox = findViewById(R.id.search_box)
        val searchBtn = findViewById<ImageButton>(R.id.search_btn)
        val homeBtn = findViewById<MaterialButton>(R.id.btn_home)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() { override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) = false }
        webView.webChromeClient = WebChromeClient()

        searchBtn.setOnClickListener { val q = searchBox.text.toString().trim(); if (q.isNotEmpty()) { val searchUrl = "$baseUrl/?s=" + java.net.URLEncoder.encode(q, "utf-8"); webView.loadUrl(searchUrl) } }
        searchBox.setOnEditorActionListener { _, actionId, _ -> if (actionId == EditorInfo.IME_ACTION_SEARCH) { searchBtn.performClick(); true } else false }

        findViewById<MaterialButton>(R.id.btn_video).setOnClickListener { webView.loadUrl("$baseUrl/wiki/Videos") }
        findViewById<MaterialButton>(R.id.btn_audio).setOnClickListener { webView.loadUrl("$baseUrl/wiki/Audio") }
        findViewById<MaterialButton>(R.id.btn_lit).setOnClickListener { webView.loadUrl("$baseUrl/wiki/Granths") }
        findViewById<MaterialButton>(R.id.btn_study).setOnClickListener { webView.loadUrl("$baseUrl/wiki/Notes") }
        findViewById<MaterialButton>(R.id.btn_class).setOnClickListener { webView.loadUrl("$baseUrl/wiki/Onlineclass") }

        homeBtn.setOnClickListener { webView.loadUrl("about:blank"); webView.clearHistory(); }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) { override fun handleOnBackPressed() { if (webView.canGoBack()) webView.goBack() else finish() } })
    }
}
