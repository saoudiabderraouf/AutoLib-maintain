package com.clovertech.autolib.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clovertech.autolib.R
import com.clovertech.autolib.ui.Notif
import com.clovertech.autolib.ui.adapters.NotificationsAdapter
import com.clovertech.autolib.ui.home.HomeViewModel

import kotlinx.android.synthetic.main.fragment_home.*


class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

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
        val vm= ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.adapter = NotificationsAdapter(requireActivity(), this.loadData())
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.divider)!!)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )


    }

    fun loadData():List<Notif> {
        val data = mutableListOf<Notif>()
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,false))
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,false))
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,false))
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,true))
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,true))
        data.add(Notif("L'administrateur LOREM Ipsum vous a attribué une révision","Il y a 2j",R.drawable.ic_launcher_background,true))
        return data
    }
}