package romansytnyk.spacex.ui.capsules


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.fragment_list_data.*
import romansytnyk.spacex.R
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.ui.base.BaseFragment
import romansytnyk.spacex.ui.capsules.adapter.CapsulesAdapter


class CapsulesFragment : BaseFragment(), ICapsulesView {
    @InjectPresenter lateinit var presenter: CapsulesPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        presenter.fetchCapsules()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun showCapsules(capsules: List<Capsule>?) {
        recyclerView.adapter = CapsulesAdapter(capsules ?: listOf())
    }

    override fun showErrorToast() = showToast(R.string.error)
}
