package romansytnyk.spacex.ui.base

import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.util.Failure
import java.util.*

/**
 * Created by Roman on 18.02.2018
 */

abstract class BaseFragment : Fragment() {
    private lateinit var rootView: View
    private var progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
        initProgressBar()
    }

    private fun initProgressBar() {
        val layout = rootView as ViewGroup
        progressBar = ProgressBar(rootView.context, null, android.R.attr.progressBarStyleLarge)
        progressBar?.isIndeterminate = true

        val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
        val rl = RelativeLayout(rootView.context)

        rl.gravity = Gravity.CENTER
        rl.addView(progressBar)

        layout.addView(rl, params)
        hideProgressBar()
    }

    fun handleFailure(failure: Failure?) {
        when (failure) {
            is Failure.NetworkConnection -> showSnackbar(R.string.error)
            is Failure.ServerError -> showSnackbar(failure.message)
        }
    }

    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    fun showToast(msg: String) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
    }

    fun showToast(@StringRes id: Int) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show()
    }

    fun showSnackbar(msg: String) {
        Snackbar.make(rootView, msg, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnackbar(@StringRes id: Int) {
        Snackbar.make(rootView, id, Snackbar.LENGTH_SHORT).show()
    }

    fun hideSoftKeyboard() {
        val activity = activity as BaseActivity?
        activity?.hideSoftKeyboard()
    }

    @Suppress("DEPRECATION")
    fun currentLocale(): Locale {
        val current: Locale = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            // Android N can have a few locales
            resources.configuration.locales.get(0)
        } else {
            resources.configuration.locale
        }

        return current
    }
}