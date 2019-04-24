package romansytnyk.spacex.ui.capsules


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.capsules.adapter.CapsulesAdapter


class CapsulesFragment : BaseFragment() {

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
        val model = ViewModelProviders.of(this).get(CapsulesViewModel::class.java)
        model.fetchCapsules().observe(this, Observer { capsules ->
            capsules?.data?.let { recyclerView.adapter = CapsulesAdapter(it) } ?: handleFailure(capsules?.error)
            hideProgressBar()
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
