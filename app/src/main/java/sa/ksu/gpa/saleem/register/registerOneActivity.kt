package sa.ksu.gpa.saleem.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import sa.ksu.gpa.saleem.R

class registerOneActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register_one)
        val db = FirebaseFirestore.getInstance()

        val nameTxt = findViewById<View>(R.id.nameET) as TextView?
        val emailTxt = findViewById<View>(R.id.emailET) as TextView?


        val btn = findViewById<View>(R.id.nxtOneBtn) as Button?


        btn?.setOnClickListener {
            Toast.makeText(this@registerOneActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerTwoActivity::class.java)

            val TAG = "MyActivity"

            var nameEd = nameTxt?.text.toString()
            var emailEd = emailTxt?.text.toString()



            val user = HashMap<String, Any>()
            user.put("name", nameEd)
            user.put("email", emailEd)


            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            startActivity(intent)
        }


    }
}

