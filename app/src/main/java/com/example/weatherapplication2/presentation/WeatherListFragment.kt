package com.example.weatherapplication2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.weatherapplication2.MainActivity
import com.example.weatherapplication2.R
import com.example.weatherapplication2.data.utils.NetworkState
import com.example.weatherapplication2.databinding.FragmentWeatherListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherListFragment : Fragment() {

    private var _binding: FragmentWeatherListBinding? = null
    private val binding: FragmentWeatherListBinding get() = _binding!!
    private val viewModel by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = (requireActivity() as MainActivity).binding.toolbar.apply {
            navigationIcon = ContextCompat.getDrawable(
                requireContext(),
                com.google.android.material.R.drawable.material_ic_keyboard_arrow_left_black_24dp
            )
            setNavigationOnClickListener { findNavController().navigateUp() }
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            toolbar.title = state.input
            binding.weatherRv.apply {
                adapter = WeatherAdapter(
                    weatherList = ((state.networkState) as NetworkState.Success).data
                ) { weather ->
                    viewModel.onWeatherSelected(weather)
                    findNavController().navigate(WeatherListFragmentDirections.weatherListFragmentToWeatherDetailsFragment())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}