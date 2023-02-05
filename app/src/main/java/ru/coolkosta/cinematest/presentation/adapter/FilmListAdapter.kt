package ru.coolkosta.cinematest.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.coolkosta.cinematest.R
import ru.coolkosta.cinematest.domain.models.TopFilmsResponse

class FilmListAdapter : RecyclerView.Adapter<FilmListAdapter.ViewHolder>() {

    private var listOfMovies: List<TopFilmsResponse> = emptyList()
    private var clickListener: OnItemClickListener? = null

    fun getData(listOfMovies: List<TopFilmsResponse>) {
        this.listOfMovies = listOfMovies
        notifyItemInserted(itemCount)
    }

    fun setOnClickListener(_clickListener: OnItemClickListener) {
        clickListener = _clickListener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var ivPoster: ImageView = view.findViewById(R.id.iv_poster)
        private var tvTitle: TextView = view.findViewById(R.id.tv_title)
        private var tvGenre: TextView = view.findViewById(R.id.tv_genre)
        private var tvYear: TextView = view.findViewById(R.id.tv_year)

        @SuppressLint("SetTextI18n")
        fun populate(model: TopFilmsResponse) {
            tvTitle.text = model.title
            tvGenre.text = model.genres[0].genre
            tvYear.text = "(${model.year})"

            Glide.with(itemView.context)
                .load(model.poster)
                .into(ivPoster)

            itemView.setOnClickListener {
                clickListener?.onClick(model.id)
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(modelId: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_movie_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.populate(model = listOfMovies[position])
    }

    override fun getItemCount(): Int {
        return listOfMovies.count()
    }
}