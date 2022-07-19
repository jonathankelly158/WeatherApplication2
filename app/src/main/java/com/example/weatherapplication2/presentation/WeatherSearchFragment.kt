package com.example.weatherapplication2.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.weatherapplication2.MainActivity
import com.example.weatherapplication2.R
import com.example.weatherapplication2.data.utils.NetworkState
import com.example.weatherapplication2.databinding.FragmentWeatherSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherSearchFragment : Fragment() {

    private var _binding: FragmentWeatherSearchBinding? = null
    private val binding: FragmentWeatherSearchBinding get() = _binding!!
    private val viewModel by activityViewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).binding.toolbar.apply {
            title = getString(R.string.app_name)
            navigationIcon = null
        }
        with(binding) {
            cityEt.addTextChangedListener {
                viewModel.onInput(it.toString())
            }
            searchButton.setOnClickListener {
                viewModel.getWeatherForCity()
            }

            viewModel.state.observe(viewLifecycleOwner) { state ->
                progressCircular.isVisible = state.isLoading
                group.isVisible = !state.isLoading
                if (state.networkState is NetworkState.Success)
                    findNavController().navigate(WeatherSearchFragmentDirections.weatherSearchFragmentToWeatherListFragment())
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}