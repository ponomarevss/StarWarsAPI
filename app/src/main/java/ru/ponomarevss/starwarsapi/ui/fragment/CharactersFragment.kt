package ru.ponomarevss.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.ponomarevss.starwarsapi.databinding.FragmentCharactersBinding
import ru.ponomarevss.starwarsapi.mvp.model.entity.Film
import ru.ponomarevss.starwarsapi.mvp.presenter.CharactersPresenter
import ru.ponomarevss.starwarsapi.mvp.view.CharactersView
import ru.ponomarevss.starwarsapi.ui.App
import ru.ponomarevss.starwarsapi.ui.BackButtonListener
import ru.ponomarevss.starwarsapi.ui.adapter.CharactersRVAdapter

class CharactersFragment: MvpAppCompatFragment(), CharactersView, BackButtonListener {
    companion object {
        private const val FILM_ARG = "film"

        fun newInstance(film: Film) = CharactersFragment().apply {
            arguments = Bundle().apply {
                putParcelable(FILM_ARG, film)
            }
        }
    }

    val presenter: CharactersPresenter by moxyPresenter {
        val film = arguments?.getParcelable<Film>(FILM_ARG) as Film
        CharactersPresenter(AndroidSchedulers.mainThread(), film).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: CharactersRVAdapter? = null
    private var vb: FragmentCharactersBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCharactersBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvCharacters?.layoutManager = LinearLayoutManager(context)
        adapter = CharactersRVAdapter(presenter.charactersListPresenter)
        vb?.rvCharacters?.adapter = adapter
    }

    override fun setTitle(text: String) {
        activity?.title = text
    }

    override fun setHomeButton() {
        val activity = activity as AppCompatActivity
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun update() {
        adapter?.notifyDataSetChanged()
    }

    override fun setAlert(text: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(text)
            .setPositiveButton(android.R.string.cancel) { _, _ ->
                backPressed()
            }
            .show()
    }

    override fun backPressed() = presenter.backPressed()
}