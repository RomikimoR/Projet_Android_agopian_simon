package com.example.projet_android_agopian_simon.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.projet_android_agopian_simon.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RocketFragment: Fragment() {

    private val disposable = CompositeDisposable();


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

            return inflater.inflate(R.layout.rocket_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        disposable.add(
            ApiService.getNext5Launch()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    Log.i("", "");
                }
                .subscribe({
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                    println(recyclerView)
                    val linearLayoutManager = LinearLayoutManager(this.context)
                    recyclerView.layoutManager = linearLayoutManager
                    val customAdapter = CustomAdapter(it.launches)
                    recyclerView.adapter = customAdapter
                }, Throwable::printStackTrace))



    }



}