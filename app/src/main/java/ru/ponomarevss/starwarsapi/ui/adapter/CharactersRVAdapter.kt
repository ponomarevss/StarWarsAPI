package ru.ponomarevss.starwarsapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.ponomarevss.starwarsapi.databinding.ItemCharacterBinding
import ru.ponomarevss.starwarsapi.mvp.presenter.list.ICharactersListPresenter
import ru.ponomarevss.starwarsapi.mvp.view.list.CharacterItemView

class CharactersRVAdapter(
    val presenter: ICharactersListPresenter,
) : RecyclerView.Adapter<CharactersRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(private val vb: ItemCharacterBinding) : RecyclerView.ViewHolder(vb.root), CharacterItemView {

        override fun setName(text: String) = with(vb) { tvName.text = text }

        override fun setGender(text: String) = with(vb) { tvGender.text = text }

        override fun setBirthYear(text: String) = with(vb) { tvBirthYear.text = text }

        override var pos = -1
    }
}