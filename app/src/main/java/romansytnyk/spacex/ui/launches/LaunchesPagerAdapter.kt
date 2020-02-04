package romansytnyk.spacex.ui.launches

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.launches.list.IS_FUTURE
import romansytnyk.spacex.ui.launches.list.LaunchesFragment

class LaunchesPagerAdapter(fm: FragmentManager, private val context: Context) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val fragments: Array<Fragment>
    private val pageTitles: Array<String>

    init {
        // Fragments
        val pastLaunches = LaunchesFragment()

        val futureLaunches = LaunchesFragment()
        val futureLaunchesBundle = Bundle()
        futureLaunchesBundle.putBoolean(IS_FUTURE, true)
        futureLaunches.arguments = futureLaunchesBundle

        fragments = arrayOf(pastLaunches, futureLaunches)

        // Strings
        val pastTitle = getString(R.string.past)
        val futureTitle = getString(R.string.future)

        pageTitles = arrayOf(pastTitle, futureTitle)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return pageTitles[position]
    }

    private fun getString(id: Int): String {
        return context.getString(id)
    }
}
