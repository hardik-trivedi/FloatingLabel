package com.hardik.floatinglabel;

import android.view.View;

/**
 * An interface which is serverd as a callback event listeners for
 * FanragFloatingLabelView.java
 * 
 * @author Hardik Trivedi
 * 
 */
public interface OnFloatingLableFocusChangeListener {

	public void onFocusChange(View v, boolean hasFocus);
}
