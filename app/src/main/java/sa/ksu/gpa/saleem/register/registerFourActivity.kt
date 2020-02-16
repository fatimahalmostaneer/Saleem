package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.internal.Constants
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_one.*
import kotlinx.android.synthetic.main.activity_register_two.*
import sa.ksu.gpa.saleem.MainActivity
import sa.ksu.gpa.saleem.R
import sa.ksu.gpa.saleem.ScanActivity
import sa.ksu.gpa.saleem.loginn
import java.util.*

class registerFourActivity : AppCompatActivity() {

    val user = HashMap<String, Any>()

    private val TAG = "Sign up"



    private lateinit var auth: FirebaseAuth

    val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_four)


        val intent = Intent(this, loginn::class.java)

        auth = FirebaseAuth.getInstance()

        db.collection("users")

        val btn=findViewById<View>(R.id.startBtn) as Button?
        val one=findViewById<View>(R.id.goalOneBtn) as Button?
        val two=findViewById<View>(R.id.goalTwoBtn) as Button?
        val three=findViewById<View>(R.id.goalThreeBtn) as Button?

        var goal=0




        var button_background : Int = 1;

        var clicked=false;

        one?.setOnClickListener {



            if(button_background==2){
                one.setBackgroundResource(R.drawable.unclick);
                button_background=1;
            } else if(button_background==1){
                one.setBackgroundResource(R.drawable.rounded_buttons);
                two?.setBackgroundResource(R.drawable.unclick)
                three?.setBackgroundResource(R.drawable.unclick)
                button_background=2;
            }
         //   user.put("level","beginner")
            goal = 1
           clicked=true;
        }
        two?.setOnClickListener{
            if(button_background==2){
                two?.setBackgroundResource(R.drawable.unclick);
                button_background=1;
            } else if(button_background==1){
                two?.setBackgroundResource(R.drawable.rounded_buttons);
                one?.setBackgroundResource(R.drawable.unclick)
                three?.setBackgroundResource(R.drawable.unclick)
                button_background=2;
                two.invalidate()
            }

           // user.put("level","Intermediate")
            goal = 2
          clicked=true;
        }

        three?.setOnClickListener{
            if(button_background==2){
                three?.setBackgroundResource(R.drawable.unclick);
                button_background=1;
            } else if(button_background==1){
                three?.setBackgroundResource(R.drawable.rounded_buttons);
                two?.setBackgroundResource(R.drawable.unclick)
                one?.setBackgroundResource(R.drawable.unclick)
                button_background=2;
            }

           // user.put("level","advance")
            goal = 3
          clicked=true;
        }




        var length = getIntent().getDoubleExtra("height",0.0)
        var weight=getIntent().getDoubleExtra("wight",0.0)
        var gender = getIntent().getStringExtra("gender")
        var bmi = getIntent().getDoubleExtra("bmi",0.0)
        var level = getIntent().getIntExtra("level",0)
        var userAge= getIntent().getStringExtra("uaserAge")


        var name = getIntent().getStringExtra("name")
        var pass = getIntent().getStringExtra("password")
        var email = getIntent().getStringExtra("email")

        var agee= getIntent().getIntExtra("agee",0)
        //intent.putExtra("agee",agee)

        Log.d("this",""+email)
        Log.d("this",""+name)
        Log.d("this",""+pass)
        Log.d("this",""+length)
        Log.d("this",""+weight)
        Log.d("this",""+gender)
        Log.d("this",""+level)
        Log.d("this",""+userAge)
        Log.d("this",""+bmi)
        Log.d("this",""+goal)



        btn?.setOnClickListener {

            if (verify(clicked)) {

            Toast.makeText(this@registerFourActivity, "Click...", Toast.LENGTH_LONG).show()
            //  val intent = Intent(this, MainActivity::class.java)
            var neededCal = 0.0


            if (gender == "male")
                neededCal = calcualteCaloriesMen(3.0, weight!!, length!!, goal!!)



            if (gender == "female")
                neededCal = calcualteCaloriesWomen(3.0, weight!!, length!!, goal!!)


            createAccount(email, pass)

            createUserCollection(weight, length, level, goal, gender, name, email, neededCal, agee)

                startActivity(Intent(this, loginn::class.java))
            }
        }
