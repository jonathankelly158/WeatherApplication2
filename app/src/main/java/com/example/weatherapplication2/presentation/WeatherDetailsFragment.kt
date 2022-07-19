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
import com.example.weatherapplication2.databinding.FragmentWeatherDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding: FragmentWeatherDetailsBinding get() = _binding!!
    private val viewModel by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
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
        with(binding) {
            viewModel.state.observe(viewLifecycleOwner) { state ->
                toolbar.title = state.input
                val selectedWeather = state.selectedWeather
                mainWeatherTv.text = selectedWeather?.temp?.toString()
                feelsLikeWeatherTv.text = getString(R.string.feel_like)
                    .format(selectedWeather?.feelLike.toString())
                descriptionTv.text = selectedWeather?.main.orEmpty()
                subDescriptionTv.text = selectedWeather?.description
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}