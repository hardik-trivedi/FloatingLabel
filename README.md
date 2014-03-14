FloatingLabel
=============

FloatingLabel Allows you to create a blow kind of EditText.


![Animation Example](/form-animation-_gif_-1.gif)


Floating Lable project is in initial mode, where a few customization is provided like changing Text color of EditText and Its Lable


```java
<com.hardik.floatinglabel.FloatingLabelView
        android:id="@+id/label1"
        android:layout_width="fill_parent"
        android:layout_height="wrap_content"
        label:floatHintText="Whatever hint you want to display"
        label:textColor="@color/blue"
        label:textTypeface="some_custome_font.ttf"/>
```
##Features 


###Float Hint Text
* Specifies the text for float hint label
```Android
	floatview:floatHintText="@string/app_name"
```
###Float Hint Text Color
* Specifies text color for float hint label both for focused mode and unfocused mode
```Android
	floatview:floatHintTextColorFocused="@color/blue"
        floatview:floatHintTextColorUnFocused="@color/green"
```
###Float Hint Text Style
* Specifies the text style, bold,normal,italic
```Android
	floatview:floatHintTextStyle="bold"
```
###Float Hint Text Gravity
* Specifies gravity of text left,right,top,bottom
```Android
	floatview:floatHintTextGravity="right"
```
###Float Hint Text Size
* Specifies text size
```Android
	floatview:floatHintTextSize="20"
```
###Float Hint Text Custom Typeface
* Specifies custom font. (It has to be in to the assets folder e.g. /assets/some_font.ttf)
```Android
	floatview:floatHintTextTypeface="Ithornet.ttf"
```
###Float Hint Text Background
* Specifies custom background. You can specify both color and drawable.
```Android
	floatview:floatHintTextBackground="@drawable/label_bg"
```
###Actual Text
###Actual Text Color
###Actual Text Style
###Actual Text Gravity
###Actual Text Size
###Actual Text Custom Typeface
###Actual Text Background
###Actual Text As Password



