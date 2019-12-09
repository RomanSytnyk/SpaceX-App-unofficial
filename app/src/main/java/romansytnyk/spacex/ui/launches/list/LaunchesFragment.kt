package romansytnyk.spacex.ui.launches.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_data.*
import kotlinx.android.synthetic.main.no_internet.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.launches.details.LaunchDetailsActivity
import romansytnyk.spacex.ui.launches.list.adapter.LaunchesAdapter
import romansytnyk.spacex.ui.launches.list.adapter.OnLaunchItemClicked
import romansytnyk.spacex.util.Utils

const val IS_FUTURE = "is_future"

class LaunchesFragment : BaseFragment(), OnLaunchItemClicked {
    private val viewModel: LaunchesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initObservers()
        fetchLaunches()
    }

    private fun initObservers() {
        viewModel.data.observe(this, Observer { launches ->
            hideProgressBar()
            launches?.error?.let {
                handleFailure(it)
                return@Observer
            }

            launches?.data.let {
                recyclerView.adapter = LaunchesAdapter(
                        it ?: listOf(),
                        this)
            }
        })
    }

    private fun fetchLaunches() {
        showProgressBar()

        // Decide what launches to fetch
        val isFutureLaunchesToFetch = arguments?.getBoolean(IS_FUTURE) ?: false
        if (isFutureLaunchesToFetch) {
            viewModel.fetchFutureLaunches()
        } else {
            viewModel.fetchPastLaunches()
        }
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onLaunchItemClicked(launch: Launch) {
        LaunchDetailsActivity.start(context, launch)
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
