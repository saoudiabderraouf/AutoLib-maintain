package com.clovertech.autolib.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
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

    private var currentMonth = 0
    private val calendar = Calendar.getInstance()

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
        /*taskViewModel.getAllTaches(requireContext())?.observe(viewLifecycleOwner,
            {
                pagerAdapter.updateTasksUI(it)
            })*/
        taskViewModel.getTacheIdAgent(PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 100))
        taskViewModel.ResponseTacheById.observe(viewLifecycleOwner, Observer {
            if (it.isSuccessful) {
                it.body()?.let { it1 ->
                    pagerAdapter.updateTasksUI(it1)
                }
            }
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

        calendar.time = Date()
        currentMonth = calendar[Calendar.MONTH]

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

        val cal = calendarView.apply {
            calendarViewManager = myCalendarViewManager
            calendarChangesObserver = myCalendarChangesObserver
            calendarSelectionManager = mySelectionManager
            setDates(getDatesOfCurrentMonth())
            init()
        }

        buttonPreviousMonthDashboard.setOnClickListener {
            cal.setDates(getDatesOfPreviousMonth())
            cal.init()
            textMonthDashboard.text = "${DateUtils.getMonthName(calendar.time)}, " +
                    "${DateUtils.getYear(calendar.time)}"
        }

        buttonNextMonthDashboard.setOnClickListener {
            cal.setDates(getDatesOfNextMonth())
            cal.init()
            textMonthDashboard.text = "${DateUtils.getMonthName(calendar.time)}, " +
                    "${DateUtils.getYear(calendar.time)}"
        }
    }

    private fun getDatesOfNextMonth(): List<Date> {
        currentMonth++ // + because we want next month
        if (currentMonth == 12) {
            // we will switch to january of next year, when we reach last month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] + 1)
            currentMonth = 0 // 0 == january
        }
        return getDates(mutableListOf())
    }

    private fun getDatesOfPreviousMonth(): List<Date> {
        currentMonth-- // - because we want previous month
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        return getDates(mutableListOf())
    }

    private fun getDatesOfCurrentMonth(): List<Date> {
        if (currentMonth == -1) {
            // we will switch to december of previous year, when we reach first month of year
            calendar.set(Calendar.YEAR, calendar[Calendar.YEAR] - 1)
            currentMonth = 11 // 11 == december
        }
        return getDates(mutableListOf())
    }

    private fun getFutureDatesOfCurrentMonth(): List<Date> {
        // get all next dates of current month
        currentMonth = calendar[Calendar.MONTH]
        return getDates(mutableListOf())
    }


    private fun getDates(list: MutableList<Date>): List<Date> {
        // load dates of whole month
        calendar.set(Calendar.MONTH, currentMonth)
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        list.add(calendar.time)
        while (currentMonth == calendar[Calendar.MONTH]) {
            calendar.add(Calendar.DATE, +1)
            if (calendar[Calendar.MONTH] == currentMonth)
                list.add(calendar.time)
        }
        calendar.add(Calendar.DATE, -1)
        return list
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
            return when (position){
                0 -> doneTasksFragment
                else -> ongoingTasksFragment
            }
        }

        fun updateTasksUI(tasks: List<Tache>) {
            allTasks.clear()
            allTasks.addAll(tasks)
            doneTasksFragment.updateTasks(getDoneTasks(tasks))
            ongoingTasksFragment.updateTasks(getOngoingTasks(tasks))
        }

        private fun getOngoingTasks(allTasks: List<Tache>): List<Tache> {
            return allTasks.filter { it.endDate == null }
        }

        private fun getDoneTasks(allTasks: List<Tache>): List<Tache> {
            return allTasks.filter { it.endDate != null }
        }
    }
}


