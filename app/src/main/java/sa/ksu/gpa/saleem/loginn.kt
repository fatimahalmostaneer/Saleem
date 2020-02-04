package sa.ksu.gpa.saleem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import sa.ksu.gpa.saleem.register.registerOneActivity

class loginn : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_loginn)

        var Button = findViewById<View>(R.id.loginBtn) as Button?
        var signUp=findViewById<View>(R.id.signUpBtn)as TextView?

        Button?.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
        signUp?.setOnClickListener {
            val intent = Intent(applicationContext, registerOneActivity::class.java)
            startActivity(intent)
        }

   /*     override fun onClick(v: View) {
            when (v.id) {
                R.id.loginBtn -> {
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                }
                else -> {
                    // else condition
                }
            }
        }*/
    }
}
