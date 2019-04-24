package romansytnyk.spacex.ui.rockets

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_data.*
import org.koin.android.ext.android.inject
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.rockets.adapter.RocketsAdapter


class RocketsFragment : BaseFragment() {
    private val viewModel: RocketsViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchRockets()
    }

    private fun fetchRockets() {
        showProgressBar()
        viewModel.fetchLaunches().observe(this, Observer { launches ->
            launches?.data?.let { recyclerView.adapter = RocketsAdapter(it) } ?: handleFailure(launches?.error)
            hideProgressBar()
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
