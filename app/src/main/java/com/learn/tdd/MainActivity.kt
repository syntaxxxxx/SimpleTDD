package com.learn.tdd

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.learn.tdd.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    companion object {
        const val MAIN_FRAGMENT = "main_fragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        supportActionBar?.setDisplayShowCustomEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        val customToolbar = layoutInflater.inflate(R.layout.custom_toolbar, null)
        supportActionBar?.customView = customToolbar

        if (savedInstanceState == null) {
            gotoMainFragment()
        }
    }

    private fun gotoMainFragment() {
        val fragment = MainFragment.newInstance()
        openFragment(fragment, MAIN_FRAGMENT)
    }

    private fun openFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .commitNow()
    }

    private fun getFragment(tag: String): Fragment? {
        return supportFragmentManager.findFragmentByTag(tag)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_stars_action -> sortByStars()
            R.id.sort_by_name_action -> sortByName()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortByStars(): Boolean {
        (getFragment(MAIN_FRAGMENT) as MainFragment?)?.sortByStars()
        return true
    }

    private fun sortByName(): Boolean {
        (getFragment(MAIN_FRAGMENT) as MainFragment?)?.sortByName()
        return true
    }

}
