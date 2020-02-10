package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import sa.ksu.gpa.saleem.MainActivity
import sa.ksu.gpa.saleem.R
import java.util.HashMap

class registerFourActivity : AppCompatActivity() {

    val user = HashMap<String, Any>()

    val db = FirebaseFirestore.getInstance()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_four)
        db.collection("users")

        val btn=findViewById<View>(R.id.startBtn) as Button?
        val one=findViewById<View>(R.id.goalOneBtn) as Button?
        val two=findViewById<View>(R.id.goalTwoBtn) as Button?
        val three=findViewById<View>(R.id.goalThreeBtn) as Button?

        var goal=0


        var button_background : Int = 1;

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
        }


        val x= intent.extras

        val length = getIntent().getDoubleExtra("height",0.0)
        var weight=getIntent().getDoubleExtra("wight",0.0)
        var gender = getIntent().getStringExtra("gender")
        var bmi = getIntent().getDoubleExtra("bmi",0.0)
        var level = getIntent().getIntExtra("level",0)

        btn?.setOnClickListener {
            Toast.makeText(this@registerFourActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)


            if (gender=="male"){
                calcualteCaloriesMen(3.0,weight!!,length!!,goal!!)


            }
            if (gender=="female"){
                calcualteCaloriesWomen(3.0,weight!!,length!!,goal!!)


            }

            startActivity(intent)
        }



    }

    fun calcualteCaloriesWomen(activityLevel:Double,weight:Double,length:Double,goal:Int){

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


    }
    fun calcualteCaloriesMen(activityLevel:Double,weight:Double,length:Double,goal:Int){
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

        db.collection("Users").document("user")
        //...neededCalories

        showDialogWithOkButton("needed calories"+neededCalories)

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
