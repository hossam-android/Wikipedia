package org.hashtag.wikipedia.fragments


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import org.hashtag.wikipedia.R
import org.hashtag.wikipedia.WikiApplication
import org.hashtag.wikipedia.adapters.ArticleCardRecyclerAdapter
import org.hashtag.wikipedia.managers.WikiManager
import org.hashtag.wikipedia.models.WikiPage
import org.jetbrains.anko.doAsync



class FavoritesFragment : Fragment() {

    private var wikiManager: WikiManager? = null
    var favoritesRecyler: RecyclerView? = null
    private val adapter: ArticleCardRecyclerAdapter = ArticleCardRecyclerAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        wikiManager = (activity.applicationContext as WikiApplication).wikiManager
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_favorites, container, false)

        favoritesRecyler = view.findViewById<RecyclerView>(R.id.favorites_article_recycler);
        favoritesRecyler!!.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        favoritesRecyler!!.adapter = adapter

        return view
    }

    override fun onResume(){
        super.onResume()

        doAsync {
            val favoriteArticles = wikiManager!!.getFavorites()
            adapter.currentResults.clear()
            adapter.currentResults.addAll(favoriteArticles as ArrayList<WikiPage>)
            activity.runOnUiThread{ adapter.notifyDataSetChanged() }
        }
    }

}// Required empty public constructor
