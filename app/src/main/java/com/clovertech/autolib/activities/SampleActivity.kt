package com.clovertech.autolib.activities


import android.app.SearchManager
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clovertech.autolib.R
import com.clovertech.autolib.ui.menu.DrawerAdapter
import com.clovertech.autolib.ui.menu.DrawerItem
import com.clovertech.autolib.ui.menu.SimpleItem
import com.clovertech.autolib.ui.menu.SpaceItem
import com.clovertech.autolib.ui.panne.PanneFragment
import com.clovertech.autolib.ui.settings.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import kotlinx.android.synthetic.main.activity_main.*


class SampleActivity : AppCompatActivity(), DrawerAdapter.OnItemSelectedListener {
    private lateinit var screenTitles: Array<String>
    private lateinit var screenIcons: Array<Drawable?>
    private lateinit var menuItems: Array<Int>
    private lateinit var adapter:DrawerAdapter
    private lateinit var navView: BottomNavigationView


    private var slidingRootNav: SlidingRootNav? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFirebase()
        initBottomBar()

        val toolbar = findViewById<Toolbar>(R.id.my_toolbar)
        this.setSupportActionBar(toolbar)
        toolbar.title = ""

        navView = findViewById(R.id.nav_view)

        screenIcons = this.loadScreenIcons()
        screenTitles = loadScreenTitles()
        menuItems =arrayOf(R.id.navigation_home
            , R.id.navigation_notifications
            , R.id.navigation_calendar
            ,-1
            ,-1
            , R.id.navigation_userProfil)


        adapter = DrawerAdapter(
            listOf(
                createItemFor(POS_ACCUEIL).setChecked(true),
                createItemFor(POS_NOTIF),
                createItemFor(POS_CALENDAR),
                createItemFor(POS_PANNE),
                createItemFor(POS_SETTINGS),
                createItemFor(POS_PROFIL),
                SpaceItem(48),
                createItemFor(POS_LOGOUT)
            ) as List<DrawerItem<DrawerAdapter.ViewHolder>>?
        )

        navView.setSelectedItemId(R.id.navigation_home)


        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home,
            R.id.navigation_notifications,
            R.id.navigation_calendar,
            R.id.navigation_userProfil,
            R.id.nav_panne,
            R.id.nav_settings
        ))

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        slidingRootNav = SlidingRootNavBuilder(this)
            .withToolbarMenuToggle(toolbar)
            .withMenuOpened(false)
            .withContentClickableWhenMenuOpened(false)
            .withSavedState(savedInstanceState)
            .withMenuLayout(R.layout.menu_left_drawer)
            .inject()


        adapter.setListener(this)
        val list = findViewById<RecyclerView>(R.id.list)
        list.isNestedScrollingEnabled = false
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = adapter
        adapter.setSelected(POS_ACCUEIL)
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_nav_menu, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }
        return true
    }


    override fun onItemSelected(position: Int) {
        slidingRootNav!!.closeMenu()
        var id = 0
        var isAtBottom = false
        when (position) {
            POS_ACCUEIL -> {
                id = R.id.navigation_home
                isAtBottom = true
            }
            POS_NOTIF -> {
                id = R.id.navigation_notifications
                isAtBottom = true
            }
            POS_CALENDAR -> {
                id = R.id.navigation_calendar
                isAtBottom = true
            }
            POS_PANNE -> { id = R.id.nav_panne}
            POS_SETTINGS -> { id = R.id.nav_settings}
            POS_PROFIL -> {
                id = R.id.navigation_userProfil
                isAtBottom = true
            }
            POS_LOGOUT -> { finish() }
        }
        navigateTo(id,position,isAtBottom)

    }

    private fun navigateTo(id: Int,position: Int, isAtBottom: Boolean){
        if(isAtBottom){
            navView.setSelectedItemId(menuItems[position])
        }
        val navController = findNavController(R.id.nav_host_fragment)
        navController.navigate(id)
    }

    private fun createItemFor(position: Int): SimpleItem {

        return SimpleItem(screenIcons[position], screenTitles[position])
            .withIconTint(color(R.color.textColorSecondary))
            .withTextTint(color(R.color.textColorSecondary))
            .withSelectedIconTint(color(R.color.textColorSecondary))
            .withSelectedTextTint(color(R.color.textColorSecondary))
            //.withSelectedIconTint(color(R.color.colorAccent))
            //.withSelectedTextTint(color(R.color.colorAccent))
    }

    private fun loadScreenTitles(): Array<String> {

        return resources.getStringArray(R.array.ld_activityScreenTitles)
    }


    private fun loadScreenIcons(): Array<Drawable?> {
        val ta = resources.obtainTypedArray(R.array.ld_activityScreenIcons)
        val icons = arrayOfNulls<Drawable>(ta.length())
        for (i in 0 until ta.length()) {
            val id = ta.getResourceId(i, 0)
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id)
            }
        }
        ta.recycle()
        return icons
    }

    @ColorInt
    private fun color(@ColorRes res: Int): Int {
        return ContextCompat.getColor(this, res)
    }

    companion object {
        private const val POS_ACCUEIL = 0
        private const val POS_NOTIF= 1
        private const val POS_CALENDAR = 2
        private const val POS_PANNE = 3
        private const val POS_SETTINGS = 4
        private const val POS_PROFIL = 5
        private const val POS_LOGOUT = 7
    }
    private fun initBottomBar() {
        nav_view.enableItemShiftingMode(false)
        nav_view.enableAnimation(true)
    }
}
