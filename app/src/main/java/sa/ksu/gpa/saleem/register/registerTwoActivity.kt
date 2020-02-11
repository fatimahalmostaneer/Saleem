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
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

import android.R.id
import android.widget.RadioGroup
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.wajahatkarim3.easyvalidation.core.collection_ktx.noNumbersList


class registerTwoActivity : AppCompatActivity() {


    var button_date: Button? = null
    var textview_date: TextView? = null
    var cal = Calendar.getInstance()
    val TAG = "MyActivity"
    val user = HashMap<String, Any>()
    var level = 0




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_two)
        var RadioG =findViewById<View>(R.id.radio_group) as RadioGroup
        val db = FirebaseFirestore.getInstance()



        val btn=findViewById<View>(R.id.nxtTwoBtn) as Button?

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)

                user.put("gender", radio.text)
                intent.putExtra("gender", radio.text)

                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
            })



        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }


        btn?.setOnClickListener {
            Toast.makeText(this@registerTwoActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerThreeActivity::class.java)

            // Get the checked radio button id from radio group
            var id: Int = radio_group.checkedRadioButtonId
            if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                var gender =radio.text
                intent.putExtra("gender",gender)


            }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"On button click : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }

            val wightTxt = findViewById<View>(R.id.wight) as EditText?
            val heightTxt = findViewById<View>(R.id.height) as EditText?


            var wight = wightTxt?.text.toString().toDouble()
            var height = heightTxt?.text.toString().toDouble()
            var bmi = wight / height * height
            val type = calculateBmi(wight = wight, height = height)

           /* user.put("level", level)
            user.put("wight", wight)
            user.put("height", height)*/


            intent.putExtra("wight", wight)
            intent.putExtra("height", height)
            intent.putExtra("BMI", bmi)
            intent.putExtra("type", type)
          //  intent.putExtra("age", age)



            startActivity(intent)
        }//------------------------------------------




        // get the references from layout file
        textview_date = this.text_view_date_1
        button_date = this.button_date_1

        textview_date!!.text = "--/--/----"

        // create an OnDateSetListener
        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
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
               // user.put("DOB", age)
                intent.putExtra("age", age)
                updateDateInView()
            }
        }

        // when you click on the button, show DatePickerDialog that is set with OnDateSetListener
        button_date!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(
                    this@registerTwoActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()


            }

        })
    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textview_date!!.text = sdf.format(cal.getTime())
    }


    fun message(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show()
    }
    // Get the selected radio button text using radio button on click listener
    fun radio_button_click(view: View){
        // Get the clicked radio button instance
        val radio: RadioButton = findViewById(radio_group.checkedRadioButtonId)
        Toast.makeText(applicationContext,"On click : ${radio.text}",
            Toast.LENGTH_SHORT).show()
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
            when (view.getId()) {
                R.id.rb_male ->
                    if (checked) {
                        // Pirates are the best
                        /*           Toast.makeText(applicationContext,"On button click : male selected",
                            Toast.LENGTH_SHORT).show()*/
                        user.put("gender", "male")
                        intent.putExtra("gender", "male")
                    }
                R.id.rb_female ->
                    if (checked) {
                        // Ninjas rule
                        /* Toast.makeText(applicationContext,"On button click : female selected",
                            Toast.LENGTH_SHORT).show()*/
                        user.put("gender", "male")
                        intent.putExtra("gender", "Female")

                    }
            }
        }
    }


    fun calculateBmi(wight: Double, height: Double): Int {

        var bmi = wight / Math.pow(height,2.0)

        // return
        when {
            18.5 > bmi -> {
                level= 1
                showDialogWithOkButton("نحافة")
            }
            18.5 <= bmi || bmi < 25.0 -> {
                showDialogWithOkButton("طبيعي")
                level= 2
            }
            25.0 <= bmi || bmi < 30.0 -> {
                showDialogWithOkButton("زيادة وزن")
                level= 3
            }

            30.0 <= bmi || bmi < 35.0 -> {
                showDialogWithOkButton("سمنة درجة اولى")
                level= 4
            }
            35.0 <= bmi || bmi < 40.0 -> {
                showDialogWithOkButton("سمنى درجة ثانية")
                level= 5

            }
            bmi >= 40.0 -> {
                showDialogWithOkButton("سمنة مفرطة")
               level= 6
            }

            else -> print("")
        }
return level
    }


    private fun showDialogWithOkButton(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(msg)
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, id ->
                //do things
            }
        val alert = builder.create()
        alert.show()
    }




}





