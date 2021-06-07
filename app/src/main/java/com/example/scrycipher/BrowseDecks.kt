package com.example.scrycipher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BrowseDecks : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.browse_decks, container, false)
        view.findViewById<FloatingActionButton>(R.id.newDeck).setOnClickListener{
            parentFragmentManager.beginTransaction().replace(R.id.content, Deckbuilder()).commit()
        }
        val buttonParent = view.findViewById<LinearLayout>(R.id.browseDecks)
        val newButton = view.findViewById<FloatingActionButton>(R.id.newDeck)
        if(requireArguments().getBoolean(ARG_SHOW_NEW)){
            newButton.visibility = View.VISIBLE
        }
        for(deck: Deck in requireArguments().getParcelableArrayList<Deck>(ARG_LISTS)!!) {
            val button = Button(activity)
            button.text = deck.name
            button.setOnClickListener{
                val fragment = Deckviewer()
                val args = Bundle()
                args.putParcelable(Deckviewer.ARG_DECK, deck)

                if(requireArguments().getBoolean(ARG_SHOW_NEW)){
                    args.putBoolean(Deckviewer.ARG_SHOW_NEW, true)
                }
                fragment.arguments = args
                parentFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }
            buttonParent.addView(button);
        }
        return view
    }


    companion object {
        const val ARG_LISTS = "decklists"
        const val ARG_SHOW_NEW = "show"
    }
}