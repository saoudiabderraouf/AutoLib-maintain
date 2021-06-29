package com.clovertech.autolib.views.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.adapters.NotificationsAdapter
import com.clovertech.autolib.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.fragment_notifications.*


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        return root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notificationsViewModel = ViewModelProvider(requireActivity()).get(NotificationViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val adapter = NotificationsAdapter(requireActivity())
        recyclerView.adapter = adapter

        notificationsViewModel.fetchAllNotifications(requireContext())

        notificationsViewModel.getAllNotifications(requireContext()).observe(viewLifecycleOwner, {
            adapter.setNotificationList(it)
            notifs.text = it.size.toString()
        })
    }
}