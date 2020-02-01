package sa.ksu.gpa.saleem

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.android.extension.responseJson
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {

    internal var LoginURL = "https://demonuts.com/Demonuts/JsonTest/Tennis/simplelogin.php"
    private var etusername: EditText? = null
    private var etpassword:EditText? = null
    private var btnlogin: Button? = null
    private var tvreg: TextView? = null
    private val LoginTask = 1
    private var preferenceHelper: PreferenceHelper? = null
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferenceHelper = PreferenceHelper(this)

        etusername = findViewById<View>(R.id.emailET) as EditText
        etpassword = findViewById<View>(R.id.passwordED) as EditText

        btnlogin = findViewById<View>(R.id.loginBtn) as Button
        tvreg = findViewById<View>(R.id.signUpBtn) as TextView

        tvreg!!.setOnClickListener {
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }

        btnlogin!!.setOnClickListener {
            try {
                login()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

    @Throws(IOException::class, JSONException::class)
    private fun login() {

        showSimpleProgressDialog(this@LoginActivity, null, "Loading...", false)

        try {

            Fuel.post(LoginURL, listOf(
                "username" to  etusername!!.text.toString()
                , "password" to  etpassword!!.text.toString()
            )).responseJson { request, response, result ->
                Log.d("plzzzzz", result.get().content)
                onTaskCompleted(result.get().content,LoginTask)
            }
        } catch (e: Exception) {

        } finally {

        }
    }

    private fun onTaskCompleted(response: String, task: Int) {
        Log.d("responsejson", response)
        removeSimpleProgressDialog()  //will remove progress dialog
        when (task) {
            LoginTask -> if (isSuccess(response)) {
                saveInfo(response)
                Toast.makeText(this@LoginActivity, "Login Successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@LoginActivity, WelcomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                this.finish()
            } else {
                Toast.makeText(this@LoginActivity, getErrorMessage(response), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun saveInfo(response: String) {
        preferenceHelper!!.putIsLogin(true)
        try {
            val jsonObject = JSONObject(response)
            if (jsonObject.getString("status") == "true") {
                val dataArray = jsonObject.getJSONArray("data")
                for (i in 0 until dataArray.length()) {

                    val dataobj = dataArray.getJSONObject(i)
                    preferenceHelper!!.putName(dataobj.getString("name"))
                    preferenceHelper!!.putHobby(dataobj.getString("hobby"))
                }
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    fun isSuccess(response: String): Boolean {
        try {
            val jsonObject = JSONObject(response)
            return if (jsonObject.optString("status") == "true") {
                true
            } else {

                false
            }

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return false
    }

    fun getErrorMessage(response: String): String {
        try {
            val jsonObject = JSONObject(response)
            return jsonObject.getString("message")

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return "No data"
    }

    fun showSimpleProgressDialog(context: Context, title: String?, msg: String, isCancelable: Boolean) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg)
                mProgressDialog!!.setCancelable(isCancelable)
            }
            if (!mProgressDialog!!.isShowing) {
                mProgressDialog!!.show()
            }

        } catch (ie: IllegalArgumentException) {
            ie.printStackTrace()
        } catch (re: RuntimeException) {
            re.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog!!.isShowing) {
                    mProgressDialog!!.dismiss()
                    mProgressDialog = null
                }
            }
        } catch (ie: IllegalArgumentException) {
            ie.printStackTrace()

        } catch (re: RuntimeException) {
            re.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}

class PreferenceHelper (private val context: Context) {

        private val INTRO = "intro"
        private val NAME = "name"
        private val HOBBY = "hobby"
        private val app_prefs: SharedPreferences

        init {
            app_prefs = context.getSharedPreferences(
                "shared",
                Context.MODE_PRIVATE
            )
        }

        fun putIsLogin(loginorout: Boolean) {
            val edit = app_prefs.edit()
            edit.putBoolean(INTRO, loginorout)
            edit.commit()
        }

        fun getIsLogin(): Boolean {
            return app_prefs.getBoolean(INTRO, false)
        }

        fun putName(loginorout: String) {
            val edit = app_prefs.edit()
            edit.putString(NAME, loginorout)
            edit.commit()
        }

    fun getNames(): String? {
        return app_prefs.getString(NAME, "")
    }

    fun putHobby(loginorout: String) {
        val edit = app_prefs.edit()
        edit.putString(HOBBY, loginorout)
        edit.commit()
    }

    fun getHobbys(): String? {
        return app_prefs.getString(HOBBY, "")
    }
}
