package com.sumeyra.mybio.ui.blog


import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.sumeyra.mybio.R
import com.sumeyra.mybio.databinding.FragmentBlogBinding
import com.sumeyra.mybio.delegate.viewBinding
import com.sumeyra.mybio.utils.extension.back


class BlogFragment : Fragment(R.layout.fragment_blog ) {

    private val binding by viewBinding(FragmentBlogBinding::bind)
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        webView = binding.webView

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true


        with(binding) {
            toolbarBlog.tvToolbarTitle.text = getString(R.string.blog_page)

            toolbarBlog.ivArrowBack.setOnClickListener {
                Navigation.back(it)
            }

            webView.loadUrl(getString(R.string.medium))

            webView.webChromeClient = WebChromeClient()

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    // After loading
                    loadingLottie.visibility= View.GONE
                }
            }
        }
    }
}