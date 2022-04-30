package com.reto2.pokes.pokeList

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.reto2.pokes.MainActivity
import com.reto2.pokes.R
import com.reto2.pokes.fragments.PokeDetail
import com.reto2.pokes.model.Poke

class Adapter() : RecyclerView.Adapter<PostVH>() {

    var postList = ArrayList<Poke>()
    lateinit var myActivity: MainActivity

    init {
        for (i in 1..3){
            postList.add(Poke(
                "",
                "",
                "POKES ${i}",
                "Veloz",
                i*34,
                i*45,
                i*67,
                i*12,
                "Hoy"))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_row, parent, false)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {

        holder.postName.text = postList[position].name
        holder.postDate.text = postList[position].catchDate

        // Poke Img
        /*
        val profileImg = BitmapFactory.decodeFile(postList[position].img)
        val thumb1 = Bitmap.createScaledBitmap(
            profileImg,
            profileImg.width / 12,
            profileImg.height / 12,
            true
        )
        holder.postImg.setImageBitmap(thumb1)
        */

        val profileImg = BitmapFactory.decodeResource(
            holder.postName.resources,
            R.drawable.logo
        )
        val thumb1 = Bitmap.createScaledBitmap(profileImg, profileImg.width / 12, profileImg.height / 12, true)
        holder.postImg.setImageBitmap(thumb1)


        holder.postFrame.setOnClickListener {

            myActivity.showFragment(myActivity.pokeDetail)
        }

    }

    override fun getItemCount(): Int {
        return postList.size
    }

    fun addPost(temp: Poke) {
        postList.add(temp)
        notifyItemInserted(postList.size - 1)
    }

}