package tr.xip.widget.simpleratingview.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import tr.xip.widget.simpleratingview.SimpleRatingView;


public class MainActivity extends ActionBarActivity {

    TextView mRatingText;
    SimpleRatingView mSimpleRatingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRatingText = (TextView) findViewById(R.id.rating_text);

        mSimpleRatingView = (SimpleRatingView) findViewById(R.id.simple_rating_view);
        mSimpleRatingView.setOnRatingChangedListener(new SimpleRatingView.OnRatingChangeListener() {
            @Override
            public void onRatingChanged(SimpleRatingView.Rating ratingType) {
                switch (ratingType) {
                    case POSITIVE:
                        mRatingText.setText(R.string.srv_rating_positive);
                        break;
                    case NEUTRAL:
                        mRatingText.setText(R.string.srv_rating_neutral);
                        break;
                    case NEGATIVE:
                        mRatingText.setText(R.string.srv_rating_negative);
                        break;
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
