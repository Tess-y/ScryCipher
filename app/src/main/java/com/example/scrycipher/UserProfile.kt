package com.example.scrycipher

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment


class UserProfile : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.user_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var user = requireArguments().getParcelable<User>(ARG_USER)
        if (user != null) {
            view.findViewById<TextView>(R.id.name).text = user.name
            view.findViewById<TextView>(R.id.mana_cost).text = user.mana?.let { getMana(it) }
            user.mana?.let { setColours(it) }
            view.findViewById<TextView>(R.id.type).text = user.type
            view.findViewById<TextView>(R.id.text).text = user.text
        }
        view.findViewById<Button>(R.id.loadDecklists).setOnClickListener{
            Thread {
                val fragment = BrowseDecks()
                val args = Bundle()

                if (user?.name != null) {
                    args.putParcelableArrayList(
                        BrowseDecks.ARG_LISTS, ArrayList(
                            DeckDatabase.getInstance(
                                context as Context
                            ).deckDAO().getUserDecks(user.name!!)
                        )
                    )
                }
                fragment.arguments = args
                parentFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
            }.start()
        }
    }

    companion object {
        const val ARG_USER = "user"
    }


    fun getMana(cost: String): String {
        var mana = ""
        for(symbol: Char in cost){
            when(symbol){
                'W' -> {
                    mana += getString(R.string.ic_white)
                }
                'U' -> {
                    mana += getString(R.string.ic_blue)
                }
                'B' -> {
                    mana += getString(R.string.ic_black)
                }
                'R' -> {
                    mana += getString(R.string.ic_red)
                }
                'G' -> {
                    mana += getString(R.string.ic_green)
                }
            }
        }
        return mana
    }

    fun setColours(cost: String) {
        val view = view?.findViewById<LinearLayout>(R.id.profileBackground)
        val title = view?.findViewById<LinearLayout>(R.id.topBar)
        val type = view?.findViewById<TextView>(R.id.type)
        val text = view?.findViewById<TextView>(R.id.text)
        if (view != null && title != null && type != null && text != null) {
            if (cost.length > 1) {
                view.setBackgroundColor(resources.getColor(R.color.multicoloured_dark))
                if(cost.length > 2){
                    title.setBackgroundColor(resources.getColor(R.color.multicoloured_dark))
                    type.setBackgroundColor(resources.getColor(R.color.multicoloured_dark))
                    text.setBackgroundColor(resources.getColor(R.color.multicoloured_light))
                }
                else{
                    title.setBackgroundColor(resources.getColor(R.color.multicoloured_dark))
                    type.setBackgroundColor(resources.getColor(R.color.multicoloured_dark))
                    var colour1 = (resources.getColor(R.color.multicoloured_light))
                    var colour2 = (resources.getColor(R.color.multicoloured_light))
                    when(cost[0]){
                        'W' -> {
                            colour1 = resources.getColor(R.color.white_light)
                        }
                        'U' -> {
                            colour1 = resources.getColor(R.color.blue_light)
                        }
                        'B' -> {
                            colour1 = resources.getColor(R.color.black_light)
                        }
                        'R' -> {
                            colour1 = resources.getColor(R.color.red_light)
                        }
                        'G' -> {
                            colour1 = resources.getColor(R.color.green_light)
                        }
                    }
                    when(cost[1]){
                        'W' -> {
                            colour2 = resources.getColor(R.color.white_light)
                        }
                        'U' -> {
                            colour2 = resources.getColor(R.color.blue_light)
                        }
                        'B' -> {
                            colour2 = resources.getColor(R.color.black_light)
                        }
                        'R' -> {
                            colour2 = resources.getColor(R.color.red_light)
                        }
                        'G' -> {
                            colour2 = resources.getColor(R.color.green_light)
                        }
                    }
                val drawable = GradientDrawable(
                        GradientDrawable.Orientation.LEFT_RIGHT, intArrayOf(
                            colour1, colour2)
                        )
                    text.background = drawable

                }
            } else if (cost.isEmpty()) {
                view.setBackgroundColor(resources.getColor(R.color.colourless_dark))
                title.setBackgroundColor(resources.getColor(R.color.colourless_light))
                type.setBackgroundColor(resources.getColor(R.color.colourless_light))
                text.setBackgroundColor(resources.getColor(R.color.colourless_light))

            } else {
                when (cost) {
                    "W" -> {
                        view.setBackgroundColor(resources.getColor(R.color.white_dark))
                        title.setBackgroundColor(resources.getColor(R.color.white_light))
                        type.setBackgroundColor(resources.getColor(R.color.white_light))
                        text.setBackgroundColor(resources.getColor(R.color.white_light))
                    }
                    "U" -> {
                        view.setBackgroundColor(resources.getColor(R.color.blue_dark))
                        title.setBackgroundColor(resources.getColor(R.color.blue_light))
                        type.setBackgroundColor(resources.getColor(R.color.blue_light))
                        text.setBackgroundColor(resources.getColor(R.color.blue_light))
                    }
                    "B" -> {
                        view.setBackgroundColor(resources.getColor(R.color.black_dark))
                        title.setBackgroundColor(resources.getColor(R.color.black_light))
                        type.setBackgroundColor(resources.getColor(R.color.black_light))
                        text.setBackgroundColor(resources.getColor(R.color.black_light))
                    }
                    "R" -> {
                        view.setBackgroundColor(resources.getColor(R.color.red_dark))
                        title.setBackgroundColor(resources.getColor(R.color.red_light))
                        type.setBackgroundColor(resources.getColor(R.color.red_light))
                        text.setBackgroundColor(resources.getColor(R.color.red_light))
                    }
                    "G" -> {
                        view.setBackgroundColor(resources.getColor(R.color.green_dark))
                        title.setBackgroundColor(resources.getColor(R.color.green_light))
                        type.setBackgroundColor(resources.getColor(R.color.green_light))
                        text.setBackgroundColor(resources.getColor(R.color.green_light))
                    }
                }

            }
        }
    }
}