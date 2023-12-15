package com.example.habitstracker


import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.habitstracker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var MainClass: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var pref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainClass.root)

        MAIN = this

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        pref = MAIN.getSharedPreferences("User", MODE_PRIVATE)
        if(pref.contains("username")) {
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).also { navHost ->
                val navInflater = navHost.navController.navInflater
                val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                    setStartDestination(R.id.homeFragment)
                }
                navHost.navController.graph = navGraph
            }
        }else {
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).also { navHost ->
                val navInflater = navHost.navController.navInflater
                val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                    setStartDestination(R.id.enterNameFragment)
                }
                navHost.navController.graph = navGraph
            }
        }
//            setupWithNavController(MainClass.bottomNavigationView, navController)

        setupWithNavController(MainClass.bottomNavigationView, navController)
    }
}