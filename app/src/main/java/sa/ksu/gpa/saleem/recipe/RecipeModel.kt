package sa.ksu.gpa.saleem.recipe


data class RecipeModel(var recipeId: Int,
                      var recipeTitle: String,
                      var recipePicture: String?,
                       var recipeCalories:String) {
    constructor() : this(0, "", null,"")
}