package com.ismin.projectapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ismin.projectapp.Town
import com.ismin.projectapp.fragment.FragmentOne
import com.ismin.projectapp.fragment.TownListFragment

class MyPagerAdapter(fm: FragmentManager, fragment: ArrayList<Town>) : FragmentPagerAdapter(fm) {

    val fragment : ArrayList<Town> = fragment

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0-> {
                return TownListFragment(fragment)
            }
            else -> {

                return FragmentOne()

            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0-> "Towns"
            else -> "Favorites"
        }
    }
}