package sa.ksu.gpa.saleem

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_excercise_dialog.view.*
import kotlinx.android.synthetic.main.scanner_dialog_add_snack.view.*

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE=123;
    private var btn: Button? = null
    private var btn1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.fortesting) as Button
        btn1 = findViewById(R.id.fortestingadd) as Button
        btn1!!.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_excercise_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            val  mAlertDialog = mBuilder.show()
            mDialogView.addExcercise.setOnClickListener{
               val burnt = mDialogView.addExcerciseburentCal!!.text.toString().toDouble()
                val workoutName = mDialogView.addExcerciseWorkoutname!!.text.toString()

                Log.d("this",""+burnt+workoutName)
                //Firebase
                if (burnt==null||workoutName==null)
                    Toast.makeText(this,"الرجاء إدخال المعلومات كاملة", Toast.LENGTH_LONG)
                else{

                    Toast.makeText(this,"تمت اضافة المنتج", Toast.LENGTH_LONG)
                    mAlertDialog.dismiss()

                }
                // extra detail add a success shape
            }
            mDialogView.cancelExcercise.setOnClickListener{
                mAlertDialog.dismiss()

            }
        }


        btn!!.setOnClickListener {
            val permisison= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            val intent = Intent(this@MainActivity, ScanActivity::class.java)
            if(permisison!= PackageManager.PERMISSION_GRANTED){
                makeRequest()
            }

            else startActivity(intent)
        }

        }
    private fun makeRequest() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                    Log.d("tag","Permission Denied")

                } else {
                    Log.d("tag","permisson granted")
                    val intent = Intent(this@MainActivity, ScanActivity::class.java)
                    startActivity(intent)

                }
            }
        }
    }


    }

