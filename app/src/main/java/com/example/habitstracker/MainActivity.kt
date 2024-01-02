package com.example.habitstracker


import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.domain.useCase.GetUserNameUseCase

class MainActivity : AppCompatActivity() {

    lateinit var MainClass: ActivityMainBinding
    lateinit var navController: NavController
    private val getUserName = GetUserNameUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(MainClass.root)

        MAIN = this

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        if(getUserName.execute() != DEFAULT_NAME) {
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

        setupWithNavController(MainClass.bottomNavigationView, navController)
    }
}