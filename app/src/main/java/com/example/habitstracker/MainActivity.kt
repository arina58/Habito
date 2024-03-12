package com.example.habitstracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.domain.useCase.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainClass: ActivityMainBinding
    lateinit var navController: NavController
    private val getUserName = GetUserNameUseCase()
    private val getNameTheme = GetNameThemeUseCase()
    private val switchTheme = SwitchThemeUseCase()

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainClass = ActivityMainBinding.inflate(layoutInflater)

        MAIN = this

        switchTheme.execute(getNameTheme.execute())
        setContentView(mainClass.root)

        navController = Navigation.findNavController(this, R.id.navHostFragment)

        val startDestination = if (getUserName.execute() != DEFAULT_NAME) R.id.homeFragment else R.id.enterNameFragment

        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment).also { navHost ->
            val navInflater = navHost.navController.navInflater
            val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                setStartDestination(startDestination)
            }
            navHost.navController.graph = navGraph
        }

        mainClass.bottomNavigationView.setupWithNavController(navController)
        SetTaskReceiverUseCase().execute()

    }
}