package com.example.habitstracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.domain.useCase.*
import com.example.habitstracker.viewModel.AnalysisViewModel
import com.example.habitstracker.viewModel.FinishViewModel
import com.example.habitstracker.viewModel.HomeViewModel
import com.example.habitstracker.viewModel.MainViewModel
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainClass: ActivityMainBinding
    var vmAnalysis: AnalysisViewModel? = null
    var vmFinish: FinishViewModel? = null
    lateinit var navController: NavController
     lateinit var vm: MainViewModel
    lateinit var vmHome: HomeViewModel

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainClass.root)
        MAIN = this

        vm = ViewModelProvider(this)[MainViewModel::class.java]
        navController = Navigation.findNavController(this, R.id.navHostFragment)

        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .also { navHost ->
            val navInflater = navHost.navController.navInflater
            val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                vm.startDestination.value?.let { setStartDestination(it) }
            }
            navHost.navController.graph = navGraph
        }

        mainClass.bottomNavigationView.setupWithNavController(navController)

        vm.theme.observe(this){
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
                .also { navHost ->
                    val navInflater = navHost.navController.navInflater
                    val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                        setStartDestination(R.id.settingsFragment)
                    }
                    navHost.navController.graph = navGraph
                }
            mainClass.bottomNavigationView.setupWithNavController(navController)
        }
    }
}