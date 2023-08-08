- CocktailBar
-В проекте использовались следующие библиотеки:

-[Glide](https://github.com/bumptech/glide) (`implementation 'com.github.bumptech.glide:glide:4.12.0'`) - библиотека для загрузки и отображения изображений.
- [Lifecycle ViewModel](https://developer.android.com/jetpack/androidx/releases/lifecycle) (`implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1'`) - библиотека для управления данными, связанными с пользовательским интерфейсом.
- [Activity](https://developer.android.com/jetpack/androidx/releases/activity) (`implementation 'androidx.activity:activity-ktx:1.7.2'`) - библиотека для упрощения работы с активностями в AndroidX.
- также используется binding для упрощения работы с xml

-Описание функционала:
-сначала создаётся MainActivity и запускает HomePageFragment, загружается начальный экран без коктейлей
При добавлении часть фрагмента заменяется другим для отображения списка добавленных коктейлей
При нажатии на кнопку добавить открывается активити save_cocktail_view
при нажатии на кнопку сохранения список регенерируется вместе с новым элементом и нас переносит на главный экран с изменённым фрагментом
при нажатии на кнопку отмена нас возвращает обратно
при нажатии на конкретный коктель открывается cocktail_view_activity со всеми данными (изображение почему-то не отображается именно в этой активити)
при нажатии кнопки edit запускается ранее упомянутая save_cocktail_view, но в этот раз туда передаётся intent для различия с добавлением, все поля заполняются и после сохранения обновляются только на главном экране
при отмене нас возвращает обратно

-Храняться данные в MutableLiveData<MutableList<Cocktail>> c подпиской на изменения
-для сохранения данных была попытка написать класс с sharedpreferences но не вышло, пыталась сохранить MutableList, в виде JSON, но приложение всегда зависало при сохранении
так же пыталась исправить recyclerview чтобы не было таких оступов, но не нашла ничего, что исправило бы ситуацию
