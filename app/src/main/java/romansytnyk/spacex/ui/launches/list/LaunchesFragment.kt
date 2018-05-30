package romansytnyk.spacex.ui.launches.list


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.launches.details.LaunchDetailsActivity
import romansytnyk.spacex.ui.launches.list.adapter.LaunchesAdapter
import romansytnyk.spacex.ui.launches.list.adapter.OnLaunchClicked


class LaunchesFragment : BaseFragment(), OnLaunchClicked {

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
        val model = ViewModelProviders.of(this).get(LaunchesViewModel::class.java)
        model.fetchLaunches().observe(this, Observer {
            hideProgressBar()
            it?.error?.let {
                handleFailure(it)
                return@Observer
            }

            it?.data.let {
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
