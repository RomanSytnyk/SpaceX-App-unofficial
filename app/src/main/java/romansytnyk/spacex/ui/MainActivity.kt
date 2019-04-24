package romansytnyk.spacex.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import romansytnyk.spacex.R
import romansytnyk.spacex.ui.capsules.CapsulesFragment
import romansytnyk.spacex.ui.launches.list.LaunchesFragment
import romansytnyk.spacex.ui.rockets.RocketsFragment

private const val RESTORE_CURRENT_TAB = "current tab"

class MainActivity : AppCompatActivity() {
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        savedInstanceState?.let {
            for (fragment in supportFragmentManager.fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        }

        initBottomNavigationMenu()
    }

    // Let me know, if you know a better solution without tons of code
    private fun initBottomNavigationMenu() {
        val launchesFragment = LaunchesFragment()
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

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(RESTORE_CURRENT_TAB, bottomNavigationView.selectedItemId)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState?.let {
            bottomNavigationView.selectedItemId = savedInstanceState.getInt(RESTORE_CURRENT_TAB)
        }
    }

    private fun addAndHideFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .add(R.id.container, fragment)
                .hide(fragment)
                .commit()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.
                beginTransaction()
                .hide(currentFragment!!)
                .show(fragment)
                .commit()
        currentFragment = fragment
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == android.R.id.home) finish()
        return super.onOptionsItemSelected(item)
    }
}
