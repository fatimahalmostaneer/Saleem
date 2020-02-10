package sa.ksu.gpa.saleem.register

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register_one.*
import sa.ksu.gpa.saleem.R

class registerOneActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nameTxt = findViewById<View>(R.id.nameET) as EditText?
        val emailTxt = findViewById<View>(R.id.emailET) as EditText?
        val passTxt = findViewById<View>(R.id.passwordED) as EditText?
        val repassTxt = findViewById<View>(R.id.repasswordED) as EditText?

        setContentView(R.layout.activity_register_one)


        auth = FirebaseAuth.getInstance()

        var nameEd = nameTxt?.text.toString()
        var emailEd = emailTxt?.text.toString()
        var passEd = passTxt?.text.toString()
        var repassEd = repassTxt?.text.toString()

        //setProgressBar(R.id.progressBar)


        val btn = findViewById<View>(R.id.nxtOneBtn) as Button?
        btn?.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {


                    createAccount(emailEd, passEd)

                    //btn?.setOnClickListener

            }

        })

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

    private fun sendEmailVerification() {
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
                    Log.e(TAG, "sendEmailVerification", task.exception)
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

    private fun validateForm(): Boolean {
        var valid = true

        val email = emailET.text.toString()
        if (TextUtils.isEmpty(email)) {
            emailET.error = "Required."
            valid = false
        } else {
            emailET.error = null
        }

        val password = passwordED.text.toString()
        if (TextUtils.isEmpty(password)) {
            passwordED.error = "Required."
            valid = false
        } else {
            passwordED.error = null
        }

        return valid
    }

/*    private fun updateUI(user: FirebaseUser? {
        hideProgressBar()
        if (user != null) {
            status.text = getString(R.string.emailpassword_status_fmt,
                user.email, user.isEmailVerified)
            detail.text = getString(R.string.firebase_status_fmt, user.uid)

            emailPasswordButtons.visibility = View.GONE
            emailPasswordFields.visibility = View.GONE
            signedInButtons.visibility = View.VISIBLE

            verifyEmailButton.isEnabled = !user.isEmailVerified
        } else {
            status.setText(R.string.signed_out)
            detail.text = null

            emailPasswordButtons.visibility = View.VISIBLE
            emailPasswordFields.visibility = View.VISIBLE
            signedInButtons.visibility = View.GONE
        }
    }*/



    companion object {
        private const val TAG = "EmailPassword"
    }

    private fun checkPassword(pass: String, repass: String): Boolean {
        return pass == repass
    }

    private fun showErrorMsg() {
        // Toast.makeText(SignUp.this, "password didn't match", Toast.LENGTH_LONG).show();
        showDialogWithOkButton("كلمتا المرور غير متطابقتين")
    }

    private fun createAccount(email: String, password: String) {

        val nameTxt = findViewById<View>(R.id.nameET) as EditText?
        val emailTxtt = findViewById<View>(R.id.emailET) as EditText?
        val passTxtt = findViewById<View>(R.id.passwordED) as EditText?
        val repassTxt = findViewById<View>(R.id.repasswordED) as EditText?

        if (!checkPassword(
                passTxtt?.getText().toString(),
                repassTxt?.getText().toString()
            )
        ) {
            showErrorMsg()
            return
        }
        //input
        val emailTxt = emailTxtt?.getText().toString()
        val passTxt = passTxtt?.getText().toString()

        if (emailTxt == "" && passTxt == "" && nameTxt?.getText().toString() == "" && repassTxt?.getText().toString() == "") {
            //show a popup for result
            showDialogWithOkButton("الرجاء ادخال اسم المستخدم و البريد الالكتروني وكلمة المرور")

        } else if (emailTxt == "") {
            //show a popup for result
            showDialogWithOkButton("الرجاء ادخال البريد الالكتروني")

        }//end if
        else if (nameTxt?.getText().toString() == "") {
            //show a popup for result
            showDialogWithOkButton("الرجاء ادخال اسم المستخدم")

        }//end if
        else if (repassTxt?.getText().toString() == "") {
            //show a popup for result
            showDialogWithOkButton("الرجاء اعادة تعين كلمة المرور")

        }//end if
        else if (passTxt == "") {
            //show a popup for result
            showDialogWithOkButton("الرجاء ادخال كلمة المرور")


        }
        Log.d(TAG, "createAccount:$email")
        if (!validateForm()) {
            return
        }
        /*   showProgressBar()*/

        // [START create_user_with_email]
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    //---------------------------
                    //sendEmailVerification()
                    val user = auth.currentUser
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //showDialogWithOkButton("البريد الالكتروني غير صحيح")

                    val intent = Intent(this, registerTwoActivity::class.java)

                    //updateUI(null)
                }

                // [START_EXCLUDE]
                /*  hideProgressBar()*/
                // [END_EXCLUDE]
            }
        // [END create_user_with_email]
    }



}

/*
private fun Button?.setOnClickListener(
    registerOneActivity: registerOneActivity,
    function: () -> Unit
) {
    createAccount(emailED, passTxt.text.toString())

}

fun createAccount(toString: Any, toString1: Any) {

}
*/



