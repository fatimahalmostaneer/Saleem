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

        val btn=findViewById<View>(R.id.nxtThreeBtn) as Button?



        val db = FirebaseFirestore.getInstance()


        var button_background : Int = 1;
        val one=findViewById<View>(R.id.levelOneBtn) as Button?
        val two=findViewById<View>(R.id.levelTwoBtn) as Button?
        val three=findViewById<View>(R.id.levelThreeBtn) as Button?



    /*    var button_change = findViewById(R.id.button_changing) as Button;
*/
        db.collection("users")

       one?.setOnClickListener {
            if(button_background==2){
                one.setBackgroundResource(R.drawable.rounded_buttons);
                button_background=1;
            } else if(button_background==1){
                one.setBackgroundResource(R.drawable.selected_btn);
                button_background=2;
            }
           user.put("level","beginner")
        }
        two?.setOnClickListener{
            if(button_background==2){
                two?.setBackgroundResource(R.drawable.rounded_buttons);
                button_background=1;
            } else if(button_background==1){
                two?.setBackgroundResource(R.drawable.selected_btn);
                button_background=2;
            }
            user.put("level","Intermediate")
        }

        three?.setOnClickListener{
            if(button_background==2){
                three?.setBackgroundResource(R.drawable.rounded_buttons);
                button_background=1;
            } else if(button_background==1){
                three?.setBackgroundResource(R.drawable.selected_btn);
                button_background=2;
            }
            user.put("level","advance")
        }



        btn?.setOnClickListener {

            Toast.makeText(this@registerThreeActivity, "Click...", Toast.LENGTH_LONG).show()
            val intent = Intent(this, registerFourActivity::class.java)
// To pass any data to next activity
            //intent.putExtra("keyIdentifier", value)
// start your next activity
            startActivity(intent)
        }
    }
/*    fun level(view: View) {

        when (view.id) {
            R.id.levelOneBtn ->{ user.put("level","beginner")
                if(button_background==2){
                    one?.setBackgroundResource(R.drawable.rounded_buttons);
                    button_background=1;
                } else if(button_background==1){
                    one?.setBackgroundResource(R.drawable.selected_btn);
                    button_background=2;
                }
            }
                    R.id.levelTwoBtn -> {user.put("level","Intermediate")
                        if(button_background==2){
                            two?.setBackgroundResource(R.drawable.rounded_buttons);
                            button_background=1;
                        } else if(button_background==1){
                            two?.setBackgroundResource(R.drawable.selected_btn);
                            button_background=2;
                        }
                    }
                R.id.levelThreeBtn -> {user.put("level","Advanced")
                    if(button_background==2){
                        three?.setBackgroundResource(R.drawable.rounded_buttons);
                        button_background=1;
                    } else if(button_background==1){
                        three?.setBackgroundResource(R.drawable.selected_btn);
                        button_background=2;
                    }
                }
           *//* else -> perform action*//*
         }

        // Do something in response to button click
   }*/
}
