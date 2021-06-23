package com.example.flightgear_controller_app.screens.connect

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.example.flightgear_controller_app.R
import com.example.flightgear_controller_app.databinding.ConnectFragmentBinding

import java.lang.Exception

/**
 * Fragment for the starting  of the app
 */
class ConnectFragment : Fragment() {
    private lateinit var binding: ConnectFragmentBinding

    private lateinit var viewModel: ConnectViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.connect_fragment, container, false)

        Log.i("ConnectFragment", "Called ViewModelProvider.get")

        // Get the viewModel
        viewModel = ViewModelProvider(this).get(ConnectViewModel::class.java)

        binding.connectButton.setOnClickListener { onConnect() }

        return binding.root
    }

    /**
     * Activates when clicked on CONNECT button
     */
    private fun onConnect() {
        try {
            val ip = binding.flightGearIP.text.toString()
            if (!Patterns.IP_ADDRESS.matcher(ip).matches())
                throw Exception()
            viewModel.flightGearIP = ip
            viewModel.flightGearPort = binding.flightGearPort.text.toString().toInt()
            viewModel.connectToFG()
        }
        catch(e:Exception) {
            Toast.makeText(activity, "Something Wrong. Please try again.",
                Toast.LENGTH_SHORT).show()
            return
        }
        // go to Controller fragment
        Toast.makeText(activity, "Connected Successfully!",
            Toast.LENGTH_SHORT).show()
        findNavController().navigate(ConnectFragmentDirections.actionConnectToController())
    }
}
