package edu.uoc.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import edu.uoc.android.models.Museums
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class Activity_Museums : AppCompatActivity() {

    lateinit var museumService: MuseumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_museums)
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://do.diba.cat")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        museumService = retrofit.create<MuseumService>(MuseumService::class.java)

        museums()


    }

    fun museums() {
        museumService.museums("1","5").enqueue(object : Callback<Museums>{
            override fun onResponse(call: Call<Museums>, response: Response<Museums>){
                if (response.code() == 200) {
                    //
                    // showProgress( false );
                    //
                    //
                    val museums = response.body()!!
                    // Adapter <<− museums // elements
                    val elemento = museums.getElements()
                    for (oneElement in elemento) {
                        Log.d("Etiqueta", oneElement.adrecaNom)
                    }
                }
            }

            override fun onFailure(call: Call<Museums>, t: Throwable) { // Log.d(TAG, ””xxxxx”)
                Log.d("Etiqueta","Fallo en llamada")
            }
        })
    }

}
