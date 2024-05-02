package com.example.wspolicy.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.wspolicy.R
import com.example.wspolicy.activities.ShowWantedActivity
import com.example.wspolicy.models.Wanted

class WantedsAdapter(private val mList : List<Wanted>, private val context: Context) : RecyclerView.Adapter<WantedsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WantedsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.wanted_list_item, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WantedsAdapter.ViewHolder, position: Int) {
        holder.nameTextView.text = mList[position].first_name + mList[position].last_name
        Glide.with(context).load(mList[position].photo).into(holder.imageView)
        holder.itemViewLayout.setOnClickListener {
            context.startActivity(Intent(context, ShowWantedActivity::class.java)
                .putExtra("wantedStatus", mList[position].status)
                .putExtra("wantedFirstName", mList[position].first_name)
                .putExtra("wantedLastName", mList[position].last_name)
                .putExtra("wantedNickname", mList[position].nicknames)
                .putExtra("wantedDescription", mList[position].description)
                .putExtra("wantedPhoto", mList[position].photo)
                .putExtra("wantedLocation", mList[position].last_location))
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val itemViewLayout : LinearLayout = itemView.findViewById(R.id.itemView)
        val nameTextView : TextView = itemView.findViewById(R.id.wantedTextView)
        val imageView : ImageView = itemView.findViewById(R.id.wantedImageView)
    }
}