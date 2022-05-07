package com.reto2.pokes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.reto2.pokes.MainActivity
import com.reto2.pokes.R
import com.reto2.pokes.databinding.PokeDetailBinding
import com.reto2.pokes.databinding.PokeInfoBinding
import com.reto2.pokes.model.Poke
import java.text.SimpleDateFormat
import java.util.*

class PokeDetail : Fragment() {

    private lateinit var binding:PokeDetailBinding
    private lateinit var pokeToRender: Poke

    var isCatch:Boolean = false
    val df = SimpleDateFormat("yyyy.MM.dd")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokeDetailBinding.inflate(layoutInflater,container,false)

        val myActivity = (activity as? MainActivity)!!

        binding.profileSignoutBtn1.setOnClickListener{
            myActivity.singout(it)
        }

        if(isCatch){
            binding.catchBtn2.visibility = View.GONE
            binding.freeBtn.visibility = View.VISIBLE
            binding.pokaDateDetail.text = df.format(Date(pokeToRender.catchDate))
            binding.pokaDateDetail.visibility = View.VISIBLE
        } else {
            binding.freeBtn.visibility = View.GONE
            binding.catchBtn2.visibility = View.VISIBLE
            binding.pokaDateDetail.visibility = View.GONE
        }

        binding.back.setOnClickListener {
            myActivity.showFragment(myActivity.pokeInfo)
        }

        Glide.with(this).load(pokeToRender.img).centerCrop().into(binding.pokeImg)

        binding.pokeAtaqueDetail.text = "${pokeToRender.attack}"
        binding.pokeDefensaDetail.text = "${pokeToRender.defence}"
        binding.pokeVelDetail.text = "${pokeToRender.speed}"
        binding.pokeVidaDetail.text = "${pokeToRender.health}"

        binding.pokeNameDetail.text = pokeToRender.name
        binding.pokePoderDetail.text = pokeToRender.power


        binding.catchBtn2.setOnClickListener{
            myActivity.catchPoke(pokeToRender)
            myActivity.showFragment(myActivity.pokeInfo)
        }
        binding.freeBtn.setOnClickListener{
            myActivity.freePoke(pokeToRender)
            myActivity.showFragment(myActivity.pokeInfo)
        }


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