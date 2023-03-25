package com.example.googlewebview

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import com.example.googlewebview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var binding : ActivityMainBinding ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        initWebView()
        initToolBar()
    }

    private fun initToolBar(){
        binding?.toolbarMainActivity?.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.goBack -> {
                    if(binding?.WebView?.canGoBack() == true) {
                        binding?.WebView?.goBack()
                    }
                    true
                }
                R.id.goForward -> {
                    if (binding?.WebView?.canGoForward() == true){
                        binding?.WebView?.goForward()
                    }
                    true
                }
                R.id.refresh -> {
                    binding?.WebView?.reload()
                    true
                }
                else -> false
            }
        }
    }

    private fun initWebView() {
        binding?.WebView?.loadUrl("https://www.google.com/")
        binding?.WebView?.settings?.javaScriptEnabled = true
        binding?.WebView?.settings?.setSupportZoom(true)
        binding?.WebView?.isDrawingCacheEnabled = true
        binding?.WebView?.webViewClient = object : WebViewClient(){
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                binding?.pgBar?.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding?.pgBar?.visibility = View.GONE
                super.onPageFinished(view, url)
            }
        }
    }

    override fun onBackPressed() {
        if(binding?.WebView?.canGoBack() == true){
            binding?.WebView?.goBack()
        }else{
            super.onBackPressed()
        }
    }
}