package romansytnyk.spacex.ui.capsules


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_data.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import romansytnyk.spacex.R
import romansytnyk.spacex.data.util.core.Resource
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.capsules.adapter.CapsulesAdapter


class CapsulesFragment : BaseFragment() {
    private val viewModel: CapsulesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        fetchCapsules()
    }

    private fun fetchCapsules() {
        viewModel.capsules.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Success -> {
                    recyclerView.adapter = CapsulesAdapter(result.data)
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
