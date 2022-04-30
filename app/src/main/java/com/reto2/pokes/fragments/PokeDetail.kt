package com.reto2.pokes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.reto2.pokes.MainActivity
import com.reto2.pokes.R
import com.reto2.pokes.databinding.PokeDetailBinding
import com.reto2.pokes.databinding.PokeInfoBinding
import com.reto2.pokes.model.Poke

class PokeDetail : Fragment() {

    private lateinit var binding:PokeDetailBinding
    private lateinit var pokeToRender: Poke

    var isCatch:Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokeDetailBinding.inflate(layoutInflater,container,false)

        val myActivity = activity as? MainActivity

        binding.profileSignoutBtn1.setOnClickListener{
            myActivity?.singout(it)
        }
        if(isCatch){
            binding.catchBtn2.visibility = View.GONE
            binding.freeBtn.visibility = View.VISIBLE
        } else {
            binding.freeBtn.visibility = View.GONE
            binding.catchBtn2.visibility = View.VISIBLE
        }

        binding.back.setOnClickListener {
            myActivity?.showFragment(myActivity?.pokeInfo)
        }

        binding.pokeAtaqueDetail.text = "${pokeToRender.attack}"
        binding.pokeDefensaDetail.text = "${pokeToRender.defence}"
        binding.pokeVelDetail.text = "${pokeToRender.speed}"
        binding.pokeVidaDetail.text = "${pokeToRender.health}"

        binding.pokeNameDetail.text = pokeToRender.name
        binding.pokePoderDetail.text = pokeToRender.power


        return binding.root
    }

    fun setPoke (poke: Poke){
        pokeToRender = poke
    }


    companion object {
        fun newInstance(): PokeDetail {
            val fragment = PokeDetail()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}