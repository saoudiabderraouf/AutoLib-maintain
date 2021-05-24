package com.clovertech.autolib.ui.userProfil

//import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.clovertech.autolib.R
import com.clovertech.autolib.utils.PrefUtils
import com.clovertech.autolib.viewmodel.ProfilViewModel
import kotlinx.android.synthetic.main.fragment_profil.*

class UserProfilFragment : Fragment() {

    private lateinit var viewModel: UserProfilViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var viewModel = ViewModelProvider(this).get(ProfilViewModel::class.java)
        var id = PrefUtils.with(requireContext()).getInt(PrefUtils.Keys.ID, 0)
        if (id != 0) {
            viewModel.getThisProfil(id)
            viewModel.ResponseProfil.observe(viewLifecycleOwner, Observer {
                if(it.isSuccessful){
                    var profil = it.body()
                    if (profil != null) {
                        textView2.text=profil.firstName
                    }
                    if (profil != null) {
                        textView9.text=profil.address
                    }

                }
                else{
                }
            })
        }


    }

}