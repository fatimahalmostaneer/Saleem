package sa.ksu.gpa.saleem

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE=123;
    private var btn: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.fortesting) as Button

        btn!!.setOnClickListener {
            val permisison= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            val intent = Intent(this@MainActivity, ScanActivity::class.java)
            if(permisison!= PackageManager.PERMISSION_GRANTED){
                makeRequest()
            }

            else startActivity(intent)
        }
        //setSupportActionBar(toolbar)

       // fab.setOnClickListener { view ->
           // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          //      .setAction("Action", null).show()
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

