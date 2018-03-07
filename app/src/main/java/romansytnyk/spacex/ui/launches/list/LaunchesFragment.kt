package romansytnyk.spacex.ui.launches.list


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.launches.details.LaunchDetailsActivity
import romansytnyk.spacex.ui.launches.list.adapter.LaunchesAdapter
import romansytnyk.spacex.ui.launches.list.adapter.OnLaunchClicked


class LaunchesFragment : BaseFragment(), ILaunchesView, OnLaunchClicked {
    @InjectPresenter lateinit var presenter: LaunchesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.fetchLaunches()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun showLaunches(futureLaunches: List<Launch>?, pastLaunches: List<Launch>?) {
        recyclerView.adapter = LaunchesAdapter(
                futureLaunches ?: listOf(),
                pastLaunches ?: listOf(),
                this)
    }

    override fun onLaunchItemClicked(launch: Launch) {
        LaunchDetailsActivity.start(context, launch)
    }

    override fun showErrorToast() = showToast(R.string.error)
}
