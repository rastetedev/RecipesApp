package com.rastete.recipesapp.presentation.recipes.detail.instructions

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.rastete.recipesapp.databinding.FragmentInstructionsRecipeBinding
import com.rastete.recipesapp.domain.Recipe
import com.rastete.recipesapp.presentation.util.parcelable

class InstructionsRecipeFragment : Fragment() {

    private var _binding: FragmentInstructionsRecipeBinding? = null
    private val binding get() = _binding!!

    private val recipe by lazy {
        arguments?.parcelable<Recipe>("recipe")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipe?.let {
            binding.webInstructionsRecipeF.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    binding.pbLoadingInstructionsRecipeF.visibility = View.GONE
                    binding.webInstructionsRecipeF.visibility   = View.VISIBLE
                }
            }

            binding.webInstructionsRecipeF.settings.javaScriptEnabled = true
            binding.webInstructionsRecipeF.loadUrl(it.sourceUrl)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}