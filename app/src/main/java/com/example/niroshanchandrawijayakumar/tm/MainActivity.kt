package com.example.niroshanchandrawijayakumar.tm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    lateinit var viewModel:MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.listenError().observe(this, Observer { message -> showMessage(message) })

        showFragment(ItemFragment.newInstance(1))

    }


    fun showFragment(fragmentToShow: Fragment, addToBackStack: Boolean =false, tag: String="TAG"){
        supportFragmentManager.beginTransaction()
                .replace(R.id.root, fragmentToShow,tag)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .let { if (addToBackStack) {it.addToBackStack(tag)}; return@let it}
                .commit()

    }

    private fun showMessage(message: String?, duration: Int =Toast.LENGTH_LONG) {
        if(!message.isNullOrBlank()){
            Toast.makeText(this,message,duration).show()
            viewModel.resetError()
        }
    }



}
