package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import sa.ksu.gpa.saleem.R

class registerThreeActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_three)

        val btn=findViewById<View>(R.id.nxtThreeBtn) as Button?



        btn?.setOnClickListener {
            Toast.makeText(this@registerThreeActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerFourActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }
    }
}
