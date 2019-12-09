package romansytnyk.spacex.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.capsules.CapsulesFragment
import romansytnyk.spacex.ui.launches.LaunchesTabsFragment
import romansytnyk.spacex.ui.rockets.RocketsFragment

private const val RESTORE_CURRENT_TAB = "current tab"

class MainActivity : AppCompatActivity() {
    private var currentFragment: androidx.fragment.app.Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0F
        supportActionBar?.title = getString(R.string.app_name)
        savedInstanceState?.let {
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        initBottomNavigationMenu()
    }

    // Let me know, if you know a better solution without tons of code
    private fun initBottomNavigationMenu() {
        val launchesFragment = LaunchesTabsFragment()
        val rocketsFragment = RocketsFragment()
        val capsulesFragment = CapsulesFragment()

        addAndHideFragment(capsulesFragment)
        addAndHideFragment(rocketsFragment)
        addAndHideFragment(launchesFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_launches -> showFragment(launchesFragment)
                R.id.action_rockets ->  showFragment(rocketsFragment)
                R.id.action_capsules -> showFragment(capsulesFragment)
            }
            true
        }

        currentFragment = launchesFragment
        bottomNavigationView.selectedItemId = R.id.action_launches
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(RESTORE_CURRENT_TAB, bottomNavigationView.selectedItemId)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        bottomNavigationView.selectedItemId = savedInstanceState.getInt(RESTORE_CURRENT_TAB)
    }

    private fun addAndHideFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment)
                .hide(fragment)
                .commit()
    }

    private fun showFragment(fragment: androidx.fragment.app.Fragment) {
        supportFragmentManager.
                beginTransaction()
                .hide(currentFragment!!)
                .show(fragment)
                .commit()
        currentFragment = fragment
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
