package com.reto2.pokes.pokeList

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.reto2.pokes.MainActivity
import com.reto2.pokes.R
import com.reto2.pokes.fragments.PokeDetail
import com.reto2.pokes.model.Poke

class Adapter() : RecyclerView.Adapter<PostVH>() {

    private var _postList: MutableLiveData<ArrayList<Poke>> = MutableLiveData()
    val postList: LiveData<ArrayList<Poke>>
        get() {
            return _postList
        }

    lateinit var myActivity: MainActivity

    init {
        _postList.value = ArrayList<Poke>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.poke_row, parent, false)
        return PostVH(view)
    }

    override fun onBindViewHolder(holder: PostVH, position: Int) {

        holder.postName.text = _postList.value!![position].name
        holder.postDate.text = _postList.value!![position].catchDate

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
            myActivity.pokeDetail.setPoke(_postList.value!![position])
            myActivity.pokeDetail.isCatch = true
            myActivity.showFragment(myActivity.pokeDetail)
        }

    }

    override fun getItemCount(): Int {
        return _postList.value?.size!!
    }

    fun addPost(temp: Poke) {
        _postList.value!!.add(temp)
        notifyItemInserted(_postList.value!!.size - 1)
    }

    fun setpokeList (pokeList: ArrayList<Poke>){
        _postList.value = pokeList
    }

}