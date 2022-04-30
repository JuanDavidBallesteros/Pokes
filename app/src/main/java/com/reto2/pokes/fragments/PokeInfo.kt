package com.reto2.pokes.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.reto2.pokes.MainActivity
import com.reto2.pokes.databinding.PokeInfoBinding
import com.reto2.pokes.pokeApi.PokeApi
import com.reto2.pokes.pokeList.Adapter

class PokeInfo : Fragment() {
    private lateinit var binding: PokeInfoBinding

    val adapter = Adapter()
    val pokeApi = PokeApi.createApi()
    private lateinit var myActivity : MainActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokeInfoBinding.inflate(layoutInflater,container,false)

        myActivity = (activity as? MainActivity)!!



        binding.welcomeTX.text = "Welcome ${myActivity.user?.username}"

        binding.profileSignoutBtn2.setOnClickListener{
            myActivity.singout(it)
        }

        adapter.myActivity = myActivity

        binding.pokesRV.adapter = adapter
        binding.pokesRV.layoutManager = LinearLayoutManager(context)

        adapter.postList.observe(viewLifecycleOwner){
            pokeList ->
            binding.pokesRV.adapter = adapter
        }

        myActivity.catchedPokeList(adapter)


        binding.catchBtn.setOnClickListener {
            getPoke(1)
        }
        binding.seeBtn.setOnClickListener {
            getPoke(0)
        }

        binding.searchBtn.setOnClickListener {
            if(binding.searchTF.text.toString() != ""){
                myActivity.pokeSearch(binding.searchTF.text.toString())
            } else{
                Toast.makeText(context, "Campo Vacío", Toast.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    private fun getPoke(type : Number){

        if(binding.catchTF2.text.toString() != ""){
            pokeApi.getByName(binding.catchTF2.text.toString())

            pokeApi.poke.observe(viewLifecycleOwner){

                when (type) {
                    0  -> {
                        myActivity.pokeDetail.setPoke(it)
                        myActivity.showFragment(myActivity.pokeDetail)
                    }
                    1 -> {
                        myActivity.catchPoke(it)
                    }
                }

            }

        }else{
            Toast.makeText(context, "Campo Vacío", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        fun newInstance(): PokeInfo {
            val fragment = PokeInfo()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}