package com.example.linearlayout

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.hbb20.CountryCodePicker
import java.util.Calendar
import java.util.Locale


class MainActivity : AppCompatActivity() {
    private val myCalendar = Calendar.getInstance()
    private lateinit var datePicker: EditText
    private lateinit var autoCompleteTextView: AutoCompleteTextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        datePicker = findViewById(R.id.date_picker)
        datePicker.setOnClickListener {
            showDatePicker()
        }
        autoCompleteTextView = findViewById(R.id.editTextCountry)

        val countryCodes = Locale.getISOCountries()
        val countryNames = mutableListOf<String>()

        for (countryCode in countryCodes) {
            val locale = Locale("", countryCode)
            val countryName = locale.displayCountry
            countryNames.add(countryName)
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, countryNames)
        autoCompleteTextView.setAdapter(adapter)
    }
    private fun showDatePicker() {
        val datePickerDialog = DatePickerDialog(
            this,
            R.style.DatePickerStyle,
            { _, year, month, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, month)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateBirthdate()
            },
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.show()
    }

    private fun updateBirthdate() {
        val dateFormat = "dd/MM/yyyy"
        val sdf = java.text.SimpleDateFormat(dateFormat, Locale.getDefault())
        datePicker.setText(sdf.format(myCalendar.time))
    }
    }
