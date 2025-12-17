package com.example.epreuvebaptiste.util

import android.content.Context
import android.widget.Toast

// valide le point : "Extension liée à une composante Android"
fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}