package com.example.wspolicy.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wspolicy.R
import com.example.wspolicy.activities.ShowDepartmentActivity
import com.example.wspolicy.models.DepartmentItem

class DepartmentsAdapter(private val mList : List<DepartmentItem>, private val context: Context): RecyclerView.Adapter<DepartmentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DepartmentsAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.department_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: DepartmentsAdapter.ViewHolder, position: Int) {
        holder.nameTextView.text = mList[position].name
        holder.addressTextView.text = mList[position].address
        holder.itemViewLayout.setOnClickListener {
            context.startActivity(Intent(context, ShowDepartmentActivity::class.java)
                .putExtra("departmentName", mList[position].name)
                .putExtra("departmentAddress", mList[position].address)
                .putExtra("departmentBoss", mList[position].boss)
                .putExtra("departmentPhone", mList[position].phone)
                .putExtra("departmentEmail", mList[position].email)
                .putExtra("departmentDescription", mList[position].description)
                .putExtra("departmentCoords", mList[position].coords))
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemViewLayout : LinearLayout = itemView.findViewById(R.id.itemView)
        val nameTextView : TextView = itemView.findViewById(R.id.nameTextView)
        val addressTextView : TextView = itemView.findViewById(R.id.addressTextView)
    }
}