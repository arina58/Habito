package com.example.habitstracker.presentation.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View.GONE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.HabitoApp
import com.example.habitstracker.R
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: MainViewModel by lazy {
        ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
    }

    private val component by lazy {
        (application as HabitoApp).component
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHostFragment.navController

        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
            .also { navHost ->
            val navInflater = navHost.navController.navInflater
            val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                vm.startDestination.value?.let { setStartDestination(it) }
            }
            navHost.navController.graph = navGraph
        }

        binding.bottomNavigationView.setupWithNavController(navController)

        vm.theme.observe(this){
            (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
                .also { navHost ->
                    val navInflater = navHost.navController.navInflater
                    val navGraph = navInflater.inflate(R.navigation.nav_graph).apply {
                        setStartDestination(R.id.settingsFragment)
                    }
                    navHost.navController.graph = navGraph
                }
            binding.bottomNavigationView.setupWithNavController(navController)
        }

        vm.finishedHabits.observe(this){
            vm.showFinishedHabits(supportFragmentManager)
        }
    }
}