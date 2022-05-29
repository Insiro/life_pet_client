package com.insiro.lifepet

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.insiro.lifepet.achievement.Achievement
import com.insiro.lifepet.pet.pet_info

class NavigationBar(val context: Activity) :
    BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val intent = when (item.itemId) {
            R.id.bottom_menu1 -> Intent(context, DashBoard::class.java)
            R.id.bottom_menu2 -> Intent(context, pet_info::class.java)
            R.id.bottom_menu3 -> Intent(context, ScheduleActivity::class.java)
            R.id.bottom_menu4 -> Intent(context, Achievement::class.java)
            R.id.bottom_menu5 -> Intent(context, Settings::class.java)
            else -> return false
        }
        context.startActivity(intent)
        context.finish()
        return true
    }
}