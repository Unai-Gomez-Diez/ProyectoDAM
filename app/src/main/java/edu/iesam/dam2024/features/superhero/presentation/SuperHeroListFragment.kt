package edu.iesam.dam2024.features.superhero.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.dam2024.R
import edu.iesam.dam2024.app.extensions.loadUrl
import edu.iesam.dam2024.databinding.FragmentSuperheroListBinding
import edu.iesam.dam2024.features.movies.domain.ErrorApp
import edu.iesam.dam2024.features.movies.domain.Movie
import edu.iesam.dam2024.features.movies.presentation.MovieViewModel
import edu.iesam.dam2024.features.superhero.domain.SuperHero

class SuperHeroListFragment : Fragment() {
    private var _binding: FragmentSuperheroListBinding? = null
    private val binding get() = _binding!!

    private lateinit var factory: SuperHeroFactory
    private lateinit var viewModel: SuperHeroListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSuperheroListBinding.inflate(inflater, container, false)
        return binding.root
    }




    private fun bindData(superheros: List<SuperHero>) {
        binding.superheroAlias1.text = superheros[0].alias
        binding.superheroSuperpoder1.text = superheros[0].superPoder
        val imageView1 = binding.imagen1
        imageView1.loadUrl(superheros[0].imagen)
        binding.layout1.setOnClickListener {
            navigateToDetails(superheros[0].id)

        }
        binding.superheroAlias2.text = superheros[1].alias
        binding.superheroSuperpoder2.text = superheros[1].superPoder
        val imageView2 = binding.imagen2
        imageView2.loadUrl(superheros[1].imagen)
        binding.layout2.setOnClickListener {
            navigateToDetails(superheros[1].id)

        }
        binding.superheroAlias3.text = superheros[2].alias
        binding.superheroSuperpoder3.text = superheros[2].superPoder
        val imageView3 = binding.imagen3
        imageView3.loadUrl(superheros[2].imagen)
        binding.layout3.setOnClickListener {
            navigateToDetails(superheros[2].id)

        }
        binding.superheroAlias4.text = superheros[3].alias
        binding.superheroSuperpoder4.text = superheros[3].superPoder
        val imageView4 = binding.imagen4
        imageView4.loadUrl(superheros[3].imagen)
        binding.layout4.setOnClickListener {
            navigateToDetails(superheros[3].id)

        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        factory = SuperHeroFactory(requireContext())
        viewModel = factory.getSuperHeroListViewModel()
        setupObserver()
        viewModel.loadSuperHeroes()
    }
    private fun setupObserver() {
        val superheroObserver = Observer<SuperHeroListViewModel.UiState> { uiState ->
            uiState.superHeroes?.let {
                bindData(it)
            }
            uiState.errorApp?.let {
                //pinto el error
            }
            if (uiState.isLoading) {
                //muestro el cargando...
                Log.d("@dev", "Cargando...")
            } else {
                //oculto el cargando...
                Log.d("@dev"," Cargado ...")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, superheroObserver)
    }
    private fun showError(error: ErrorApp){
        when(error){
            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetErrorApp -> TODO()
            ErrorApp.ServerErrorApp -> TODO()
            ErrorApp.UnknowErrorApp -> TODO()
            ErrorApp.TestErrorApp -> TODO()
        }
    }
    private fun navigateToDetails(superHeroId: String){ //llega bien
        findNavController().navigate(SuperHeroListFragmentDirections
            .actionSuperheroFragmentToSuperheroFragmentDetail(superHeroId)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
