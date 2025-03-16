package com.example.weblinkandphonecallapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weblinkandphonecallapp.ui.theme.WebLinkAndPhoneCallAppTheme
import androidx.compose.foundation.background
import androidx.compose.ui.res.colorResource
import androidx.compose.foundation.background



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebLinkAndPhoneCallAppTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {

    val context = LocalContext.current // Obtain the context
    // Access colors from resources
    val backgroundColor = colorResource(id = R.color.background)
    val yellowButtonColor = colorResource(id = R.color.yellow)
    val redButtonColor = colorResource(id = R.color.red)
    val blackButtonColor = colorResource(id = R.color.black)
    val whiteColor =  colorResource(id = R.color.white)

    Scaffold(modifier = Modifier
                        .fillMaxSize()
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)  // Apply the background color here
        )
        var url by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("")
        }

        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_implicit_intents),
                    contentDescription = "App Icon",
                    modifier = Modifier.size(40.dp) // Adjust size as needed
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    "Implicit Intents",
                    fontSize = 24.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = whiteColor
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("URL", color = whiteColor) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { launchUrl(context, url) },
                colors = ButtonDefaults.buttonColors(containerColor = yellowButtonColor)
            )
            {
                Text("Launch URL",color = blackButtonColor)
            }
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = whiteColor) },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = { dialPhoneNumber(context, phoneNumber) },
                colors = ButtonDefaults.buttonColors(containerColor = redButtonColor)

            ) {
                Text("Ring Phone" ,color = blackButtonColor)
            }
            Button(onClick = { closeApp(context) },
                colors = ButtonDefaults.buttonColors(containerColor = blackButtonColor)
                ) {
                Text("Close App", color = whiteColor)
            }
        }
    }
}

fun launchUrl(context: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(intent)
}

fun dialPhoneNumber(context: Context, phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL)
    intent.data = Uri.parse("tel:$phoneNumber")
    context.startActivity(intent)
}

fun closeApp(context: Context) {
    if (context is Activity) {
        context.finish()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    WebLinkAndPhoneCallAppTheme {
        MainScreen()
    }
}
