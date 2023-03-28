package com.example.lektion_13_sideeffect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lektion_13_sideeffect.ui.theme.Lektion_13_SideEffectTheme
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay
import java.util.concurrent.Delayed

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lektion_13_SideEffectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TestLaunchEffect()
                }
            }
        }
    }
}

@Composable
fun TestLaunchEffect() {

    var counterValue by remember {
        mutableStateOf(0)
    }
    /*
        SideEffect {
            println("RECOMPOSING")
        }
     */

    LaunchedEffect(false) {
        delay(1000L)
        println("Value Has Changed")
    }

    Column {

        Text(text = counterValue.toString())
        Button(onClick = { counterValue++ }) {
            Text(text = " + ")
        }

    }

}

@Composable
fun TestSideEffect() {
    val parentCounter = remember { mutableStateOf(0) }
    val childCounter = remember { mutableStateOf(0) }

    SideEffect {
        // Each Time Composable Recomposes
        // DO NOT UPDATE UI HERE
        println("Did recompose?")
    }

    Column {
        Text(text = "Parent counter: ${parentCounter.value}")
        ChildScreen(childCounter.value)
    }
}

@Composable
fun ChildScreen(value: Int) {

    Column() {
        Text(text = value.toString())
    }

}





@Composable
fun ParentComposable() {

    var counterValue by rememberSaveable {
        mutableStateOf(0)
    }

    // Retrofit Request //  100 request
    // Database Request //  100 DB Query

    Column {

        Text("Hello world $counterValue")

        Button(onClick = { counterValue++ }) {
            Text(text = " + ")
        }
    }
}