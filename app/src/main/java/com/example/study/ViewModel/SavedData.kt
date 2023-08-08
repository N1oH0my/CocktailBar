import android.content.Context
import com.example.study.Models.Cocktail
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SavedData(context: Context?) {
    private val sharedPreferences = context?.getSharedPreferences("CocktailSharedPreferences", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveCocktailList(cocktailList: MutableList<Cocktail>) {
        val cocktailListJson = gson.toJson(cocktailList)
        sharedPreferences?.edit()?.putString("cocktailList", cocktailListJson)?.apply()
    }

    fun getCocktailList(): MutableList<Cocktail> {
        val cocktailListJson = sharedPreferences?.getString("cocktailList", null)
        return gson.fromJson(cocktailListJson, object : TypeToken<MutableList<Cocktail>>() {}.type)
            ?: mutableListOf()
    }
}