package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import sa.ksu.gpa.saleem.R

class registerOneActivity : AppCompatActivity() {

   /* val emailTxt = findViewById<View>(R.id.emailET) as EditText
    val nameTxt = findViewById<View>(R.id.nameET) as EditText*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_one)
        val btn=findViewById<View>(R.id.nxtOneBtn) as Button?



       // emailTxt.callOnClick()
        btn?.setOnClickListener {
            Toast.makeText(this@registerOneActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerTwoActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }



/*
        val db = FirebaseFirestore.getInstance()
        val TAG = "MyActivity"

        var emailEd = emailTxt.text.toString()
        var nameEd = nameTxt.text.toString()

        val user = hashMapOf(
            "email" to "emailEd",
            "name" to "nameEd"
        )

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }*/

    /*    if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener
            { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    startActivity(Intent(this, Timeline::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
            })
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }*/
    }
    }

