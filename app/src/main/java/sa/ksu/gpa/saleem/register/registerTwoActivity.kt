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
        val intent = Intent(this, registerThreeActivity::class.java)




        val btn=findViewById<View>(R.id.nxtTwoBtn) as Button?

        radio_group.setOnCheckedChangeListener(
            RadioGroup.OnCheckedChangeListener { group, checkedId ->
                val radio: RadioButton = findViewById(checkedId)

                user.put("gender", radio.text)
                getIntent().putExtra("gender", radio.text.toString())

                Toast.makeText(applicationContext," On checked change : ${radio.text}",
                    Toast.LENGTH_SHORT).show()
                getIntent().putExtra("gender", radio.text.toString())

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

            var name = getIntent().getStringExtra("name")
            var pass = getIntent().getStringExtra("password")
            var email= getIntent().getStringExtra("email")

            val wightTxt = findViewById<View>(R.id.wight) as EditText?
            val heightTxt = findViewById<View>(R.id.height) as EditText?


            var wight = wightTxt?.text.toString().toDouble()
            var height = heightTxt?.text.toString().toDouble()
            var bmi = (wight)/(height/100 * height/100)
            val type = calculateBmi(wight = wight, height = height)


            // Get the checked radio button id from radio group
            var id: Int = radio_group.checkedRadioButtonId
         //   if (id!=-1){ // If any radio button checked from radio group
                // Get the instance of radio button using id
                val radio:RadioButton = findViewById(id)
                var gender =radio?.text.toString()



       /*     }else{
                // If no radio button checked in this radio group
                Toast.makeText(applicationContext,"On button click : nothing selected",
                    Toast.LENGTH_SHORT).show()
            }
*/

             fun onDateSet(
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
                var userAge=age?.toString()

                getIntent().putExtra("age",userAge)

                Log.d("this2","age in 2nd activity"+userAge)
                updateDateInView()
            }






            intent.putExtra("wight",wight)
            intent.putExtra("height", height)
            intent.putExtra("type", type)
            intent.putExtra("name", name)
            intent.putExtra("BMI", bmi)

            intent.putExtra("password", pass)
            intent.putExtra("email",email)

            intent.putExtra("gender",gender)
            Log.d("this2",""+gender)

            Log.d("this2",""+email)
            Log.d("this2",""+name)
            Log.d("this2",""+pass)
            Log.d("this2",""+height)
            Log.d("this2",""+wight)
            Log.d("this2",""+type)
            Log.d("this2",""+bmi)

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
               val userAge=age?.toString()

                getIntent().putExtra("age",userAge)
                intent.putExtra("age",userAge)


                Log.d("this2","age in 2nd activity"+userAge)
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
                        //user.put("gender", "male")
                        intent.putExtra("gender", "male")
                    }
                R.id.rb_female ->
                    if (checked) {
                        // Ninjas rule
                        /* Toast.makeText(applicationContext,"On button click : female selected",
                            Toast.LENGTH_SHORT).show()*/
                       // user.put("gender", "male")
                        intent.putExtra("gender", "Female")

                    }
            }
        }
    }


    fun calculateBmi(wight: Double, height: Double): Int {

        var bmi = (wight)/(height/100*height/100)
        intent.putExtra("BMI",bmi)


        // return
        when {
            18.5 > bmi -> {
                level= 1
                showDialogWithOkButton("نحافة")
                intent.putExtra("type", level)


            }
            18.5 <= bmi || bmi < 25.0 -> {
                showDialogWithOkButton("طبيعي")
                level= 2
                intent.putExtra("type", level)
            }
            25.0 <= bmi || bmi < 30.0 -> {
                showDialogWithOkButton("زيادة وزن")
                level= 3
                intent.putExtra("type", level)
            }

            30.0 <= bmi || bmi < 35.0 -> {
                showDialogWithOkButton("سمنة درجة اولى")
                level= 4
                intent.putExtra("type", level)
            }
            35.0 <= bmi || bmi < 40.0 -> {
                showDialogWithOkButton("سمنى درجة ثانية")
                level= 5
                intent.putExtra("type", level)

            }
            bmi >= 40.0 -> {
                showDialogWithOkButton("سمنة مفرطة")
               level= 6
                intent.putExtra("type", level)
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





