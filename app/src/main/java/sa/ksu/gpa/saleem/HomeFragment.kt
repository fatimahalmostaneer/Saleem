package sa.ksu.gpa.saleem


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.leinardi.android.speeddial.SpeedDialActionItem
import com.leinardi.android.speeddial.SpeedDialView
import kotlinx.android.synthetic.main.add_excercise_dialog.view.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ImageView>(R.id.ivAddView).setOnClickListener { addFood() }
        view.findViewById<LinearLayout>(R.id.add_breakfast).setOnClickListener { addFood() }
        view.findViewById<LinearLayout>(R.id.add_lunch).setOnClickListener { addFood() }
        view.findViewById<LinearLayout>(R.id.add_dinner).setOnClickListener { addFood() }
        view.findViewById<LinearLayout>(R.id.add_snack).setOnClickListener { addFood() }
    }
    fun showAddFood(data: ArrayList<String>) {
        val fragment = ItemListDialogFragmentA(data)
        val bundle = Bundle()
        bundle.putStringArrayList("item_data", data)
        this.activity?.supportFragmentManager?.let { fragment.show(it, "tag") }
        fragment.setOnSelectData(object : ItemListDialogFragmentA.Listener {
            override fun onItemClicked(position: Int) {
                when(position){
                    0 -> {
                        startAddFoodActivity()
                    }
                    1 -> {
                        addExcercizeDialog()
                    }
                }
            }
        })
    }

    private fun startAddFoodActivity() {

        var intent = Intent(activity,AddFoodActivity::class.java).apply{

        }
        startActivity(intent)

    }

    fun addFood(){
        val list = ArrayList<String>()
        list.add("وجبة مفصلة")
        list.add("وجبة ")
        showAddFood(list)

    }

    private fun addExcercizeDialog() {
        val mDialogView = LayoutInflater.from(context).inflate(R.layout.add_fast_food, null)
        val mBuilder = activity?.let {
            AlertDialog.Builder(it)
                .setView(mDialogView)
        }

        val  mAlertDialog = mBuilder?.show()
        mAlertDialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        var burnt = mDialogView.addExcerciseburentCal!!.text
        val workoutName = mDialogView.addExcerciseWorkoutname!!.text
        Log.d("this",""+burnt+workoutName)

        mDialogView.addExcercise.setOnClickListener{


            if (burnt==null||workoutName==null){

                mDialogView.addExcerciseError.setText("الرجاء ادخال المعلومات الناقصة")
            }
            else{

                Toast.makeText(context,"تمت اضافة الوجبة", Toast.LENGTH_LONG)
                mAlertDialog?.dismiss()

            }
            // extra detail add a success shape
        }
        mDialogView.cancelExcercise.setOnClickListener{
            mAlertDialog?.dismiss()

        }

    }

}
