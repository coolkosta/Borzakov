package ru.coolkosta.cinematest.presentation.mainactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ru.coolkosta.cinematest.R
import ru.coolkosta.cinematest.presentation.popularfragment.PopularFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, PopularFragment.newInstance()).commit()
        }

    }
}
