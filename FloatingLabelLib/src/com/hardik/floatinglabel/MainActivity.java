package com.hardik.floatinglabel;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	FloatingLabelView label1, label2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		label1 = (FloatingLabelView) findViewById(R.id.label1);
		// label2 = (FloatingLabelView) findViewById(R.id.label2);

	}

}
