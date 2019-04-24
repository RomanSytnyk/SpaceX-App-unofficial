package romansytnyk.spacex.ui.launches.list


import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_data.*
import org.koin.android.ext.android.inject
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.launches.details.LaunchDetailsActivity
import romansytnyk.spacex.ui.launches.list.adapter.LaunchesAdapter
import romansytnyk.spacex.ui.launches.list.adapter.OnLaunchClicked


class LaunchesFragment : BaseFragment(), OnLaunchClicked {
    private val viewModel: LaunchesViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchLaunches()
    }

    private fun fetchLaunches() {
        showProgressBar()
        viewModel.fetchLaunches().observe(this, Observer { launches ->
            hideProgressBar()
            launches?.error?.let {
                handleFailure(it)
                return@Observer
            }

            launches?.data.let {
                recyclerView.adapter = LaunchesAdapter(
                        it?.futureLaunches ?: listOf(),
                        it?.pastLaunches ?: listOf(),
                        this)
            }
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onLaunchItemClicked(launch: Launch) {
        LaunchDetailsActivity.start(context, launch)
    }
}
