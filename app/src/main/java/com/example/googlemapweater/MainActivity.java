package com.example.googlemapweater;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.TextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWeather(23,49);
    }

    public void getWeather(int lon, int lat) {
        Call<WeatherResponse> callToday = WeatherApi.getClient().create(WeatherApi.WeatherInterface.class).getToday(lon, lat, WeatherApi.APP_ID);
        callToday.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                Log.e(TAG, "onResponse");
                WeatherResponse data = response.body();
                Log.d(TAG,response.toString());
                System.out.println(data.getCity());
                if (response.isSuccessful()) {
                    TextView textViewInfo = findViewById(R.id.weatherInfo);
                    textViewInfo.setText(data.getCity() + " +" + data.getTemp() + " C " + data.getWeather());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchViewItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;

            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}


//Завдання.
//        1) Почитати, що таке REST API
//        2) Підключити, прочитати доку про retrofit https://square.github.io/retrofit/
//        3) https://openweathermap.org/api ознайомитись з документацією (заюзати їх API в майбутньому)
//        4) https://developers.google.com/maps/documentation/android-sdk/intro ознайомитись (заюзати)
//
//        При старті аплікації, має грузитись гугл карта, з маркером поточної локації користувача.
//        При кліку на маркер карти має показувати поточну погоду. (Lviv, +16 C , sunny)
//        в Аплікації має бути тулбар з можливість шукати (searchview), поки без функціоналу.
//        Також має бути можливість поставити маркер наприклад на київ і взнати погоду там.
//        Поки все.

//        Тарас Пащенко, [12.03.20 15:18]
//        хз, чи вийде це Вові зробити
//        гугл вроді зара поміняв все
//        і щоб дістати api-key для карт тре вказати billing account
//
//        Сєрий, [12.03.20 15:19]
//        навіть якщо так, йому всерівно debug треба, я думаю це реалізується
//
//        Сєрий, [12.03.20 15:21]
//        та введе свою карту і норм буде
//
//        Сєрий, [12.03.20 15:26]
//        If you want to change the quota to more than 1 load per day, you need a billing account (link a credit card to your Google account).
//
//        Сєрий, [12.03.20 15:26]
//        підари
//
//        Сєрий, [12.03.20 15:27]
//        почитаєш вова скільки там халявно квота з введеною карточкою, якщо ні  то заюзаємо openstreetmap

//        url = "http://api.openweathermap.org/data/2.5/weather?lat=49&lon=23&appid=aa9129410495346cb551bf6e9e3aa5fe";
