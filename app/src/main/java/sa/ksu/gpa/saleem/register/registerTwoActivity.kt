package sa.ksu.gpa.saleem.register

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_two.*
import sa.ksu.gpa.saleem.R
import java.text.SimpleDateFormat
import java.util.*

class registerTwoActivity : AppCompatActivity() {
/*
    val heightTxt = findViewById<View>(R.id.height) as EditText
    val wightTxt = findViewById<View>(R.id.wight) as EditText

    val db = FirebaseFirestore.getInstance()
    val TAG = "MyActivity"*/

    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_two)

        val btn=findViewById<View>(R.id.nxtTwoBtn) as Button?


        btn?.setOnClickListener {
            Toast.makeText(this@registerTwoActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerThreeActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }

 /*       var heightEd = heightTxt.text.toString()
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
            }*/
        // get the references from layout file
        textview_date = this.text_view_date_1
        button_date = this.button_date_1

        textview_date!!.text = "--/--/----"

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@registerTwoActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }

        })
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }

}

