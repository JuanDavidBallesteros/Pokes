package com.reto2.pokes.pokeList

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.reto2.pokes.databinding.PokeRowBinding

class PostVH(root: View) : RecyclerView.ViewHolder(root){

    private var binding: PokeRowBinding = PokeRowBinding.bind(root)

    var postImg = binding.pokeImg
    var postName = binding.pokeName
    var postDate = binding.pokeDate
    var postFrame = binding.row

}