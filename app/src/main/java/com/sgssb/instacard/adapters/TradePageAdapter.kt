package com.sgssb.instacard.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TradePageAdapter(private val fm: FragmentActivity, private val fragmentList: List<Fragment>):FragmentStateAdapter(fm) {


    override fun getItemCount(): Int {
       return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }


}