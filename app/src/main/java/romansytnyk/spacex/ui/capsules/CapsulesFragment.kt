package romansytnyk.spacex.ui.capsules


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_data.*
import kotlinx.android.synthetic.main.no_internet.*
import org.koin.android.ext.android.inject
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.capsules.adapter.CapsulesAdapter
import romansytnyk.spacex.util.Utils


class CapsulesFragment : BaseFragment() {
    private val viewModel: CapsulesViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchCapsules()
    }

    private fun fetchCapsules() {
        showProgressBar()
        viewModel.fetchCapsules().observe(this, Observer { capsules ->
            capsules?.data?.let { recyclerView.adapter = CapsulesAdapter(it) } ?: handleFailure(capsules?.error)
            hideProgressBar()
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onResume() {
        super.onResume()
        if (!Utils.isOnline(context)) {
            // User has no internet
            recyclerView.visibility = View.GONE
            noInternetLayout.visibility = View.VISIBLE
        } else {
            // Internet connection established
            recyclerView.visibility = View.VISIBLE
            noInternetLayout.visibility = View.GONE
        }
    }
}
