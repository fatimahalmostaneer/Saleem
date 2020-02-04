package sa.ksu.gpa.saleem

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import cn.pedant.SweetAlert.SweetAlertDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.Result
import kotlinx.android.synthetic.main.activity_scan.*
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity : AppCompatActivity() ,ZXingScannerView.ResultHandler{
    private lateinit var mScannerView: ZXingScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan)

        initScannerView()



    }

    private fun initScannerView() {
        mScannerView = ZXingScannerView(this)
        mScannerView.setAutoFocus(true)
        mScannerView.setResultHandler(this)
        frame_layout_camera.addView(mScannerView)
    }
    override fun onStart() {
        mScannerView.startCamera()
        doRequestPermission()
        super.onStart()
    }
    private fun doRequestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(arrayOf(Manifest.permission.CAMERA), 100)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            100 -> {
                initScannerView()
            }
            else -> {
            }
        }
    }

    override fun onPause() {
        mScannerView.stopCamera()
        super.onPause()
    }
    override fun handleResult(p0: Result?) {
        productNotFoundedDialog()
    }

    private fun productNotFoundedDialog() {
       SweetAlertDialog(this,SweetAlertDialog.WARNING_TYPE)
            .setTitleText("نعتذر لك")
            .setContentText("هذا المنتج غير متوفر")
            .setConfirmButton("حسنًا") { sDialog -> sDialog.dismissWithAnimation()
               finish()

           }
            .show()

    }


}
