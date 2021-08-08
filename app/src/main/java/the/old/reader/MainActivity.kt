package the.old.reader

import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.webkit.WebSettingsCompat
import androidx.webkit.WebViewFeature
import the.old.reader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = 0x44000000.toInt()

        binding = ActivityMainBinding.inflate(layoutInflater)
        val webView = binding.root
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                Thread {
                    if(url != null)
                        view?.loadUrl(url)
                }
                return false
            }
        }
        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK) && applicationContext.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES) {
            WebSettingsCompat.setForceDark(webView.settings, WebSettingsCompat.FORCE_DARK_ON)
        }
        webView.settings.javaScriptEnabled = true
        webView.loadUrl("https://theoldreader.com/posts/all")
        setContentView(binding.root)
    }
}