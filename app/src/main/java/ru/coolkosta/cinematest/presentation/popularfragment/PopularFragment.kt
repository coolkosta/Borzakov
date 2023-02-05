package ru.coolkosta.cinematest.presentation.popularfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.coolkosta.cinematest.R
import ru.coolkosta.cinematest.app.CinemaTestApp
import ru.coolkosta.cinematest.databinding.FragmentPopularBinding
import ru.coolkosta.cinematest.presentation.adapter.FilmListAdapter
import ru.coolkosta.cinematest.presentation.lazyViewModel
import ru.coolkosta.cinematest.presentation.moviedetailfragment.MovieDetailFragment


class PopularFragment : Fragment(R.layout.fragment_popular) {

    private val viewModel: PopularFragmentViewModel by lazyViewModel { stateHandel ->
        (activity?.application as CinemaTestApp).appComponent.injectMoviesListViewModel()
            .create(stateHandel)
    }

    private val binding: FragmentPopularBinding by viewBinding()
    private var listOfFilmsAdapter = FilmListAdapter()
    private lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.application as CinemaTestApp).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewContainer.adapter = listOfFilmsAdapter
        layoutManager = LinearLayoutManager(context)
        binding.recyclerViewContainer.layoutManager = layoutManager
        viewModel.cinemaListState.observe(viewLifecycleOwner) {
            listOfFilmsAdapter.getData(it)
        }

        listOfFilmsAdapter.setOnClickListener(onClick)
    }

    private val onClick = object : FilmListAdapter.OnItemClickListener {

        override fun onClick(modelId: Int) {

            val fragment: Fragment
            val bundle = Bundle()
            bundle.putInt(ARG_KEY_ID, modelId)
            fragment = MovieDetailFragment.newInstance()
            fragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.root, fragment)
                .commit()
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = PopularFragment().apply {
            arguments = Bundle().apply {

            }
        }

        const val ARG_KEY_ID = "id"
    }

}