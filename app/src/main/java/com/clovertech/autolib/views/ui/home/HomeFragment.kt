package com.clovertech.autolib.views.ui.home


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.clovertech.autolib.R
import com.clovertech.autolib.model.AgentToken
import com.clovertech.autolib.adapters.ListTasksAdapter
import com.clovertech.autolib.adapters.TaskStepsAdapter
import com.clovertech.autolib.databinding.FragmentHomeBinding
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.NotificationViewModel
import com.clovertech.autolib.viewmodel.TaskViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlin.math.abs


class HomeFragment : Fragment() {

    private val TAG = "LOG TAG"
    private val taskViewModel: TaskViewModel by activityViewModels()
    private val notificationViewModel: NotificationViewModel by activityViewModels()
    private lateinit var adapterSteps: TaskStepsAdapter
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding
            .inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = PrefUtils.with(requireContext()).getString(PrefUtils.Keys.AGENT_NAME, "...")
        val adapter = ListTasksAdapter(findNavController())
        val tasksPager = binding.pagerTasksHome
        val taskStepRecycler = binding.taskStepsRecycler

        binding.userGreeting.text = "Hello! $name"

        tasksPager.adapter = adapter
        tasksPager.clipToPadding = false
        tasksPager.clipChildren = false
        tasksPager.offscreenPageLimit = 4

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(32))

        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }

        tasksPager.setPageTransformer(transformer)
        tasksPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                taskViewModel.task = adapter.data[position]
                adapterSteps.setListSteps(adapter.data[position].steps!!)
            }
        })

        adapterSteps = TaskStepsAdapter(taskViewModel)
        taskStepRecycler.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        taskStepRecycler.isNestedScrollingEnabled = false
        taskStepRecycler.adapter = adapterSteps

        val id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)

        if (id != 0) {
            taskViewModel.getTasksByIdAgent(requireContext(), id)
            taskViewModel.getAllTasks(requireContext())?.observe(viewLifecycleOwner,{
                val listFiltered = it.filter { task -> ((task.idTaskState == 1) || (task.idTaskState == 2)) }
                adapter.setTaskList(listFiltered)
                binding.tasksNumber.text = listFiltered.size.toString()
                tasksPager.requestTransform()
            })
        }

        taskViewModel.getTasksByIdAgent(requireContext(), id)
        binding.stepsTitle.setOnClickListener {
            findNavController().navigate(R.id.detailTache)
        }
    }

    override fun onResume() {
        super.onResume()
        sendFCMToken()
    }

    private fun sendFCMToken() {
        val idAgent = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            var token = task.result
            if (token == null) {
                token = ""
            }

            // Post token
            if(idAgent == 0){
                notificationViewModel.postFCMToken(requireContext(), AgentToken(idAgent, token))
            }

            // Log
            Log.d(TAG, "FCM token : $token")

        })
    }

}







