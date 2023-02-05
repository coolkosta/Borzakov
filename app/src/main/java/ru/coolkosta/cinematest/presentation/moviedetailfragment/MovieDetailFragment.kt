package ru.coolkosta.cinematest.presentation.moviedetailfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import ru.coolkosta.cinematest.R
import ru.coolkosta.cinematest.app.CinemaTestApp
import ru.coolkosta.cinematest.databinding.FragmentMovieDetailBinding
import ru.coolkosta.cinematest.presentation.lazyViewModel
import ru.coolkosta.cinematest.presentation.popularfragment.PopularFragment

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieDetailFragmentViewModel by lazyViewModel { stateHandel ->
        (activity?.application as CinemaTestApp).appComponent.injectMovieDetailsViewModel()
            .create(stateHandel)
    }

    private val binding: FragmentMovieDetailBinding by viewBinding()
    private var filmId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as CinemaTestApp).appComponent.inject(this)

        filmId = requireArguments().getInt(PopularFragment.ARG_KEY_ID)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilmById(filmId!!)
        viewModel.cinemaState.observe(viewLifecycleOwner) {

            binding.tvTitle.text = it.title
            binding.tvDescription.text = it.description

            var genres= ""
            it.genres.forEachIndexed { index, film ->
                if (index != 0) genres += ", "
                genres += film.genre
            }
            binding.tvGenre.text = "Жанры: $genres"

            var countries = ""
            it.countries.forEachIndexed{index, film ->
                if (index !=0 ) countries += ", "
                countries += film.country
            }
            binding.tvCountry.text = "Страны: $countries"

            Glide.with(this)
                .load(it.posterUrl)
                .into(binding.banner)
        }

        binding.buttonBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }


    companion object {
        @JvmStatic
        fun newInstance() = MovieDetailFragment().apply {
            arguments = Bundle().apply {

            }
        }
    }
}