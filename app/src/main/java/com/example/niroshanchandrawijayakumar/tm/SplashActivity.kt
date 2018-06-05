package com.example.niroshanchandrawijayakumar.tm

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_sp)



    }

    override fun onResume() {
        super.onResume()

        Observable.fromCallable { Thread.sleep(1000) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally { openHomePage() }
                .subscribe()


    }

    fun openHomePage(){
        val i = Intent(this,MainActivity::class.java)
        startActivity(Intent(this , MainActivity::class.java))
        finish()
    }
}
