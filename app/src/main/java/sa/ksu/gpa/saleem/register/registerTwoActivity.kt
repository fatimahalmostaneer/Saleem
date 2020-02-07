package sa.ksu.gpa.saleem.register

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SwitchCompat
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_two.*
import sa.ksu.gpa.saleem.R
import java.text.SimpleDateFormat
import java.util.*

class registerTwoActivity : AppCompatActivity() {


    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    val TAG = "MyActivity"
    val user = HashMap<String, Any>()





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_two)

        val db = FirebaseFirestore.getInstance()

        val wightTxt = findViewById<View>(R.id.wight) as TextView?
        val heightTxt = findViewById<View>(R.id.height) as TextView?

        val genderf= findViewById<View>(R.id.rb_female) as RadioButton
        val genderm= findViewById<View>(R.id.rb_male)  as RadioButton

        val btn=findViewById<View>(R.id.nxtTwoBtn) as Button?


        btn?.setOnClickListener {
            Toast.makeText(this@registerTwoActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerThreeActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity

            var wight = wightTxt?.text.toString()
            var hight = heightTxt?.text.toString()



        /*    var checkedF = genderf.text.toString() as RadioButton
            var checkedM = genderf.text.toString() as RadioButton

*/


            //val user = HashMap<String, Any>()
            user.put("wight", wight)
            user.put("height", hight)


            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }

            // Set an checked change listener for switch button

            fun onRadioButtonClicked(view: View) {
                var checked = view as RadioButton
                if (rb_male == checked) {
                    message(rb_male.text.toString() + if (rb_male.isChecked) " Checked " else " UnChecked ")
                    // val user = HashMap<String, Any>()
                    user.put("gender",rb_male)
                }
                if (rb_female == checked) {
                    message(rb_female.text.toString() + if (rb_female.isChecked) " Checked " else " UnChecked ")
                    // val user = HashMap<String, Any>()
                    user.put("gender",rb_female)
                }
            }

            startActivity(intent)
        }



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

                val dob = Calendar.getInstance()
                val today = Calendar.getInstance()

                dob.set(year, monthOfYear, dayOfMonth)

                var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)

                if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)) {
                    age--
                }

                val ageInt = age + 1

               // val user = HashMap<String, Any>()
                user.put("DOB", age)



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

    // multiple Radio click method


    fun message(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }


}



