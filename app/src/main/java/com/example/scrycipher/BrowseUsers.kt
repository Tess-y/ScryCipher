package com.example.scrycipher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class BrowseUsers : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.browse_users, container, false)
        val buttonParent = view.findViewById<LinearLayout>(R.id.browseUsers)
        val userList = requireArguments().getParcelableArrayList<User>(ARG_LIST)!!
        val currentUserProfile = requireArguments().getParcelable<User>(ARG_USER)
        for(user: User in userList) {
            if(user != currentUserProfile) {
                val button = Button(activity)
                button.text = user.name
                button.setOnClickListener {
                    val fragment = UserProfile()
                    val args = Bundle()
                    args.putParcelable(UserProfile.ARG_USER, user)
                    fragment.arguments = args
                    parentFragmentManager.beginTransaction().replace(R.id.content, fragment)
                        .commit()
                }
                buttonParent.addView(button);
            }
        }
        return view
    }

    companion object {
        const val ARG_LIST = "userlist"
        const val ARG_USER = "curentuser"
    }
}
