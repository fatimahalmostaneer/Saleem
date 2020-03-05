package sa.ksu.gpa.saleem.recipe

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_recipes.*
import sa.ksu.gpa.saleem.R

class Recipes : Fragment() {
    private lateinit var db: FirebaseFirestore

    companion object {

        fun newInstance(): Recipes {
            return Recipes()
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_recipes, container, false)
        db = FirebaseFirestore.getInstance()
        val activity = activity as Context


        return view
    }




}