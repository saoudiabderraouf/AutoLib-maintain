package com.clovertech.autolib.views.ui.userProfil

//import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profil.*

class UserProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_profil, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        var id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)
       // id = 3
        if (id != 0) {
            viewModel.getThisProfile(id)
            viewModel.responseProfile.observe(viewLifecycleOwner, Observer {
                if (it.isSuccessful) {
                    Toast.makeText(requireContext(), it.code().toString(), Toast.LENGTH_SHORT)
                        .show()
                    var profil = it.body()
                    if (profil != null) {
                        textView2.text = profil.firstName + " " + profil.lastName
                        textView9.text = profil.address
                        textView5.text = profil.userType
                        textView7.text = profil.phoneNumber
                    }
                } else {
                    Toast.makeText(requireContext(), it.code().toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            })
        }


    }

}