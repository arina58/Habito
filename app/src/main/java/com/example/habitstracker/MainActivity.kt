package com.example.habitstracker

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.domain.useCase.GetNameThemeUseCase
import com.example.habitstracker.domain.useCase.GetUserNameUseCase
import com.example.habitstracker.domain.useCase.SwitchThemeUseCase

class MainActivity : AppCompatActivity() {

    private lateinit var mainClass: ActivityMainBinding
    lateinit var navController: NavController
    private val getUserName = GetUserNameUseCase()
    private val getNameTheme = GetNameThemeUseCase()
    private val switchTheme = SwitchThemeUseCase()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainClass = ActivityMainBinding.inflate(layoutInflater)

        MAIN = this

        switchTheme.execute(getNameTheme.execute())
        setContentView(mainClass.root)

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

        setupWithNavController(mainClass.bottomNavigationView, navController)
    }
}