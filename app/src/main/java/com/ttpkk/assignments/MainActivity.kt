package com.ttpkk.assignments

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import androidx.navigation.findNavController
import com.google.android.material.navigation.NavigationView
import com.ttpkk.assignments.assignment4.model.Categories
import com.ttpkk.assignments.assignment4.products.AllProductFragment
import com.ttpkk.assignments.assignment4.products.ProductFragment
import com.ttpkk.assignments.assignment4.service.CategoryService
import com.ttpkk.assignments.assignment4.service.RetrofitInstance
import com.ttpkk.assignments.databinding.ActivityMainBinding
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        drawerLayout = binding.dl
        navView = binding.navView
        toggle = ActionBarDrawerToggle(
            this@MainActivity,
            drawerLayout,
            R.string.open_drawer,
            R.string.close_drawer)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "SATO"

        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.home -> {

                }
                R.id.products -> {
                    replaceFragment(AllProductFragment(), it.title.toString())
                }
                R.id.test -> {
                    replaceFragment(ProductFragment(), "TEST")
                }

            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment, fragment)
        fragmentTransaction.commit()

        drawerLayout.closeDrawers()
        supportActionBar?.title = title
        setTitle(title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav)
        return super.onSupportNavigateUp()
    }
}