package com.sharif.currencyconverter.ui.home

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.ui.adapter.TabViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeTabViewPager()

        toolbar.setNavigationOnClickListener {
            showAreYouSureDialog()
        }
    }

    /**
     * When user click on back button, this show a confirmation dialog.
     */
    private fun showAreYouSureDialog() {
        AlertDialog.Builder(this)
            .setMessage(R.string.are_you_sure_exit)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                finish()
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    private fun initializeTabViewPager() {
        val tabViewPagerAdapter = TabViewPagerAdapter(this, getTabFragments())
        pagerCurrency.apply {
            adapter = tabViewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

        TabLayoutMediator(tabLayout, pagerCurrency){tab, position ->
            tab.text = tabViewPagerAdapter.getPageTitle(position)
            Timber.d("position $position")
        }.attach()
    }

    /**
     * Initialize all fragments required for Tab ViewPager.
     * @return list of fragments
     */
    private fun getTabFragments(): MutableList<Fragment> {
        return mutableListOf(
            FragmentRates.newInstance(),
            FragmentConverter.newInstance()
        )
    }

}
