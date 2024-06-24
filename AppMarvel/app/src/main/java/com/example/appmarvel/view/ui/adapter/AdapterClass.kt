package com.example.appmarvel.view.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.appmarvel.R
import com.example.appmarvel.data.remote.service.DataClass

class AdapterClass : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    private val differ = object : DiffUtil.ItemCallback<DataClass>() {
        override fun areItemsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: DataClass, newItem: DataClass): Boolean {
            return oldItem.dataTitle == newItem.dataTitle &&
                    oldItem.dataDescription == newItem.dataDescription &&
                    oldItem.dataUrl == newItem.dataUrl &&
                    oldItem.dataFavorite == newItem.dataFavorite
        }
    }

    private val asyncDiffer = AsyncListDiffer(this, differ)

    var dataList: ArrayList<DataClass>
        get() = ArrayList(asyncDiffer.currentList)
        set(value) = asyncDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_characteres, parent, false)
        return ViewHolderClass(itemView)
    }

    inner class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvImage: ImageView = itemView.findViewById(R.id.imgCharacterImage)
        val rvTitle: TextView = itemView.findViewById(R.id.txtCharacterName)
        val rvFavorite: ImageView = itemView.findViewById(R.id.ic_favorite)
        val rvClickedArea: ConstraintLayout = itemView.findViewById(R.id.clCharacters)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        with(holder) {
            rvTitle.text = currentItem.dataTitle
            if (currentItem.dataFavorite) {
                rvFavorite.setImageResource(R.drawable.ic_favorite_red)
            } else {
                rvFavorite.setImageResource(R.drawable.ic_favorite)
            }

            rvImage.load(
                currentItem.dataUrl + "." + currentItem.extension
            )

            rvFavorite.setOnClickListener {
                if (!currentItem.dataFavorite) {
                    currentItem.dataFavorite = true
                    rvFavorite.setImageResource(R.drawable.ic_favorite_red)
                    isFavoriteClicked?.let {
                        it(currentItem)
                    }
                } else {
                    currentItem.dataFavorite = false
                    rvFavorite.setImageResource(R.drawable.ic_favorite)
                    isFavoriteClicked?.let {
                        it(currentItem)
                    }
                }
            }

            rvClickedArea.setOnClickListener {
                onItemClickListener?.let {
                    it(currentItem)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private var isFavoriteClicked: ((DataClass) -> Unit)? = null

    fun setOnFavoriteClicked(itemCLicked: (DataClass) -> Unit) {
        isFavoriteClicked = itemCLicked
    }

    private var onItemClickListener: ((DataClass) -> Unit)? = null

    fun setOnItemClickListener(listener: (DataClass) -> Unit) {
        onItemClickListener = listener
    }

}