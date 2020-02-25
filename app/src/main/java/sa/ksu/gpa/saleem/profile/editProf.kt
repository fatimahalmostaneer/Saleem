package sa.ksu.gpa.saleem.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_register_two.*
import sa.ksu.gpa.saleem.R

class editProf : AppCompatActivity() {

    lateinit var userUid: String
    var storage = FirebaseStorage.getInstance()
    var firebaseFirestore = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_prof)


        var edname: EditText?
        var edwight: EditText?
        var edheight: EditText?

        var emailText: TextView? = findViewById(R.id.emailSignUpHin)
        var saveEdit: Button?


        userUid = FirebaseAuth.getInstance().currentUser!!.uid

        val toolbar = findViewById<View>(R.id.toolbar)
        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar!!.setTitle("")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(false)

        edname = findViewById(R.id.nameSignUpHin)
        edwight=findViewById(R.id.wightHin)
        edheight=findViewById(R.id.heightHin)


        saveEdit = findViewById(R.id.edit_profile) as Button
        var auth = FirebaseAuth.getInstance()
        var storageRef = storage.reference

        saveEdit!!.setOnClickListener(View.OnClickListener {
            // save changes
            val n = edname!!.getText().toString()
            val w = edwight!!.getText().toString()
            val h = edheight!!.getText().toString()
            editName(n)
            editWight(w)
            editHight(h)

            startActivity(Intent(this,Profile::class.java))
        })

        retriveUserData()
    }

    private fun editHight(h: String) {
        val washingtonRef = firebaseFirestore.collection("Users").document(userUid)
        washingtonRef
            .update("height", h + "")
            .addOnSuccessListener { Log.d("TAG", "تم تعديل الطول") }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
    }

    private fun editWight(w: String) {
        val washingtonRef = firebaseFirestore.collection("Users").document(userUid)
        washingtonRef
            .update("weight", w + "")
            .addOnSuccessListener { Log.d("TAG", "تم تعديل الوزن") }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
    }

    private fun editName(n: String) {
        val washingtonRef = firebaseFirestore.collection("Users").document(userUid)
        washingtonRef
            .update("name", n + "")
            .addOnSuccessListener { Log.d("TAG", "تم تعديل الاسم") }
            .addOnFailureListener { e -> Log.w("TAG", "Error updating document", e) }
    }

    fun retriveUserData() {
        var edname: EditText? = findViewById(R.id.nameSignUpHin)
        var emailText: TextView? = findViewById(R.id.emailSignUpHin)
        var edwight: EditText? = findViewById(R.id.wightHin)
        var edheight: EditText? = findViewById(R.id.heightHin)


        val docRef = firebaseFirestore.collection("Users").document(userUid)
        docRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val document = task.result
                if (document!!.exists()) {

                    val userName = document.get("name")!!.toString()
                    val email = document.get("email")!!.toString()

                    val weight = document.get("weight")!!.toString()
                    val height = document.get("height")!!.toString()


                    if (userName != null && email != null) {
                        edname?.setText(userName)
                        emailText?.setText(email)
                        edwight?.setText(weight)
                        edheight?.setText(height)



                    }

                }
            }
        }



    }

    override fun onBackPressed() {
        super.onBackPressed()

    }


}
