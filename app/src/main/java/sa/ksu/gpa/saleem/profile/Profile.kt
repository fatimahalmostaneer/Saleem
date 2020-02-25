package sa.ksu.gpa.saleem.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import kotlinx.android.synthetic.main.fragment_item_list_dialog_item.*
import kotlinx.android.synthetic.main.reset_password.*
import sa.ksu.gpa.saleem.register.registerFourActivity
import com.google.firebase.auth.FirebaseUser

import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.R

import com.google.firebase.database.DatabaseReference
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T





class Profile : AppCompatActivity() {
    val db = FirebaseFirestore.getInstance()

    internal var user: User? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(sa.ksu.gpa.saleem.R.layout.activity_profile)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(sa.ksu.gpa.saleem.R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(sa.ksu.gpa.saleem.R.id.tabLayout)
        tabs.setupWithViewPager(viewPager)

       // var ref = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().currentUser!!.uid)

  /*      val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                user = dataSnapshot.getValue() as User
                textView.text = user?.getName()
            }
            override fun onCancelled(databaseError: DatabaseError) {
                // handle error
            }
        }*/
      //  ref.addListenerForSingleValueEvent(menuListener)

      val userUid = FirebaseAuth.getInstance().currentUser!!.uid
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user!!.uid

        val db = FirebaseFirestore.getInstance()




        val docRef = db.collection("Users").document(userUid)

            docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                 //   Log.d(registerFourActivity.TAG, "${document.id} => ${document.data}")

                 val name = document.get("name").toString()




                    Log.d(registerFourActivity.TAG, "${document.id} => ${document.get("name")}")

                    val nameTxt: TextView =findViewById(sa.ksu.gpa.saleem.R.id.userName)
                    nameTxt.setText(name)


                }
            }
            .addOnFailureListener { exception ->
                Log.d(registerFourActivity.TAG, "get failed with ", exception)


            }

    }

}
