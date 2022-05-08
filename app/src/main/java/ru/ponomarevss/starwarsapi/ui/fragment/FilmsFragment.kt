package ru.ponomarevss.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.ponomarevss.starwarsapi.databinding.FragmentFilmsBinding
import ru.ponomarevss.starwarsapi.mvp.presenter.FilmsPresenter
import ru.ponomarevss.starwarsapi.mvp.view.FilmsView
import ru.ponomarevss.starwarsapi.ui.App
import ru.ponomarevss.starwarsapi.ui.BackButtonListener
import ru.ponomarevss.starwarsapi.ui.adapter.FilmsRVAdapter

class FilmsFragment: MvpAppCompatFragment(), FilmsView, BackButtonListener {
    companion object {
        fun newInstance() = FilmsFragment()
    }

    val presenter: FilmsPresenter by moxyPresenter {
        FilmsPresenter(AndroidSchedulers.mainThread()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var adapter: FilmsRVAdapter? = null
    private var vb: FragmentFilmsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFilmsBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun init() {
        vb?.rvFilms?.layoutManager = LinearLayoutManager(context)
        adapter = FilmsRVAdapter(presenter.filmsListPresenter)
        vb?.rvFilms?.adapter = adapter

        vb?.svFilm?.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                presenter.filterFilms(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.filterFilms(newText)
                return false
            }
        })
    }

    override fun setTitle(text: String) {
        activity?.title = text
    }

    override fun setHomeButton() {
        val activity = activity as AppCompatActivity
        val actionBar = activity.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(false)
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