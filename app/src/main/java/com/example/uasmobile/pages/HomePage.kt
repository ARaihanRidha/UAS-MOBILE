package com.example.uasmobile.pages

import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.uasmobile.AuthViewModel
import com.example.uasmobile.data.Cat
import com.example.uasmobile.viewModel.CatViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.uasmobile.ui.theme.UASMobileTheme
import com.google.gson.Gson

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel,
    catViewModel: CatViewModel = viewModel()
) {
    val configuration = LocalConfiguration.current
    var searchQuery by remember { mutableStateOf("") }
    val catList by catViewModel.catList.collectAsState()

    val filteredList = if (searchQuery.isEmpty()) {
        catList
    } else {
        catList.filter { cat ->
            cat.breeds.any { breed ->
                breed.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    when (configuration.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> {
            PortraitHomePage(navController, authViewModel, searchQuery, onSearchQueryChange = { searchQuery = it }, filteredList)
        }
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeHomePage(navController, authViewModel, searchQuery, onSearchQueryChange = { searchQuery = it }, filteredList)
        }
    }
}

@Composable
fun PortraitHomePage(
    navController: NavController,
    authViewModel: AuthViewModel,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    catList: List<Cat>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Home Cats", fontSize = 32.sp)

        TextField(
            value = searchQuery,
            onValueChange = onSearchQueryChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text(text = "Search by breed...") }
        )

        LazyColumn {
            items(catList) { item ->
                CatItem(item = item, navController)
            }
        }

        TextButton(onClick = {
        }) {
            Text(text = "Sign Out")
        }
    }
}

@Composable
fun LandscapeHomePage(
    navController: NavController,
    authViewModel: AuthViewModel,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    catList: List<Cat>
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Home Cats", fontSize = 32.sp)

            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text(text = "Search by breed...") }
            )

            LazyColumn {
                items(catList) { item ->
                    CatItem(item = item, navController)
                }
            }

            TextButton(onClick = {
            }) {
                Text(text = "Sign Out")
            }
        }
    }
}

@Composable
fun CatItem(item: Cat, navController: NavController) {
    val gson = Gson()
    val catJson = gson.toJson(item)
    val configuration = LocalConfiguration.current

    if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
        PortraitCatItem(item, catJson, navController)
    } else {
        LandscapeCatItem(item, catJson, navController)
    }
}

@Composable
fun PortraitCatItem(item: Cat, catJson: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(300.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("catDetail/${Uri.encode(catJson)}")
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.url),
                contentDescription = item.id,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(3f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (item.breeds.isEmpty()) {
                    Text(
                        text = "Breed: No Data",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                } else {
                    item.breeds.forEach { breed ->
                        Text(
                            text = breed.name,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LandscapeCatItem(item: Cat, catJson: String, navController: NavController) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(200.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navController.navigate("catDetail/${Uri.encode(catJson)}")
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = item.url),
                contentDescription = item.id,
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                if (item.breeds.isEmpty()) {
                    Text(
                        text = "Breed: No Data",
                        style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    )
                } else {
                    item.breeds.forEach { breed ->
                        Text(
                            text = breed.name,
                            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomePage() {
    UASMobileTheme {
        HomePage(navController = rememberNavController(), authViewModel = viewModel())
    }
}
