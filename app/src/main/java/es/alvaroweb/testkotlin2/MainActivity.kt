package es.alvaroweb.testkotlin2

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val  mContext: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        message.setOnClickListener { view -> doToast(view) }
        go.setOnClickListener { view -> openOtherActivity() }
    }


    private fun openOtherActivity() {
        startActivity(Intent(mContext, OtherActivity::class.java))
    }

    private fun doToast(view: View) {
        Toast.makeText(mContext, "Hello from Kotlin: ${(view as TextView).text}", Toast.LENGTH_SHORT).show()
    }
}
