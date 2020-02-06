package romansytnyk.spacex.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import romansytnyk.spacex.R
import romansytnyk.spacex.data.core.Resource
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.rockets.adapter.RocketsAdapter


class RocketsFragment : BaseFragment() {
    private val viewModel: RocketsViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchRockets()
    }

    private fun fetchRockets() {
        viewModel.rockets.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    recyclerView.adapter = RocketsAdapter(result.data)
                    hideProgressBar()
                }
                is Resource.Loading -> showProgressBar()
                is Resource.Error -> {
                    hideProgressBar()
                    showSnackbar(result.message)
                }
            }
        })
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}
