package sa.ksu.gpa.saleem

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_excercise_dialog.view.*
import kotlinx.android.synthetic.main.advice_dialog.view.*
import kotlinx.android.synthetic.main.fragment_home_body.*
import java.lang.Exception
import java.util.ArrayList


class SplashScreenActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE=123;
    private var btn: Button? = null
    private var addExcercize: Button? = null
    private lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        val background = object : Thread(){
            override fun run(){
                try {
                    Thread.sleep(5000)

                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } catch (e: Exception){
                    e.printStackTrace()
                }
            }
        }
        background.start()

        /* db= FirebaseFirestore.getInstance()
        btn = findViewById(R.id.fortesting) as Button

        btn!!.setOnClickListener {
            val permisison= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            val intent = Intent(this@SplashScreenActivity, ScanActivity::class.java)
            if(permisison!= PackageManager.PERMISSION_GRANTED){
                makeRequest()

            }

            else startActivity(intent)
        }

        addExcercize = findViewById(R.id.fortestingadd) as Button

        addExcercize!!.setOnClickListener {
            addExcercizeDialog()

        }

        //button click to show dialog
        addAdviceIV.setOnClickListener{
            addAdviceDialog()

        }*/

        }

    /*private fun addAdviceDialog(){
        //inflate dialog with custom view
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.advice_dialog, null)
        //alert dialog builder
        val mBuilder= AlertDialog.Builder(this)
            .setView(mDialogView)
            .setTitle("نشر نصيحة")
        //show dialog
        val mAlertDialog = mBuilder.show()
        //advice click of custom layout
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

    private fun addExcercizeDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_excercise_dialog, null)
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)

        val  mAlertDialog = mBuilder.show()
        mAlertDialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        var burnt = mDialogView.addExcerciseburentCal!!.text
        val workoutName = mDialogView.addExcerciseWorkoutname!!.text
        Log.d("this",""+burnt+workoutName)

        mDialogView.addExcercise.setOnClickListener{


            if (burnt==null||workoutName==null){

                mDialogView.addExcerciseError.setText("الرجاء ادخال المعلومات الناقصة")
            }
            else{
                var  burnt1 = burnt!!.toString()
                var burntcal=burnt1.toDouble()
                val currentuser = FirebaseAuth.getInstance().currentUser!!.uid
                val burntCalories = db.collection("Users").document(currentuser)
                burntCalories.update("burntCalories", FieldValue.increment(burntcal))
                Toast.makeText(applicationContext,"تمت اضافة المنتج", LENGTH_LONG)
                mAlertDialog.dismiss()

            }
            // extra detail add a success shape
        }
        mDialogView.cancelExcercise.setOnClickListener{
            mAlertDialog.dismiss()

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
                    val intent = Intent(this@SplashScreenActivity, ScanActivity::class.java)
                    startActivity(intent)

                }
            }
        }
    }*/


    }

