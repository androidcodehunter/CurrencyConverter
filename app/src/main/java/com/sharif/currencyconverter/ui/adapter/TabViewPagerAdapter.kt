package com.sharif.currencyconverter.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sharif.currencyconverter.R

class TabViewPagerAdapter(private val fragmentActivity: FragmentActivity, private val fragments: List<Fragment>):
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


    fun getPageTitle(position: Int): String {
        return fragmentActivity.resources.getString(TAB_TITLES[position])
    }

    companion object{
        private val TAB_TITLES = arrayOf(
            R.string.tab_title_rates,
            R.string.tab_title_converter,
            R.string.tab_title_alert
        )
    }

}