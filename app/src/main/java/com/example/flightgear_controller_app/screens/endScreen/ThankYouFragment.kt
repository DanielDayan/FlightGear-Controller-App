package com.example.flightgear_controller_app.screens.endScreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.example.flightgear_controller_app.R
import com.example.flightgear_controller_app.databinding.ThankYouFragmentBinding
import kotlin.system.exitProcess

/**
 * ThankYou Fragment , end screen of app
 */

class ThankYouFragment : Fragment() {

    private lateinit var binding: ThankYouFragmentBinding



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.thank_you_fragment,
            container,
            false
        )
        Log.i("ThankYouFragment", "Called ViewModelProvider.get")


        binding.flyAgainButton.setOnClickListener{onFlyAgain()}
        binding.goodBye.setOnClickListener{onGoodBye()}

        return binding.root
    }

    /**
     * called when pressed on Fly Again ! button
     * navigate to the start screen
     */
    private fun onFlyAgain() {
        Toast.makeText(activity, "Preparing for another flight", Toast.LENGTH_SHORT).show()
        val action = ThankYouFragmentDirections.actionRestartToConnect()
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * called when pressed on GOOD BYE button
     * close the app
     */
    private fun onGoodBye() {
        exitProcess(0);
    }
}
