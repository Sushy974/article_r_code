package com.sushydev.article_r_code

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView

class Main : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navController: NavController
    lateinit var bouttonCreationArticle: Button
    lateinit var bouttonLectureArticle: Button
    lateinit var bouttonHistoriqueArticle: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)



        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, findViewById(R.id.toolbar),
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        NavigationUI.setupWithNavController(navView, navController)
        navView.setNavigationItemSelectedListener(this)


        loadFragment(CreationArticle())

        bouttonCreationArticle = this.findViewById(R.id.button_creation_article)
        bouttonLectureArticle = findViewById(R.id.lecture_article)
        bouttonHistoriqueArticle = findViewById(R.id.button_historique_creation)
        bouttonCreationArticle.setOnClickListener {
            loadFragment(CreationArticle())
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        bouttonLectureArticle.setOnClickListener {
            loadFragment(LecteurArticle())
            drawerLayout.closeDrawer(GravityCompat.START)
        }
        bouttonHistoriqueArticle.setOnClickListener {
            loadFragment(HistoriqueArticle())
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }


    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        TODO("Not yet implemented")
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        fragment?.onActivityResult(requestCode, resultCode, data)
    }

}