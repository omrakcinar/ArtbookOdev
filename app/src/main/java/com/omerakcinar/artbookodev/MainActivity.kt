package com.omerakcinar.artbookodev

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.omerakcinar.artbookodev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navigationController : NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        navigationController =Navigation.findNavController(this,R.id.fragmentView)
        NavigationUI.setupActionBarWithNavController(this,navigationController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.menu_options,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragmentView)
        return navController.navigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.addArtOption){


            val action = ArtListFragmentDirections.actionArtListFragmentToNewArtFragment()
            Navigation.findNavController(this,R.id.fragmentView).navigate(action)

            //val fragmentManager = supportFragmentManager
            //val fragmentTransaction = fragmentManager.beginTransaction()

            //val mainFragment = NewArtFragment()
            //fragmentTransaction.replace(R.id.fragmentView,mainFragment).commit()
        }

        return super.onOptionsItemSelected(item)
    }

}