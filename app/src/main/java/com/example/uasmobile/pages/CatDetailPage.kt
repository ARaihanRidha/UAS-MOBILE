package com.example.uasmobile.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.uasmobile.data.Cat

@Composable
fun CatDetailPage(item: Cat, catId: String, navController: NavController) {
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        PortraitCatDetailPage(item)
    } else {
        LandscapeCatDetailPage(item)
    }
}

@Composable
fun PortraitCatDetailPage(item: Cat) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Cat Details",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 33.sp),
            modifier = Modifier.padding(16.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                val painter = rememberAsyncImagePainter(model = item.url)
                Image(
                    painter = painter,
                    contentDescription = item.id,
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    contentScale = ContentScale.Crop
                )
                CatDetails(item)
            }
        }
    }
}

@Composable
fun LandscapeCatDetailPage(item: Cat) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        val painter = rememberAsyncImagePainter(model = item.url)
        Image(
            painter = painter,
            contentDescription = item.id,
            modifier = Modifier
                .height(240.dp)
                .fillMaxHeight()
                .padding(end = 8.dp),
            contentScale = ContentScale.Crop
        )
        Card(
            modifier = Modifier
                .fillMaxHeight()
                .background(color = Color.White),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                CatDetails(item)
            }
        }
    }
}

@Composable
fun CatDetails(item: Cat) {
    Column(modifier = Modifier.padding(16.dp)) {
        item.breeds.forEach { breed ->
            Text(
                text = "Breed: ${breed.name}",
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Origin: ${breed.origin}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Lifespan: ${breed.life_span}",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Description: ${breed.description}",
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            breed.wikipedia_url?.let { url ->
                Text(
                    text = "More Info: $url",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
            }
        }
    }
}
