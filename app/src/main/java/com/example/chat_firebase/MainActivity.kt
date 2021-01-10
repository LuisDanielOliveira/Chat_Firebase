package com.example.chat_firebase

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.chat_firebase.Fragments.ChatsFragment
import com.example.chat_firebase.Fragments.SearchFragment
import com.example.chat_firebase.Fragments.SettingsFragment
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar_main))

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = ""

        val tabLayout : TabLayout = findViewById(R.id.tab_layout)
        val viewPager : ViewPager = findViewById(R.id.view_pager)
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        viewPagerAdapter.addFragment(ChatsFragment(),"Chats")
        viewPagerAdapter.addFragment(SearchFragment(),"Search")
        viewPagerAdapter.addFragment(SettingsFragment(),"Settings")

        viewPager.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //LOGOUT BUTTON
         when (item.itemId) {
            R.id.action_logout -> {
                FirebaseAuth.getInstance().signOut()

                val intent = Intent(this@MainActivity,StartingActivity ::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()

                return true
            }
        }
        return false
    }

    internal  class ViewPagerAdapter(fragmentManager: FragmentManager) :
            FragmentPagerAdapter(fragmentManager) {

        private val fragments: ArrayList<Fragment>
        private val titles: ArrayList<String>

        init {
            fragments = ArrayList<Fragment>()
            titles = ArrayList<String>()
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int {
            return fragments.size
        }

        fun addFragment(fragment: Fragment, title: String){

            fragments.add(fragment)
            titles.add(title)

        }

        override fun getPageTitle(i: Int): CharSequence? {
            return titles[i]
        }

    }


}