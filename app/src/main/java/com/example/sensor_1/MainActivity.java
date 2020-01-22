package com.example.sensor_1;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//proximity sensor gives distance between closest object
public class MainActivity extends AppCompatActivity implements SensorEventListener{

    Button b;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager sm;
        sm=(SensorManager) this.getSystemService(SENSOR_SERVICE);
        Sensor proxy;
        proxy=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);                    //making obj of sensor
        sm.registerListener(this,proxy,sm.SENSOR_DELAY_NORMAL);

        b=findViewById(R.id.plsy);


        mediaPlayer=MediaPlayer.create(this,R.raw.terimitti);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mediaPlayer.start();

            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        Sensor sensor=sensorEvent.sensor;

        if(sensor.getType()==Sensor.TYPE_PROXIMITY){
            if(sensorEvent.values[0]==0){
                mediaPlayer.stop();

                mediaPlayer=MediaPlayer.create(this,R.raw.terimitti);

            }
            else if(sensorEvent.values[0]<5 && sensorEvent.values[0]!=0 )
            {
                mediaPlayer.pause();
                mediaPlayer=MediaPlayer.create(this,R.raw.terimitti);
            }
            else mediaPlayer.start();


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {


    }
}
