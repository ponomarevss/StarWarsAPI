package ru.ponomarevss.starwarsapi.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.ponomarevss.starwarsapi.databinding.FragmentPlanetBinding
import ru.ponomarevss.starwarsapi.mvp.presenter.PlanetPresenter
import ru.ponomarevss.starwarsapi.mvp.view.PlanetView
import ru.ponomarevss.starwarsapi.ui.App
import ru.ponomarevss.starwarsapi.ui.BackButtonListener

class PlanetFragment: MvpAppCompatFragment(), PlanetView, BackButtonListener {
    companion object {
        private const val PLANET_ARG = "planet"

        fun newInstance(homeworld: String) = PlanetFragment().apply {
            arguments = Bundle().apply {
                putString(PLANET_ARG, homeworld)
            }
        }
    }

    val presenter: PlanetPresenter by moxyPresenter {
        val homeworld = arguments?.getString(PLANET_ARG) as String
        PlanetPresenter(AndroidSchedulers.mainThread(), homeworld).apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var vb: FragmentPlanetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentPlanetBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun setDiameter(text: String) {
        vb?.tvDiameter?.text = text
    }

    override fun setClimate(text: String) {
        vb?.tvClimate?.text = text
    }

    override fun setGravity(text: String) {
        vb?.tvGravity?.text = text
    }

    override fun setTerrain(text: String) {
        vb?.tvTerrain?.text = text
    }

    override fun setPopulation(text: String) {
        vb?.tvPopulation?.text = text
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