//


    }

    private fun verify(clicked:Boolean): Boolean {


        if(!clicked){
            showDialogWithOkButton("الرجاء اختيار الهدف")
            return false
        }
else return true

    }

    fun calcualteCaloriesWomen(activityLevel:Double,weight:Double,length:Double,goal:Int):Double{

        var neededCalories:Double=0.0
        var Mifflin =((10*weight)+ (6.25*length)-(5*activityLevel )-161)
        var Revised =((9.247*weight) +(3.098*length) - (4.330*activityLevel) + 447.593)

        var Calories= (Mifflin+Revised)/2

        when(goal){
            1 -> neededCalories= Calories-500
            2->  neededCalories= Calories+500
            3 -> neededCalories= Calories
        }

        //add user's needed calories to her collection

        db.collection("Users").document("user")
        //...neededCalories

        showDialogWithOkButton("needed calories"+neededCalories)
        return neededCalories


    }
    fun calcualteCaloriesMen(activityLevel:Double,weight:Double,length:Double,goal:Int):Double{
        var neededCalories:Double=0.0
        var Mifflin =((10*weight)+ (6.25*length)-(5*activityLevel )+5)
        var Revised =((13.397*weight) +(4.799*length) - (5.677*activityLevel) + 88.362)

        var Calories= (Mifflin+Revised)/2

        when(goal){
            1 -> neededCalories= Calories-500
            2->  neededCalories= Calories+500
            3 -> neededCalories= Calories
        }

        //add user's needed calories to his collection

       // db.collection("Users").document("user")
        //...neededCalories

        showDialogWithOkButton("needed calories"+neededCalories)
        return neededCalories

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

    private fun createUserCollection(weight:Double,length:Double,level:Int,goal:Int,gender:String,name:String,email:String,neededCal:Double,userAge:Int) {
        val user = HashMap<String, Any>()

        db.collection("Users").document("user")
        user.put("name",name)
        user.put("email",email)
       // user.put("pass",pass)
        user.put("weight",weight)
        user.put("height",length)
        user.put("level",level)
        user.put("goal",goal)
        user.put("gender",gender)
        user.put("needed cal",neededCal)
        user.put("age",userAge)
       // db.collection("users").document(auth.getUid().set(user))

        /*MySharedPreference.clearData(this)
        MySharedPreference.putString(this, Constants.Keys.ID, auth.getInstance().getUid())*/


        db.collection("Users").document(auth.uid!!).set(user)

            .addOnSuccessListener(OnSuccessListener<Void> {
                Toast.makeText(
                    this,
                    "user added",
                    Toast.LENGTH_SHORT
                ).show()
            })
            .addOnFailureListener(OnFailureListener { e ->
                Toast.makeText(this, "Error_add_user", Toast.LENGTH_SHORT).show()
                Log.d(TAG, e.toString())
            })
  //   user.put("age",age)
       // user.put("Needed Calories",neededCalories)


        /*MySharedPreference.clearData(this)
        MySharedPreference.putString(this, Constants.Keys.ID, mAuth.getInstance().getUid())
*/
   /*     db.collection("Users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }*/
    }

    val actionCodeSettings = ActionCodeSettings.newBuilder()
        // URL you want to redirect back to. The domain (www.example.com) for this
        // URL must be whitelisted in the Firebase Console.
        .setUrl("https://www.example.com/finishSignUp?cartId=1234")
        // This must be true
        .setHandleCodeInApp(true)
        .setIOSBundleId("com.example.ios")
        .setAndroidPackageName(
            "com.example.android",
            true, /* installIfNotAvailable */
            "12" /* minimumVersion */)
        .build()


    private fun createAccount(email: String, password: String) {


        Log.d(registerFourActivity.TAG, "createAccount:$email")

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(registerFourActivity.TAG, "createUserWithEmail:success")

                   // sendEmailVerification(email)
                    showDialogWithOkButton("الرجاء التحقق من البريد الالكتروني")
                    val user = auth.currentUser
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(registerFourActivity.TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    showDialogWithOkButton("البريد الالكتروني غير صحيح")

                }
            }
        // [END create_user_with_email]
    }



    private fun sendEmailVerification(email: String) {
        // Disable button
        //verifyEmailButton.isEnabled = false

        // Send verification email
        // [START send_email_verification]

        val user = auth.currentUser
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) { task ->
                // [START_EXCLUDE]
                // Re-enable button
                // verifyEmailButton.isEnabled = true

                if (task.isSuccessful) {
                    Toast.makeText(
                        baseContext,
                        "Verification email sent to ${user.email} ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Log.e(registerFourActivity.TAG, "sendEmailVerification", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Failed to send verification email.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                // [END_EXCLUDE]
            }
        // [END send_email_verification]
    }

    companion object {
        const val TAG = "register Four "
    }


}
