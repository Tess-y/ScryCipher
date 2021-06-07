package com.example.scrycipher

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.ArrayList

var currentUser = "Madigan Mason"
class MainActivity : AppCompatActivity() {


    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.browse -> {
                    Thread {
                        val fragment = BrowseUsers()
                        val args = Bundle()

                        args.putParcelableArrayList(BrowseUsers.ARG_LIST, ArrayList(UserDatabase.getInstance(applicationContext).userDAO().getAllUsers()))
                        args.putParcelable(BrowseUsers.ARG_USER,UserDatabase.getInstance(applicationContext).userDAO().getUser(currentUser))
                        fragment.arguments = args
                        supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
                    }.start()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.userProfile -> {
                    //replace/add user profile fragment here
                    loadHome()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.action_decks -> {
                    Thread {
                        val fragment = BrowseDecks()
                        val args = Bundle()

                        args.putParcelableArrayList(BrowseDecks.ARG_LISTS, ArrayList(DeckDatabase.getInstance(applicationContext).deckDAO().getUserDecks(currentUser)))
                        args.putBoolean(BrowseDecks.ARG_SHOW_NEW, true)
                        fragment.arguments = args
                        supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
                    }.start()
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        Thread{populateData()}.start()
        if(supportFragmentManager.findFragmentById(R.id.content) == null){
            loadHome()
        }
    }

    fun loadHome() {
        Thread {
            val fragment = UserProfile()
            val args = Bundle()
            args.putParcelable(UserProfile.ARG_USER, UserDatabase.getInstance(applicationContext).userDAO().getUser(currentUser))
            fragment.arguments = args
            supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
        }.start()
    }

    fun populateData(){
        UserDatabase.getInstance(applicationContext)
        DeckDatabase.getInstance(applicationContext)
    }

    fun getContext(): Context{
        return applicationContext
    }
}