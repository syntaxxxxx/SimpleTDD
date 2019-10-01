package com.learn.tdd.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.learn.tdd.R
import com.learn.tdd.data.entity.TrendingRepo
import com.learn.tdd.util.GlideApp
import com.learn.tdd.util.StringFormat

class RepoAdapter(private val trendingRepos: List<TrendingRepo>) :
    RecyclerView.Adapter<RepoAdapter.Holder>() {

    private lateinit var currentExpanded: LinearLayout

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_list_item, parent, false) as View
        return Holder(view)
    }

    override fun getItemCount(): Int = trendingRepos.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        with(trendingRepos[position]) {
            holder.username.text = author
            holder.repositoryName.text = name
            holder.description.text = description
            holder.forkCount.text = StringFormat.printThousand(forks)
            holder.starCount.text = StringFormat.printThousand(stars)
            holder.language.text = language
            if(!languageColor.isNullOrEmpty()){
                holder.languageColor.setBackgroundColor(Color.parseColor(languageColor))
            }
            GlideApp.with(holder.ivAvatar.context)
                .load(avatar)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(holder.ivAvatar)
        }

        holder.rootLayout.setOnClickListener {
            controlExpandedLayout(holder.expandedLayout)
        }
    }

    private fun controlExpandedLayout(expandedLayout: LinearLayout) {
        if (::currentExpanded.isInitialized) {
            if (currentExpanded == expandedLayout) {
                toggleLayout(currentExpanded)
            } else {
                currentExpanded.visibility = View.GONE
                expandedLayout.visibility = View.VISIBLE
            }
        } else {
            expandedLayout.visibility = View.VISIBLE
        }
        currentExpanded = expandedLayout
    }

    private fun toggleLayout(layout: LinearLayout) {
        layout.visibility =
            if (layout.visibility == View.VISIBLE) View.GONE
            else View.VISIBLE
    }

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val rootLayout: LinearLayout = view.findViewById(R.id.rootLayout)
        val expandedLayout: LinearLayout = view.findViewById(R.id.expandedLayout)
        val username: TextView = view.findViewById(R.id.username)
        val repositoryName: TextView = view.findViewById(R.id.repositoryName)
        val ivAvatar: ImageView = view.findViewById(R.id.ivAvatar)
        val description: TextView = view.findViewById(R.id.description)
        val forkCount: TextView = view.findViewById(R.id.forkCount)
        val starCount: TextView = view.findViewById(R.id.starCount)
        val language: TextView = view.findViewById(R.id.ivLanguageText)
        val languageColor: ImageView = view.findViewById(R.id.ivLanguageColor)
    }
}