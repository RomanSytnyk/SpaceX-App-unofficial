package romansytnyk.spacex.ui.launches


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_launches_tabs.*
import romansytnyk.spacex.R

class LaunchesTabsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_launches_tabs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pagerAdapter = LaunchesPagerAdapter(parentFragmentManager, view.context)
        viewPager.adapter = pagerAdapter
        tabLayout.isTabIndicatorFullWidth = false
        tabLayout.setupWithViewPager(viewPager)
    }
}
