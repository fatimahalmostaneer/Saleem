package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import sa.ksu.gpa.saleem.MainActivity
import sa.ksu.gpa.saleem.R

class registerFourActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_four)

        val btn=findViewById<View>(R.id.startBtn) as Button?


        btn?.setOnClickListener {
            Toast.makeText(this@registerFourActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }
    }
}
