package com.example.habitstracker.presentation.mainActivity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.habitstracker.R
import com.example.habitstracker.databinding.ActivityMainBinding
import com.example.habitstracker.di.DaggerAppComponent
import com.example.habitstracker.presentation.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component = DaggerAppComponent.factory().create(application, applicationContext)


    private val mainClass: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var vmFactory: ViewModelFactory

    private val vm: MainViewModel by lazy {
        ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
    }

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(mainClass.root)

        val navController = Navigation.findNavController(this, R.id.navHostFragment)

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