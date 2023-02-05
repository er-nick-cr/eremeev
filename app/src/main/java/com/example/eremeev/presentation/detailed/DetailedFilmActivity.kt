package com.example.eremeev.presentation.detailed

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.eremeev.R
import com.example.eremeev.databinding.ActivityDetailedFilmBinding
import com.example.eremeev.presentation.detailed.recycler.DetailedFilmAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailedFilmActivity : AppCompatActivity() {

    private val viewModel: DetailedFilmViewModel by viewModel()
    private val binding: ActivityDetailedFilmBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_film)

        val id = intent.getIntExtra("id", 0)
        val detailedFilmAdapter = DetailedFilmAdapter(::setSpannedText)

        with(binding) {
            error.button.setOnClickListener { repeatRequest(id) }
            toolbar.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }
        }

        with(viewModel) {
            detailedFilmLiveData.observe(this@DetailedFilmActivity) { detailedFilm ->
                detailedFilmAdapter.items = listOf(detailedFilm)
            }
            isLoadingLiveData.observe(this@DetailedFilmActivity) {
                    isLoading -> binding.progressBar.isVisible = isLoading
            }
            errorLiveData.observe(this@DetailedFilmActivity) { setError() }
        }

        binding.recycler.adapter = detailedFilmAdapter

        viewModel.requestDetailedFilm(id)
    }

    private fun setSpannedText(@StringRes stringResource: Int, text: String, view: TextView) {
        val string = getString(stringResource) + text
        val spanText = SpannableStringBuilder(string)

        spanText.setSpan(
                StyleSpan(Typeface.BOLD),
                0,
                string.indexOf(CLOSE_CHAR),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )

        view.text = spanText
    }

    private fun setError() = with(binding) {
        error.root.isVisible = true
    }

    private fun repeatRequest(id: Int) = with(binding) {
        error.root.isVisible = false
        viewModel.requestDetailedFilm(id)
    }

    companion object {
        private const val CLOSE_CHAR = ":"
    }
}