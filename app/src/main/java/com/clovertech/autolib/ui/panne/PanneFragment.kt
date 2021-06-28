package com.clovertech.autolib.ui.panne

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clovertech.autolib.R
import com.clovertech.autolib.viewmodel.PanneViewModel

class PanneFragment : Fragment() {

    companion object {
        fun newInstance() = PanneFragment()
    }

    private lateinit var viewModel: PanneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_panne, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PanneViewModel::class.java)
        // TODO: Use the ViewModel
    }

}