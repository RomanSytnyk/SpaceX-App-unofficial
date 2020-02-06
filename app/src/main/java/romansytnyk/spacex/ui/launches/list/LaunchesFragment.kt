package romansytnyk.spacex.ui.launches.list


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
import romansytnyk.spacex.data.db.entity.LaunchEntity
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.launches.details.LaunchDetailsActivity
import romansytnyk.spacex.ui.launches.list.adapter.LaunchesAdapter
import romansytnyk.spacex.ui.launches.list.adapter.OnLaunchItemClicked

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
    }

    private fun initObservers() {
        val launchesObserver = Observer<Resource<List<LaunchEntity>>> { result ->
            when (result) {
                is Resource.Success -> {
                    hideProgressBar()
                    recyclerView.adapter = LaunchesAdapter(
                            result.data ?: listOf(),
                            this)
                }
                is Resource.Loading -> showProgressBar()
                is Resource.Error -> {
                    hideProgressBar()
                    showSnackbar(result.message ?: getString(R.string.error))
                }
            }
        }

        val isFutureLaunchesToFetch = arguments?.getBoolean(IS_FUTURE) ?: false
        if (isFutureLaunchesToFetch) {
            viewModel.futureLaunches.observe(viewLifecycleOwner, launchesObserver)
        } else {
            viewModel.pastLaunches.observe(viewLifecycleOwner, launchesObserver)
        }
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onLaunchItemClicked(launch: LaunchEntity) {
        LaunchDetailsActivity.start(context, launch)
    }
}
