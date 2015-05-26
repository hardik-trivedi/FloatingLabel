package com.hardik.floatinglabel;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingLabelView extends LinearLayout implements
		OnFocusChangeListener {
	final String TAG = getClass().getSimpleName();
	private EditText input;
	private TextView display;
	private Context context;
	private LinearLayout.LayoutParams inpuTextParams;
	private LinearLayout.LayoutParams displayTextParams;
	private Animation bottomUp, bottomDown;
	private OnFloatingLableFocusChangeListener focusChangeListener;

	public FloatingLabelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	public FloatingLabelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		if (!isInEditMode()) {
			createLayout(attrs);
		}
	}

	public FloatingLabelView(Context context) {
		super(context);
		this.context = context;
		if (!isInEditMode()) {
			createLayout(null);
		}
	}

	private void createLayout(AttributeSet attrs) {
		input = new EditText(context);
		display = new TextView(context);
		input.setOnFocusChangeListener(this);
		bottomUp = AnimationUtils.loadAnimation(context, R.anim.txt_bottom_up);
		bottomDown = AnimationUtils.loadAnimation(context,
				R.anim.txt_bottom_down);
		// Create Default Layout
		inpuTextParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		displayTextParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		input.setLayoutParams(inpuTextParams);
		display.setLayoutParams(displayTextParams);
		setOrientation(LinearLayout.VERTICAL);
		createDefaultLayout();

		if (attrs != null) {
			createCustomLayout(attrs);
		}

		addView(display);
		addView(input);
		display.setVisibility(View.INVISIBLE);

		input.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				if (input.getText().toString().length() > 0
						&& display.getVisibility() == INVISIBLE) {
					showHint();
				} else if (input.getText().toString().length() == 0
						&& display.getVisibility() == VISIBLE) {
					hideHint();
				}
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
	}

	private void createDefaultLayout() {
		input.setGravity(Gravity.LEFT | Gravity.TOP);
		display.setGravity(Gravity.LEFT);

		display.setTextColor(Color.BLACK);
		input.setTextColor(Color.BLACK);

		input.setTextAppearance(context, android.R.attr.textAppearanceMedium);
		display.setTextAppearance(context, android.R.attr.textAppearanceSmall);

		display.setPadding(5, 2, 5, 2);
	}

	private void createCustomLayout(AttributeSet attrs) {

		TypedArray a = context.obtainStyledAttributes(attrs,
				R.styleable.FloatingLabel, 0, 0);
		// For Floating Hint

		String floatHintText = a
				.getString(R.styleable.FloatingLabel_floatHintText);
		ColorStateList floatHintTextColorFocused = a
				.getColorStateList(R.styleable.FloatingLabel_floatHintTextColorFocused);
		ColorStateList floatHintTextColorUnFocused = a
				.getColorStateList(R.styleable.FloatingLabel_floatHintTextColorUnFocused);
		int floatHintTextSize = a.getInt(
				R.styleable.FloatingLabel_floatHintTextSize, 15);
		String floatHintTextTypefaceName = a
				.getString(R.styleable.FloatingLabel_floatHintTextTypeface);
		int floatHintTextStyle = a.getInt(
				R.styleable.FloatingLabel_floatHintTextStyle, Typeface.NORMAL);
		int floatHintTextGravity = a.getInt(
				R.styleable.FloatingLabel_floatHintTextGravity, Gravity.LEFT);
		Drawable floatHintTextBackground = a
				.getDrawable(R.styleable.FloatingLabel_floatHintTextBackground);

		// For Actual Text
		String text = a.getString(R.styleable.FloatingLabel_text);
		ColorStateList textColor = a
				.getColorStateList(R.styleable.FloatingLabel_textColor);
		int textSize = a.getInt(R.styleable.FloatingLabel_textSize, 15);
		String textTypefaceName = a
				.getString(R.styleable.FloatingLabel_textTypeface);
		int textStyle = a.getInt(R.styleable.FloatingLabel_textStyle,
				Typeface.NORMAL);
		int textGravity = a.getInt(R.styleable.FloatingLabel_textGravity,
				Gravity.LEFT);

		Drawable textBackground = a
				.getDrawable(R.styleable.FloatingLabel_textBackground);
		boolean isPassword = a.getBoolean(R.styleable.FloatingLabel_isPassword,
				false);

		a.recycle();

		setFloatHintText(floatHintText);
		setFloatHintTextColor(getColorStateList(floatHintTextColorFocused,
				floatHintTextColorUnFocused));
		setFloatHintTextSize(floatHintTextSize);
		setFloatHintTypeFace(floatHintTextTypefaceName, floatHintTextStyle);
		setFloatHintGravity(floatHintTextGravity);
		setFloatHintTextBackGround(floatHintTextBackground);

		setTextHint(floatHintText);
		setTextColor(textColor);
		setTextSize(textSize);
		setTextTypeFace(textTypefaceName, textStyle);
		setTextGravity(textGravity);
		setText(text);
		setTextBackGround(textBackground);
		setPassword(isPassword);
	}

	private ColorStateList getColorStateList(ColorStateList focused,
			ColorStateList unfocused) {
		int[][] states = new int[][] {
				new int[] { android.R.attr.state_selected }, // selected
				new int[] {} // default
		};

		int[] colors = new int[] {
				(focused != null) ? focused.getDefaultColor() : Color.BLACK,
				(unfocused != null) ? unfocused.getDefaultColor() : Color.GRAY };

		return new ColorStateList(states, colors);
	}

	/** FOR LABEL **/
	private void setFloatHintGravity(int floatHintTextGravity) {
		display.setGravity(floatHintTextGravity);
	}

	private void setFloatHintTypeFace(String floatHintTextTypefaceName,
			int floatHintTextStyle) {
		try {
			Typeface face = Typeface.createFromAsset(context.getAssets(),
					floatHintTextTypefaceName);
			display.setTypeface(face);
		} catch (Exception e) {
			display.setTypeface(null, floatHintTextStyle);
		}
	}

	private void setFloatHintTextSize(int floatHintTextSize) {
		display.setTextSize(TypedValue.COMPLEX_UNIT_SP, floatHintTextSize);
	}

	private void setFloatHintTextColor(ColorStateList floatHintTextColor) {
		if (floatHintTextColor != null) {
			display.setTextColor(floatHintTextColor);
		}
	}

	public void setFloatHintText(String string) {
		if (string != null) {
			display.setText(string);
		}
	}

	private void setFloatHintTextBackGround(Drawable textBackground) {
		input.setBackgroundDrawable(textBackground);
	}

	/** FOR TEXT **/

	private void setPassword(boolean isPassword) {
		// TODO Auto-generated method stub
		if (isPassword) {
			input.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
		}
	}

	private void setTextBackGround(Drawable textBackground) {
		input.setBackgroundDrawable(textBackground);
	}

	private void setTextGravity(int textGravity) {
		input.setGravity(textGravity);
	}

	private void setTextTypeFace(String textTypefaceName, int textStyle) {
		try {
			Typeface face = Typeface.createFromAsset(context.getAssets(),
					textTypefaceName);
			input.setTypeface(face);
		} catch (Exception e) {
			input.setTypeface(null, textStyle);
		}
	}

	private void setTextSize(int textSize) {
		input.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
	}

	private void setTextColor(ColorStateList textColor) {
		if (textColor != null) {
			input.setTextColor(textColor);
		}
	}

	public void setText(String string) {
		if (string != null) {
			input.setText(string);
		}
	}

	public void setTextHint(String hintText) {
		if (hintText != null) {
			input.setHint(hintText);
		}
	}

	private void showHint() {
		if (display.getVisibility() != View.VISIBLE) {
			display.setVisibility(View.VISIBLE);
			display.startAnimation(bottomUp);
		}
	}

	private void hideHint() {
		if (display.getVisibility() != View.INVISIBLE) {
			display.setVisibility(View.INVISIBLE);
			display.startAnimation(bottomDown);
		}
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		display.setSelected(hasFocus);
		if (focusChangeListener != null) {
			focusChangeListener.onFocusChange(this, hasFocus);
		}
	}

	public String getText() {
		return input.getText().toString();
	}

	public EditText getEditText() {
		return input;
	}
}
