package com.kay.simpletaxapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kay.simpletaxapp.ui.screen.MainScreen
import com.kay.simpletaxapp.ui.theme.SimpleTaxAppTheme
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel

class MainActivity : ComponentActivity() {

    private val taxViewModel: TaxViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleTaxAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainScreen(taxViewModel = taxViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val taxViewModel = TaxViewModel()
    Surface(color = MaterialTheme.colors.background) {
        MainScreen(taxViewModel = taxViewModel)
    }
}
