package diana.soleil.eggtimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    Button button;
    TextView textView;
    MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button) findViewById(R.id.button);
        mediaPlayer = MediaPlayer.create(getApplication(), R.raw.horn);
        seekBar.setMax(30000);

        int seconds;
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
               int minutes = (int) Math.floor(i/60000);
               int seconds = (int) Math.floor((i%60000)/1000);

                if (seconds <10) {
                    textView.setText("0"+ String.valueOf(minutes) + ":0" + String.valueOf(seconds) );
                } else  {
                    textView.setText("0"+ String.valueOf(minutes) + ":" + String.valueOf(seconds) );
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                button.setVisibility(View.INVISIBLE);
                new CountDownTimer(seekBar.getProgress(), 1000) {
                    @Override
                    public void onTick(long l) {
                        seekBar.setProgress((int) l);
                    }

                    @Override
                    public void onFinish() {
                        button.setVisibility(View.VISIBLE);
                        mediaPlayer.start();

                    }
                }.start();
            }
        });

    }
}