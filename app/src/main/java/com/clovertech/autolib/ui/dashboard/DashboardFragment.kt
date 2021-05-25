package com.clovertech.autolib.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.clovertech.autolib.R
import com.clovertech.autolib.model.Tache
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.TacheViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.michalsvec.singlerowcalendar.calendar.CalendarChangesObserver
import com.michalsvec.singlerowcalendar.calendar.CalendarViewManager
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendar
import com.michalsvec.singlerowcalendar.calendar.SingleRowCalendarAdapter
import com.michalsvec.singlerowcalendar.selection.CalendarSelectionManager
import com.michalsvec.singlerowcalendar.utils.DateUtils
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.*

class DashboardFragment : Fragment() {

    private lateinit var taskViewModel: TacheViewModel
    private lateinit var pagerAdapter: TaskFragmentAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskViewModel =
            ViewModelProvider(this).get(TacheViewModel::class.java)
        return inflater.inflate(
            R.layout.fragment_dashboard,
            container, false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCalendar(view)
        initPager(view)
        attachObservers()
    }

    private fun attachObservers() {
        taskViewModel.getAllTaches(requireContext())?.observe(viewLifecycleOwner,
            {
                pagerAdapter.updateTasksUI(it)
            })
    }

    private fun initPager(view: View) {
        pagerAdapter = TaskFragmentAdapter(this)
        viewPager = view.findViewById(R.id.pagerDashboard)
        viewPager.adapter = pagerAdapter
        viewPager.offscreenPageLimit = 2

        val tabLayout = view.findViewById<TabLayout>(R.id.tabDashboard)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position){
                0 -> tab.text = "Terminée"
                else -> tab.text = "En cours"
            }
        }.attach()


    }

    private fun initCalendar(view: View) {
        // Init view manager
        val myCalendarViewManager = object : CalendarViewManager {
            override fun setCalendarViewResourceId(
                position: Int,
                date: Date,
                isSelected: Boolean
            ): Int {
                // return item layout files, which you have created
                return if (!isSelected) {
                    R.layout.calendar_item_layout
                } else {
                    R.layout.calendar_item_selected_layout
                }
            }

            override fun bindDataToCalendarView(
                holder: SingleRowCalendarAdapter.CalendarViewHolder,
                date: Date,
                position: Int,
                isSelected: Boolean
            ) {
                // bind data to calendar item views
                val dayNumber = holder.itemView.findViewById<TextView>(R.id.textCalendarDayNumber)
                val dayName3 = holder.itemView.findViewById<TextView>(R.id.textCalendarDayName3)
                dayNumber.text = DateUtils.getDayNumber(date)
                dayName3.text = DateUtils.getDay3LettersName(date)
            }
        }

        // Init selection manager
        val mySelectionManager = object : CalendarSelectionManager {
            override fun canBeItemSelected(position: Int, date: Date): Boolean {
                // return true if item can be selected
                return true
            }
        }

        // Setup observers
        val myCalendarChangesObserver = object : CalendarChangesObserver {
            override fun whenWeekMonthYearChanged(
                weekNumber: String,
                monthNumber: String,
                monthName: String,
                year: String,
                date: Date
            ) {
                super.whenWeekMonthYearChanged(weekNumber, monthNumber, monthName, year, date)
            }

            override fun whenSelectionChanged(isSelected: Boolean, position: Int, date: Date) {
                super.whenSelectionChanged(isSelected, position, date)
            }

            override fun whenCalendarScrolled(dx: Int, dy: Int) {
                super.whenCalendarScrolled(dx, dy)
            }

            override fun whenSelectionRestored() {
                super.whenSelectionRestored()
            }

            override fun whenSelectionRefreshed() {
                super.whenSelectionRefreshed()
            }
        }

        val calendarView:SingleRowCalendar = view.findViewById(R.id.calendarView)

        val calendar = calendarView.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            futureDaysCount = 30
            includeCurrentDate = true
            init()
        }
        }

    class TaskFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

        private lateinit var doneTasksFragment: TaskPagerFragment
        private lateinit var ongoingTasksFragment: TaskPagerFragment
        private val allTasks = mutableListOf<Tache>()

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            // Return a NEW fragment instance in createFragment(int)
            doneTasksFragment = TaskPagerFragment()
            ongoingTasksFragment = TaskPagerFragment()
            //fragment.arguments = Bundle().apply {
                // Our object is just an integer :-P
                //putInt(ARG_OBJECT, position + 1)
            //}
            return when (position){
                0 -> doneTasksFragment
                else -> ongoingTasksFragment
            }
        }

        fun updateTasksUI(tasks: List<Tache>) {
            allTasks.clear()
            allTasks.addAll(tasks)
            //temp
            doneTasksFragment.updateTasks(tasks)
            ongoingTasksFragment.updateTasks(tasks)
        }
    }
}


