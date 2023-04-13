package com.jomeva.chatgpt

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


class MainActivity : AppCompatActivity() {
    private lateinit var webView:WebView
private lateinit var swipe:SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
swipe=findViewById(R.id.swipe)
        webView=findViewById(R.id.webView)
        val URL_PRINCIPAL =
            "https://chat.openai.com/chat"
        //val URL_PRINCIPAL="https://google.com"


            swipe.isRefreshing=false
            swipe.setOnRefreshListener {
                //swipe.setColorSchemeColors(Color.GREEN,Color.YELLOW,Color.RED)
                swipe.setBackgroundColor(Color.RED)
                webView.reload()
            }




            webView.webChromeClient = object : WebChromeClient() {

            }
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    return false
                }

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    swipe.isRefreshing=true

                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    swipe.isRefreshing=false
                }



            }

            val setting = webView.settings
            setting.javaScriptEnabled = true
            webView.loadUrl(URL_PRINCIPAL)


        }
}