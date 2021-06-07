package com.example.scrycipher

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.lang.Exception
import java.net.URLEncoder

class Deckviewer : Fragment() {
    var client = OkHttpClient()
    var baseUrl = "https://api.scryfall.com/cards/search?q=name:"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.deckviewer, container, false)
        try {
            val editButton = view.findViewById<FloatingActionButton>(R.id.editDeck)
            if(requireArguments().getBoolean(ARG_SHOW_NEW)){
                editButton.visibility = View.VISIBLE
                editButton.setOnClickListener {
                    val fragment = Deckbuilder()
                    val args = Bundle()
                    args.putParcelable(
                        Deckbuilder.ARG_DECK,
                        requireArguments().getParcelable<Deck>(ARG_DECK)!!
                    )

                    fragment.arguments = args
                    parentFragmentManager.beginTransaction().replace(R.id.content, fragment)
                        .commit()
                }
            }
            view.findViewById<TextView>(R.id.deckname).text = requireArguments().getParcelable<Deck>(ARG_DECK)!!.name
            view.findViewById<TextView>(R.id.format).text = requireArguments().getParcelable<Deck>(ARG_DECK)!!.format
            val cardList = view.findViewById<LinearLayout>(R.id.cardlist)
            for (card: String in requireArguments().getParcelable<Deck>(ARG_DECK)!!.cards) {
                val entry = TextView(activity)
                entry.text = card
                entry.textSize = 15F
                entry.setOnClickListener {
                    var url = baseUrl + URLEncoder.encode( entry.text as String?, "UTF-8")
                    this.cardSearch().execute(url)
                }
                cardList.addView(entry)
            }
        } catch (e: Exception) {
        }
        return view
    }
    @Throws(IOException::class)
    fun getCard(url: String): String {
        println(url)
        val request: Request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return (response.body!!.string())
        }
    }
    companion object {
        const val ARG_DECK = "deck"
        const val ARG_SHOW_NEW = "show"
    }
    inner class cardSearch() : AsyncTask<String, Void, String>() {

        override fun onPostExecute(response: String?) {
            val displayView = view?.findViewById<FrameLayout>(R.id.zoomcard)
            val display = ImageView(activity)
            display.minimumHeight = 2000
            display.setOnClickListener{
                displayView?.removeAllViews()
            }
            displayView?.addView(display)
            var card = JSONObject(JSONObject(response).getJSONArray("data")[0].toString())
            var images = card.getString("image_uris")
            var med = JSONObject(images).getString("normal")
            Glide.with(activity).load(med).into(display);
        }
        override fun doInBackground(vararg params: String?): String? {
            var url = params[0].toString()
            return getCard(url)
        }
    }
}