package ru.ponomarevss.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ponomarevss.starwarsapi.databinding.ItemFilmBinding
import ru.ponomarevss.starwarsapi.mvp.presenter.list.IFilmsListPresenter
import ru.ponomarevss.starwarsapi.mvp.view.list.FilmItemView

class FilmsRVAdapter(
    val presenter: IFilmsListPresenter,
) : RecyclerView.Adapter<FilmsRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemFilmBinding) : RecyclerView.ViewHolder(vb.root), FilmItemView {

        override fun setTitle(text: String) = with(vb) { tvTitle.text = text }

        override fun setDirector(text: String) = with(vb) { tvDirector.text = text }

        override fun setProducer(text: String) = with(vb) { tvProducer.text = text }

        override fun setReleaseDate(text: String) = with(vb) { tvReleaseDate.text = text }

        override var pos = -1
    }
}