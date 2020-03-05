package sa.ksu.gpa.saleem.recipe

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.viewpager.widget.ViewPager
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.animations.DescriptionAnimation
import com.glide.slider.library.slidertypes.BaseSliderView
import com.glide.slider.library.slidertypes.TextSliderView
import com.glide.slider.library.tricks.ViewPagerEx
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_view_shared_recipe.*
import kotlinx.android.synthetic.main.activity_view_shared_recipe.recyclerViewRecipes
import kotlinx.android.synthetic.main.fragment_recipes.*
import sa.ksu.gpa.saleem.R


class viewSharedRecipeActivity : AppCompatActivity(),
    SearchView.OnQueryTextListener, BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener  {

    private lateinit var db: FirebaseFirestore
    private lateinit var slider: SliderLayout
    private lateinit var viewPager: ViewPager
    private lateinit var fragmentadapter: fragmentadapter
    val listOfRecipes = ArrayList<RecipeModel>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_shared_recipe)
        db = FirebaseFirestore.getInstance()
        slider= findViewById(R.id.slider)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewRecipes)

        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerViewRecipes.addItemDecoration(GridItemDecoration(12, 2))
        val recipeListAdapter = recipeListStaggeredAdapter()
        recyclerViewRecipes.adapter = recipeListAdapter
        recipeListAdapter.setMovieList(listOfRecipes)
        generateDummyData()

       initSlider()

    }
    private fun generateDummyData() {

        listOfRecipes.clear()
        db.collection("Recipes")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {

                    var recipename= document!!.get("name").toString()
                    var recipeImage= document!!.get("image").toString()
                    var recipeCalories= document!!.get("calories").toString()
                    var movieModel = RecipeModel(1, recipename, recipeImage,recipeCalories)
                    listOfRecipes.add(movieModel)
                    Log.d("here", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w("here", "Error getting documents: ", exception)
            }


    }


    private fun initSlider() {

        db.collection("Recipes").limit(4).get().addOnSuccessListener { documents ->
            for (document in documents) {
                val textSliderView = TextSliderView(this)

                var name =document.get("name").toString()
                var image =document.get("image").toString()
                textSliderView
                    .description(name)
                    .image(image)
                    .setOnSliderClickListener(this);

                slider.addSlider(textSliderView)
            }


        }.addOnFailureListener{
          Log.d("Exception",""+it.toString())
        }
        slider.setPresetTransformer(SliderLayout.Transformer.Default)
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
        slider.setCustomAnimation(DescriptionAnimation())
        slider.setDuration(3000)
    }



    override fun onQueryTextSubmit(query: String?): Boolean {
        if(query!=null){

            listOfRecipes.clear()
            db.collection("Recipes")
                .whereEqualTo("name",query)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {

                        var recipename= document!!.get("name").toString()
                        var recipeImage= document!!.get("image").toString()
                        var recipeCalories= document!!.get("calories").toString()
                        var movieModel = RecipeModel(1, recipename, recipeImage,recipeCalories)
                        listOfRecipes.add(movieModel)
                        Log.d("here", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("here", "Error getting documents: ", exception)
                }

            return true

        }
        return false

    }

    override fun onQueryTextChange(newText: String?): Boolean {

        if(newText!=null){

            listOfRecipes.clear()
            db.collection("Recipes")
                .whereArrayContains("name",newText)
                .get()
                .addOnSuccessListener { documents ->
                    for (document in documents) {

                        var recipename= document!!.get("name").toString()
                        var recipeImage= document!!.get("image").toString()
                        var recipeCalories= document!!.get("calories").toString()
                        var movieModel = RecipeModel(1, recipename, recipeImage,recipeCalories)
                        listOfRecipes.add(movieModel)
                        Log.d("here", "${document.id} => ${document.data}")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w("here", "Error getting documents: ", exception)
                }

            return true

        }
        return false
    }

    override fun onSliderClick(slider: BaseSliderView?) {
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }


}
