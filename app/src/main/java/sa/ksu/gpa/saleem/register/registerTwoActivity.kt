package sa.ksu.gpa.saleem.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import sa.ksu.gpa.saleem.R

class registerTwoActivity : AppCompatActivity() {

    val heightTxt = findViewById<View>(R.id.height) as EditText
    val wightTxt = findViewById<View>(R.id.wight) as EditText

    val db = FirebaseFirestore.getInstance()
    val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_two)

        var heightEd = heightTxt.text.toString()
        var wightEd = wightTxt.text.toString()

        val userHw = hashMapOf(
            "height" to "heightEd",
            "wight" to "wightEd"
        )

        db.collection("users")
            .add(userHw)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}
