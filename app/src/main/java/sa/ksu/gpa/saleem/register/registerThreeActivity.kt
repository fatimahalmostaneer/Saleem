package sa.ksu.gpa.saleem.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import sa.ksu.gpa.saleem.R
import java.util.HashMap

class registerThreeActivity : AppCompatActivity() {

    val user = HashMap<String, Any>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_three)

        val one=findViewById<View>(R.id.levelOneBtn) as Button?
        val two=findViewById<View>(R.id.levelTwoBtn) as Button?
        val three=findViewById<View>(R.id.levelThreeBtn) as Button?

        val btn=findViewById<View>(R.id.nxtThreeBtn) as Button?



        val db = FirebaseFirestore.getInstance()


        var button_background : Int = 1;


        db.collection("users")

        var goal=0


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
           user.put("goal",1)
           goal=1
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

            user.put("goal",2)
            goal=2
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
            user.put("goal",3)
            goal=3
        }



        btn?.setOnClickListener {

            Toast.makeText(this@registerThreeActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerFourActivity::class.java)
           val x=intent.extras



                val length = getIntent().getDoubleExtra("height",0.0)
                var weight=getIntent().getDoubleExtra("wight",0.0)
                var gender = getIntent().getStringExtra("gender")
                var bmi = getIntent().getDoubleExtra("bmi",0.0)

                intent.putExtra("wight",weight)
                intent.putExtra("height",length)
                intent.putExtra("BMI",bmi)
                intent.putExtra("gender",gender)



            intent.putExtra("goal",goal)


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


