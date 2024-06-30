package com.example.uasmobile.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.uasmobile.R
import com.example.uasmobile.ui.theme.UASMobileTheme

@Composable
fun StartPages(navController: NavController) {
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        PortraitLayout(navController)
    } else {
        LandscapeLayout(navController)
    }
}

@Composable
fun PortraitLayout(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        Image(
            painter = painterResource(R.drawable.img_1),
            contentDescription = "Start Up Pages",
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(250.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign Up", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 33.sp))
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Look for list of cats and sign up")
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    navController.navigate("signup")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Sign Up")
            }
            Button(
                onClick = {
                    navController.navigate("login")
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(text = "Login")
            }
        }
    }
}

@Composable
fun LandscapeLayout(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(R.drawable.img_1),
            contentDescription = "Start Up Pages",
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(50.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Sign Up", style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 33.sp))
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Look for list of cats and sign up")
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate("signup")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Sign Up")
                }
                Button(
                    onClick = {
                        navController.navigate("login")
                    },
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Login")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStartPages() {
    UASMobileTheme {
        PortraitLayout(navController = rememberNavController())
    }
}

@Preview(showBackground = true, widthDp = 700, heightDp = 300)
@Composable
fun PreviewStartPagesLandscape() {
    UASMobileTheme {
        LandscapeLayout(navController = rememberNavController())
    }
}
