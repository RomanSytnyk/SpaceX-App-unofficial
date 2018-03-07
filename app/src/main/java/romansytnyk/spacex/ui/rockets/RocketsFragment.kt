package romansytnyk.spacex.ui.rockets


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.rockets.adapter.RocketsAdapter


class RocketsFragment : BaseFragment(), IRocketsView {
    @InjectPresenter lateinit var presenter: RocketsPresenter

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

    override fun showRockets(rockets: List<Rocket>?) {
        recyclerView.adapter = RocketsAdapter(rockets ?: listOf())
    }

    override fun showErrorToast() = showToast(R.string.error)
}
