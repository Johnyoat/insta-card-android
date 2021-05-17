package com.sgssb.instacard.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.firebase.ui.auth.AuthUI
import com.google.android.material.transition.MaterialFadeThrough
import com.sgssb.instacard.R
import com.sgssb.instacard.databinding.ProfileFragmentBinding
import com.sgssb.instacard.ui.loginSplash.LoginSplashActivity
import com.sgssb.instacard.ui.profile.lounge.LoungeFragment

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding:ProfileFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View{
        binding = ProfileFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.findViewById<AppCompatTextView>(R.id.title).text = getString(R.string.profile)


        binding.logOutBtn.setOnClickListener {
            AuthUI.getInstance()
                .signOut(requireContext())
                .addOnCompleteListener {
                   requireActivity().finish()
                    startActivity(Intent(requireActivity(),LoginSplashActivity::class.java))
                }
        }

//        binding.loungeBtn.setOnClickListener {
//            val lounge  = LoungeFragment.newInstance()
//            lounge.setStyle(DialogFragment.STYLE_NORMAL,R.style.Theme_InstaCard)
//            lounge.show(requireActivity().supportFragmentManager,"Hola")
//        }
    }

}