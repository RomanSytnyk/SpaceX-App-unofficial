package romansytnyk.spacex.ui.base

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.annotation.StringRes
import com.google.android.material.snackbar.Snackbar
import romansytnyk.spacex.R
import romansytnyk.spacex.util.Utils
import java.util.*

/**
 * Created by Roman on 18.02.2018
 */

abstract class BaseFragment : androidx.fragment.app.Fragment() {
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
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // Android N can have a few locales
            resources.configuration.locales.get(0)
        } else {
            resources.configuration.locale
        }
    }
}