package com.example.scrycipher

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import java.net.URLEncoder

class Deckbuilder : Fragment() {
    var client = OkHttpClient()
    var baseUrl = "https://api.scryfall.com/cards/search?q=name:"
    var currentList = Deck("", "", ArrayList(), currentUser)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.deckbuilder, container, false)
        var editing = false;

        val searchBox = view.findViewById<EditText>(R.id.searchcard)
        searchBox.setOnKeyListener { v, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                var cardName = searchBox.text.toString()
                var url = baseUrl + URLEncoder.encode( cardName as String?, "UTF-8")
                this.cardSearch().execute(url)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }

        val button = view.findViewById<Button>(R.id.addbutton)
        button.setOnClickListener {
            var cardName = searchBox.text.toString()
            var url = baseUrl + URLEncoder.encode( cardName as String?, "UTF-8")
            this.cardSearch().execute(url)
        }
        if (arguments != null) {
            if(requireArguments().getParcelable<Deck>(Deckbuilder.ARG_DECK) != null){
                currentList = requireArguments().getParcelable<Deck>(Deckbuilder.ARG_DECK)!!
                view?.findViewById<EditText>(R.id.setdeckname)?.setText(currentList.name)
                view?.findViewById<EditText>(R.id.setformat)?.setText(currentList.format)
                editing = true

            }
            for(card: String in currentList.cards) {
                var listItem = TextView(context)
                listItem.text = card
                listItem.setOnClickListener {
                    for (card: String in currentList.cards) {
                        if (card == listItem.text) {
                            currentList.cards.remove(card)
                            view?.findViewById<LinearLayout>(R.id.cardlist)?.removeView(listItem)
                            break;
                        }
                    }
                }
                view?.findViewById<LinearLayout>(R.id.cardlist)?.addView(listItem)
            }
        }
        view.findViewById<FloatingActionButton>(R.id.newDeck).setOnClickListener{
            Thread {
                currentList.name = view?.findViewById<EditText>(R.id.setdeckname)?.text.toString()
                currentList.format = view?.findViewById<EditText>(R.id.setformat)?.text.toString()
                if (editing) {

                    DeckDatabase.getInstance(context as Context).deckDAO().update(currentList)
                } else {
                    DeckDatabase.getInstance(context as Context).deckDAO().insert(currentList)
                }

                val fragment = BrowseDecks()
                val args = Bundle()

                args.putParcelableArrayList(BrowseDecks.ARG_LISTS, ArrayList(DeckDatabase.getInstance(context as Context).deckDAO().getUserDecks(currentUser)))
                fragment.arguments = args
                parentFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }.start()
        }
        view.findViewById<FloatingActionButton>(R.id.deleteDeck).setOnClickListener{
            Thread{
                if(editing) {

                    DeckDatabase.getInstance(context as Context).deckDAO().delete(currentList)
                }

                val fragment = BrowseDecks()
                val args = Bundle()

                args.putParcelableArrayList(BrowseDecks.ARG_LISTS, ArrayList(DeckDatabase.getInstance(context as Context).deckDAO().getUserDecks(currentUser)))
                fragment.arguments = args
                parentFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }.start()
        }

        return view
    }

    @Throws(IOException::class)
    fun getCard(url: String): String {
        if(url == baseUrl){
            return "{\"object\": \"error\"}"
        }
        val request: Request = Request.Builder().url(url).build()
        client.newCall(request).execute().use { response ->
            return (response.body!!.string())
        }
    }
    inner class cardSearch() : AsyncTask<String, Void, String>() {

        override fun onPostExecute(response: String?) {
            var res = JSONObject(response)
            if(res.getString("object") == "error") {
                val toast = Toast.makeText(context, "No matching cards found.", Toast.LENGTH_LONG)
                toast.show()
            }
            else if(res.getString("object") == "list"){
            view?.findViewById<EditText>(R.id.searchcard)?.setText("")
                var cardName = JSONObject(JSONObject(response).getJSONArray("data")[0].toString()).getString("name")
                var listItem = TextView(context)
                listItem.setText(cardName)
                listItem.setOnClickListener{
                    for(card: String in currentList.cards){
                        if(card == listItem.text){
                            currentList.cards.remove(card)
                            view?.findViewById<LinearLayout>(R.id.cardlist)?.removeView(listItem)
                            break;
                        }
                    }
                }
                view?.findViewById<LinearLayout>(R.id.cardlist)?.addView(listItem)
                currentList.cards.add(cardName)
            }
        }
        override fun doInBackground(vararg params: String?): String? {
            var url = params[0].toString()
            return getCard(url)
        }
    }

    companion object{
        const val ARG_DECK = "deck"
    }

}