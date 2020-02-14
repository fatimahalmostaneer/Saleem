package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_two.*
import sa.ksu.gpa.saleem.R
import java.util.HashMap

class registerThreeActivity : AppCompatActivity() {

    val user = HashMap<String, Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_three)
        val intent = Intent(this, registerFourActivity::class.java)


        val one=findViewById<View>(R.id.levelOneBtn) as Button?
        val two=findViewById<View>(R.id.levelTwoBtn) as Button?
        val three=findViewById<View>(R.id.levelThreeBtn) as Button?

        val btn=findViewById<View>(R.id.nxtThreeBtn) as Button?



        val db = FirebaseFirestore.getInstance()


        var button_background : Int = 1;


        db.collection("users")

//        var level=0


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
           //user.put("goal",1)
          var level=1
           intent.putExtra("level",level)

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

            //user.put("goal",2)
           var level=2
            intent.putExtra("level",level)

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
            //user.put("goal",3)
           var level=3
            intent.putExtra("level",level)
            Log.d("this3",""+level)

        }



        btn?.setOnClickListener {

            Toast.makeText(this@registerThreeActivity, "Click...", Toast.LENGTH_LONG).show()



                val length = getIntent().getDoubleExtra("height",0.0)
                var weight=getIntent().getDoubleExtra("wight",0.0)
                var gender = getIntent().getStringExtra("gender")
                var bmii = getIntent().getDoubleExtra("bmi",0.0)
                var age= getIntent().getIntExtra("age",0)
            var userAge= getIntent().getStringExtra("uaserAge")

            var bmi=(weight)/(length/100*length/100)


            var name = getIntent().getStringExtra("name")
            var pass = getIntent().getStringExtra("password")
            var email = getIntent().getStringExtra("email")


                intent.putExtra("wight",weight)
                intent.putExtra("height",length)
                intent.putExtra("BMI",bmi)
                intent.putExtra("gender",gender)
               // intent.putExtra("age",age)
            intent.putExtra("userAge",userAge)
            intent.putExtra("name",name)
            intent.putExtra("password",pass)
            intent.putExtra("email",email)

            Log.d("this3",""+email)
            Log.d("this3",""+name)
            Log.d("this3",""+pass)
            Log.d("this3",""+length)
            Log.d("this3",""+weight)
          //  Log.d("this3",""+level)
            Log.d("this3",""+bmi)
            Log.d("this3",""+gender)
           // Log.d("this3",""+age)
            Log.d("this3",""+userAge)


            startActivity(intent)
        }



    }

}



/*private fun Button?.setOnClickListener(registerThreeActivity: registerThreeActivity) {
    when(view.id) {
        R.id.goalOneBtn -> {*//* do your code *//*}
        R.id.goalTwoBtn -> {*//* do your code *//*}
        R.id.goalThreeBtn -> {*//* do your code *//*}
        else -> {*//* do your code *//*}
        }
    }*/


