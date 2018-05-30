package romansytnyk.spacex.ui.rockets


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.rockets.adapter.RocketsAdapter


class RocketsFragment : BaseFragment() {

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
        val model = ViewModelProviders.of(this).get(RocketsViewModel::class.java)
        model.fetchLaunches().observe(this, Observer {
            it?.data?.let { recyclerView.adapter = RocketsAdapter(it) } ?: handleFailure(it?.error)
            hideProgressBar()
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
