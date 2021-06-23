package com.example.flightgear_controller_app.screens.controller

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.flightgear_controller_app.R
import com.example.flightgear_controller_app.databinding.ControllerFragmentBinding

/**
 * Fragment where the controller at
 */
class ControllerFragment : Fragment() {

    private lateinit var binding: ControllerFragmentBinding

    private lateinit var viewModel: ControllerViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.controller_fragment,
            container,
            false
        )
        Log.i("ControllerFragment", "Called ViewModelProvider.get")

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(ControllerViewModel::class.java)
        // rudder seekbar listener
        binding.rudderSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.rudderChanged(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        // throttle seekbar listener
        binding.throttleSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                viewModel.throttleChanged(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        // joystick listener
        binding.joystick.service = object : OnMoveListener {
            override fun onChange(a: Double, e: Double) {
                viewModel.aileronChanged(a)
                viewModel.elevatorChanged(e)
            }

        }
        binding.disconnectButton.setOnClickListener { onDisconnect() }

        return binding.root
    }

    /**
     * Activate when DISCONNECT button was pressed
     */
    private fun onDisconnect() {
        Toast.makeText(activity, "Disconnecting", Toast.LENGTH_SHORT).show()
        val action = ControllerFragmentDirections.actionControllerToThank()
        // go to the end screen
        NavHostFragment.findNavController(this).navigate(action)
    }

}