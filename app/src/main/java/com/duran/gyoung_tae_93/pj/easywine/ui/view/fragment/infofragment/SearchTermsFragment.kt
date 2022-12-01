package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.infofragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentSearchTermsBinding

class SearchTermsFragment : Fragment() {

    private lateinit var binding: FragmentSearchTermsBinding

    private val webView by lazy { binding.webView }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("dddd", "start1")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search_terms, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item1 = arguments?.getString("item1")

        setWebView()
        setWebViewUrl(item1)

    }

    private fun setWebViewUrl(item1: String?) {
        when(item1) {
            "마리아주" -> {
                // 링크 주소를 Load함
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%A7%88%EB%A6%AC%EC%95%84%EC%A3%BC&rlz=1C1FKPE_koKR965KR965&oq=%EC%99%80%EC%9D%B8+%EB%A7%88%EB%A6%AC%EC%95%84%EC%A3%BC&aqs=chrome..69i57j69i59l2j0i30j0i15i30l2j0i5i15i30j0i5i10i15i30j0i8i30l2.1496j0j15&sourceid=chrome&ie=UTF-8")
            }
            "빈티지" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%B9%88%ED%8B%B0%EC%A7%80&rlz=1C1FKPE_koKR965KR965&oq=%EC%99%80%EC%9D%B8+%EB%B9%88%ED%8B%B0%EC%A7%80&aqs=chrome..69i57j69i59l2j0i512l7.1489j0j15&sourceid=chrome&ie=UTF-8")
            }
            "디캔팅" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%94%94%EC%BA%94%ED%8C%85&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsZUtId8t2H62sKRypQqnAQR9hJU5Q%3A1669860798673&ei=vg2IY4bZKOmU2roPsMSKgAI&ved=0ahUKEwiGsOqdrNf7AhVpilYBHTCiAiAQ4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EB%94%94%EC%BA%94%ED%8C%85&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIECCMQJzIECCMQJzIECAAQQzIFCAAQgAQyBAgAEB46BwgjELADECc6CggAEEcQ1gQQsAM6CggAEIAEEIcCEBQ6BAguEEM6BwgAEIAEEAo6BggAEB4QCjoICAAQHhAPEApKBAhBGABKBAhGGABQ2gNY3xBgwRJoAnABeAGAAYQBiAH9CZIBBDAuMTGYAQCgAQHIAQfAAQE&sclient=gws-wiz-serp")
            }
            "아로마" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EC%95%84%EB%A1%9C%EB%A7%88%EC%99%80+%EB%B6%80%EC%BC%80&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsZgTeoEFKgacqDqiJeCHQz7FC3ZCQ%3A1669861059397&ei=ww6IY-u5F9Hf2roP7OiIiAI&ved=0ahUKEwirp5Oardf7AhXRr1YBHWw0AiEQ4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EC%95%84%EB%A1%9C%EB%A7%88%EC%99%80+%EB%B6%80%EC%BC%80&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQ6BwgjELADECc6CggAEEcQ1gQQsAM6BAgjECc6BAgAEEM6BggAEB4QCjoECAAQHjoICAAQHhAPEAo6CggAEIAEEIcCEBQ6BggAEB4QDzoGCAAQBRAeSgQIQRgASgQIRhgAUJQDWN0fYJMhaAVwAXgEgAF4iAHzDpIBBDAuMTeYAQCgAQHIAQrAAQE&sclient=gws-wiz-serp")
            }
            "산미" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EC%82%B0%EB%AF%B8&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsZtrfpfCNk7ssXfeuosq7PKopwMXA%3A1669861228948&ei=bA-IY8-6OZbN2roP1s2piAI&ved=0ahUKEwjPoIDrrdf7AhWWplYBHdZmCiEQ4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EC%82%B0%EB%AF%B8&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIFCAAQgAQyBggAEAgQHjIICAAQCBAeEA86CAgAEIAEELADOgQIIxAnOgQIABAeOgYIABAFEB46BggAEB4QDzoECAAQQzoKCAAQgAQQhwIQFEoECEEYAUoECEYYAFCEBliKEWCpEmgBcAB4AIABeIgByAqSAQQwLjEymAEAoAEByAEBwAEB&sclient=gws-wiz-serp")
            }
            "도수" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%8F%84%EC%88%98&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsZv8oEp6NU6N907IwbHIUNlUafzmA%3A1669861438646&ei=PhCIY8WEJ8zF-QagzJegCQ&ved=0ahUKEwjFm__Ortf7AhXMYt4KHSDmBZQQ4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EB%8F%84%EC%88%98&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIECAAQQzIFCAAQgAQyBQgAEIAEMgQIABAeMgYIABAeEA8yBggAEAUQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjIGCAAQBRAeOgoIABBHENYEELADOgQIIxAnOgoIABCABBCHAhAUOgQILhBDSgQIQRgASgQIRhgAULoDWMsPYOEQaAVwAXgBgAGRAYgB7gmSAQMwLjmYAQCgAQHIAQrAAQE&sclient=gws-wiz-serp")
            }
            "바디" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%B0%94%EB%94%94&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsY6-1jqS7QyKl1VoAQYpkxndBMJIg%3A1669861753347&ei=eRGIY6vZFN7a2roP-ZaU8AE&ved=0ahUKEwjr-Yblr9f7AhVerVYBHXkLBR4Q4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EB%B0%94%EB%94%94&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIECCMQJzIECCMQJzIECAAQQzIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgoIABBHENYEELADOgoIABCABBCHAhAUSgQIQRgASgQIRhgAUM4CWJ8IYLQJaAJwAXgBgAGPAYgBugaSAQMwLjaYAQCgAQHIAQrAAQE&sclient=gws-wiz-serp")
            }
            "당도" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%EB%8B%B9%EB%8F%84&rlz=1C1FKPE_koKR965KR965&sxsrf=ALiCzsbxH1C9KFI99k6Iu0mVHerLixJPeg%3A1669861877245&ei=9RGIY8DPDu-i2roPoeqOgAI&ved=0ahUKEwiAnpGgsNf7AhVvkVYBHSG1AyAQ4dUDCA8&uact=5&oq=%EC%99%80%EC%9D%B8+%EB%8B%B9%EB%8F%84&gs_lcp=Cgxnd3Mtd2l6LXNlcnAQAzIECCMQJzIECCMQJzIFCAAQgAQyCggAEIAEEIcCEBQyBQgAEIAEMgUIABCABDIECAAQHjIGCAAQBRAeMgYIABAFEB4yBggAEAUQHjoHCCMQsAMQJzoKCAAQRxDWBBCwAzoECAAQQ0oECEEYAEoECEYYAFClA1iECmCGC2gBcAF4AIABkQGIAdUGkgEDMC43mAEAoAEByAEKwAEB&sclient=gws-wiz-serp")
            }
            "타닌" -> {
                webView.loadUrl("https://www.google.com/search?q=%EC%99%80%EC%9D%B8+%ED%83%80%EB%8B%8C&hl=ko&sxsrf=ALiCzsb85Yf6kXlkfuQkBCSA2BTPaVYIuA%3A1669862101778&source=hp&ei=1RKIY42zLejm2roPgtqniAI&iflsig=AJiK0e8AAAAAY4gg5Q3Fib3BXJv1Z_dgn6-7sGCTokvp&ved=0ahUKEwjN8ZeLsdf7AhVos1YBHQLtCSEQ4dUDCAo&uact=5&oq=%EC%99%80%EC%9D%B8+%ED%83%80%EB%8B%8C&gs_lcp=Cgdnd3Mtd2l6EAMyBAgjECcyBAgjECcyBAgjECcyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEMgUIABCABDIFCAAQgAQyBQgAEIAEOgcIIxDqAhAnOgoILhCABBCHAhAUOhAIABCABBCHAhCxAxCDARAUOg4ILhCABBCxAxDHARDRAzoECAAQAzoLCAAQgAQQsQMQgwE6EQguEIAEELEDEIMBEMcBENEDOhAILhCABBCHAhCxAxCDARAUOgQIABBDOgoIABCABBCHAhAUUM0FWLUPYJ0QaANwAHgCgAF6iAHxCZIBBDAuMTGYAQCgAQGwAQo&sclient=gws-wiz")
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setWebView() {
        webView.settings.javaScriptEnabled = true // 자바 스크립트 허용
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = WebChromeClient()
        /* 웹뷰에서 새 창이 뜨지 않도록 방지하는 구문 */
        webView.setOnKeyListener(View.OnKeyListener() { v, keyCode, event ->
            if(event.action == KeyEvent.ACTION_DOWN) {
                if(keyCode == KeyEvent.KEYCODE_BACK){
                    if(webView.canGoBack()){
                        webView.goBack()
                    } else {
                        activity?.onBackPressed()
                    }
                }
            }
            return@OnKeyListener true
        })
    }

}