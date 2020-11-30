package com.ismin.projectapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ismin.projectapp.R
import com.ismin.projectapp.adapter.MyPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar
import kotlinx.android.synthetic.main.activity_town_list.*


class MainActivity : AppCompatActivity() {

    private val TownListActivityRequestCode = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = "Population Cities"
        setSupportActionBar(toolbar)

        web.webViewClient = CustomWebViewClient(this)
        web.loadUrl("https://www.top10hq.com/top-10-populated-cities-europe/")

    }

    fun ShowTownList(view: View) {
        val intent = Intent(this, TownListActivity::class.java)
        startActivityForResult(intent, this.TownListActivityRequestCode)
    }


    /**Toolbar Settings**/
   /**override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }**/

}


class CustomWebViewClient internal constructor(private val activity: Activity) :
        WebViewClient() {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
    ): Boolean {
        val url: String = request?.url.toString();
        view?.loadUrl(url)
        return true
    }

    override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
        webView.loadUrl(url)
        return true
    }

    override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
    ) {
        Toast.makeText(activity, "Error! $error", Toast.LENGTH_SHORT).show()
    }
}