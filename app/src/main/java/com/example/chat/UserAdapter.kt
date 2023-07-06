package com.example.chat

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class UserAdapter(val context: Context, val userList: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.userViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.usario_recycle, parent, false)
        return userViewHolder(view)
    }


    override fun onBindViewHolder(holder: userViewHolder, position: Int) {

        val currentUser = userList[position]

        holder.textName.text = currentUser.name

        holder.itemView.setOnClickListener{
            val intent = Intent(context,ChatActivity::class.java)

            intent.putExtra("name",currentUser.name)
            intent.putExtra("uid",currentUser.uid)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class userViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textName = itemView.findViewById<TextView>(R.id.username_texto)
    }
}