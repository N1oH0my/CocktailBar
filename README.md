
<!DOCTYPE html>
<html>
<head>
</head>
<body>

<h1>CocktailBar</h1>

<p>В проекте использовались следующие библиотеки:</p>

<ul>
<li><a href="https://github.com/bumptech/glide">Glide</a> (implementation 'com.github.bumptech.glide:glide:4.12.0') - библиотека для загрузки и отображения изображений.</li>
<li><a href="https://developer.android.com/jetpack/androidx/releases/lifecycle">Lifecycle ViewModel</a> (implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1') - библиотека для управления данными, связанными с пользовательским интерфейсом.</li>
<li><a href="https://developer.android.com/jetpack/androidx/releases/activity">Activity</a> (implementation 'androidx.activity:activity-ktx:1.7.2') - библиотека для упрощения работы с активностями в AndroidX.</li>
<li>Также используется binding для упрощения работы с xml.</li>
</ul>



<p>Описание функционала:</p>

<ul>
<li>Сначала создаётся MainActivity и запускает HomePageFragment, загружается начальный экран без коктейлей.</li>
<li>При добавлении, часть фрагмента заменяется другим для отображения списка добавленных коктейлей.</li>
<li>При нажатии на кнопку добавить, открывается активити SaveCocktailView.</li>
<li>При нажатии на кнопку сохранения, список регенерируется вместе с новым элементом и переносит нас на главный экран с изменённым фрагментом.</li>
<li>При нажатии на кнопку отмены, возвращаемся обратно.</li>
<li>При нажатии на конкретный коктель, открывается активити Cocktail_View_Activity со всеми данными (изображение почему-то не отображается именно в этой активити).</li>
<li>При нажатии кнопки Edit, запускается ранее упомянутая активити SaveCocktailView, но в этот раз туда передаётся Intent "edit" для различия с добавлением, все поля заполняются и после изменения и сохранения обновляются только на главном экране.</li>
<li>При отмене, возвращаемся обратно.</li>
</ul>

<p>Данные хранятся в "MutableLiveData MutableList(Cocktail)" с подпиской на изменения.</p>

<p>Для сохранения данных была попытка написать класс с SharedPreferences, но не удалось, пыталась сохранить MutableList, а ещё в виде JSON, но приложение всегда зависало при сохранении.</p>

<p>Также была попытка исправить RecyclerView, чтобы не было таких отступов, но не было найдено решения для этой ситуации.</p>
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-29-37-671_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-29-45-946_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-29-48-189_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-33-41-338_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-33-44-995_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-33-49-785_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-33-52-737_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-33-54-820_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-34-18-075_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-34-20-407_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-34-41-084_com.example.study.jpg" alt="Alt текст">
<img src="https://github.com/N1oH0my/CocktailBar/blob/master/img/Screenshot_2023-08-08-22-34-43-539_com.example.study.jpg" alt="Alt текст">
</body>
</html>

