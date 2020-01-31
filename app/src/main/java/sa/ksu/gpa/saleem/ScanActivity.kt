package sa.ksu.gpa.saleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.zxing.Result
import me.dm7.barcodescanner.zxing.ZXingScannerView

class ScanActivity : AppCompatActivity() ,ZXingScannerView.ResultHandler{
    private var mScannerView: ZXingScannerView? = null
    val db = FirebaseFirestore.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)   // Programmatically initialize the scanner view
        setContentView(mScannerView)
    }
    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

    override fun handleResult(rawResult: Result) {

         db.collection("Products").document(rawResult.text).get()
            .addOnSuccessListener { document ->
                if (document !=null){
                    Log.d("tag", "sucees ")

                }
            }.addOnFailureListener{exception ->
                Log.d("tag", "get failed with ", exception)
            }

        onBackPressed()

    }
}
