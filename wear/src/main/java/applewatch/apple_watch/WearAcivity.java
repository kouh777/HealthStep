package applewatch.apple_watch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.LinearLayout;

public class WearAcivity extends Activity {

    private LinearLayout mLayout;
    private GameView    mGameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayout = new LinearLayout(this);
        mGameView = new GameView(this);
        mLayout.addView(mGameView);
        setContentView(mLayout);
    }
}
