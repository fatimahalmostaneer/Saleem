package sa.ksu.gpa.saleem

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.advice_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home_body.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE=123;
    private var btn: Button? = null

    @SuppressLint("ResourceType")
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

        val speedDialView = findViewById<SpeedDialView>(R.id.speedDial)
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(10009, R.drawable.ic_scan)
                .create()
        )
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(10011, R.drawable.ic_dumbbell)
                .create()
        )
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(10012, R.drawable.ic_timer_black_24dp)
                .create()
        )
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(10013, R.drawable.ic_water)
                .create()
        )
        speedDialView.addActionItem(
            SpeedDialActionItem.Builder(10014, R.drawable.ic_report)
                .create()
        )
        speedDialView.setOnActionSelectedListener(SpeedDialView.OnActionSelectedListener { actionItem ->
            when (actionItem.id) {
                10009 -> {
                    //select scan barcode
                    val permisison= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

                    val intent = Intent(this@MainActivity, ScanActivity::class.java)
                    if(permisison!= PackageManager.PERMISSION_GRANTED){
                        makeRequest()
                    }

                    else startActivity(intent)
                    speedDialView.close() // To close the Speed Dial with animation
                    return@OnActionSelectedListener true // false will close it without animation
                }
            }
            false
        })
        findViewById<ImageView>(R.id.ivAddView).setOnClickListener { addFood() }

        //button click to show dialog
        addAdviceIV.setOnClickListener{
            //inflate dialog with custom view
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.advice_dialog, null)
            //alert dialog builder
            val mBuilder= AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("نشر نصيحة")
            //show dialog
            val mAlertDialog = mBuilder.show()
            //login click of custom layout
            mDialogView.dialogShareBtn.setOnClickListener{
                //dismiss dialog
                mAlertDialog.dismiss()
                //get text from editTexts of custom layout
                val title = mDialogView.dialogadviceTitleET.text.toString()
                val body = mDialogView.dialogAdviceET.text.toString()
                //set input in TV
                advicesTV.setText("العنوان: "+title+"\n النصيحة: "+body)
            }
            //cancel button click of custom layout
            mDialogView.dialogCancelBtn.setOnClickListener{
                //dismiss dialog
                mAlertDialog.dismiss()
            }

        }
        }
    fun showAddFood(data: ArrayList<String>) {
        val fragment = ItemListDialogFragmentA(data)
        val bundle = Bundle()
        bundle.putStringArrayList("item_data", data)
        this.supportFragmentManager?.let { fragment.show(it, "tag") }
        fragment.setOnSelectData(object : ItemListDialogFragmentA.Listener {
            override fun onItemClicked(position: Int) {

            }
        })
    }
    public fun addFood(){
        val list = ArrayList<String>()
        list.add("وجبة مفصلة")
        list.add("وجبة ")
        showAddFood(list)
        // ItemListDialogFragment fragment = ItemListDialogFragment.newInstance(new Gson().toJson(menuDataArrayList));
        //        fragment.show(getActivity().getSupportFragmentManager(), "tag");
        //        fragment.setOnSelectData(position -> {
        //            cetType.setText(data.get(position).getDepartmentName());
        //            typeId = data.get(position).getId();
        //        });


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

