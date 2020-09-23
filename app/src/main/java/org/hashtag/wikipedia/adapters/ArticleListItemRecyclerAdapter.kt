package org.hashtag.wikipedia.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import org.hashtag.wikipedia.R
import org.hashtag.wikipedia.holders.ListItemHolder
import org.hashtag.wikipedia.models.WikiPage


class ArticleListItemRecyclerAdapter() : RecyclerView.Adapter<ListItemHolder>() {
    val currentResults : ArrayList<WikiPage> = ArrayList<WikiPage>()

    override fun getItemCount(): Int {
        return currentResults.size // temporary
    }

    override fun onBindViewHolder(holder: ListItemHolder?, position: Int) {
        // this is where we will update our view
        var page = currentResults[position]
        holder?.updateWithPage(page)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemHolder {
        var cardItem = LayoutInflater.from(parent?.context).inflate(R.layout.article_list_item, parent, false)
        return ListItemHolder(cardItem)
    }
}