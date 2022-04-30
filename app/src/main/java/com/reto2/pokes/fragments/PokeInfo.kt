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
            myActivity?.singout(it)
        }

        adapter.myActivity = myActivity
        binding.pokesRV.adapter = adapter
        binding.pokesRV.layoutManager = LinearLayoutManager(context)

        binding.catchBtn.setOnClickListener(::pokeSearch)



        return binding.root
    }
    private fun pokeSearch(view: View?) {
        if(binding.catchTF2.text.toString() != ""){
            val search = pokeApi.getByName(binding.catchTF2.text.toString())

            pokeApi.poke.observe(this){
                myActivity.pokeDetail.setPoke(it)
                myActivity.showFragment(myActivity.pokeDetail)
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