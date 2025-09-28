package com.ag.shoping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ag.shoping.network.model.AllProductsResponse
import com.ag.shoping.repository.ExampleRepository
import com.ag.shoping.ui.theme.ShoingCartTheme

class MainActivity : ComponentActivity() {

    private val viewModel: ExampleRepository by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getProductList()

        enableEdgeToEdge()
        setContent {
            ShoingCartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    var apiResponseText by remember {
                        mutableStateOf<AllProductsResponse?>(
                            null
                        )
                    }
                    viewModel.productList.observeAsState().value?.let {
                        print("Anish ${it.products[0]}")
                        apiResponseText = it
                    }

                    Greeting(
                        data = apiResponseText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(data: AllProductsResponse?, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        userScrollEnabled = true,
        content = {
            val list = data?.products ?: emptyList()
            items(list) {
                Column(Modifier.padding(start=10.dp, bottom = 5.dp)) {
                    Text(
                        modifier = Modifier.padding(start = 5.dp, top= 2.dp), text = it.title, style = TextStyle(
                            fontSize = 22.sp,
                            fontWeight = Bold
                        )
                    )
                    Text(text = it.description)
                    Text(
                        text = "Rs ${it.price}", Modifier.padding(start = 5.dp), style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = Bold
                        )
                    )
                }
                HorizontalDivider(Modifier.fillMaxWidth()
                    .height(2.dp)
                    .background(Color.Black))
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoingCartTheme {
        //  Greeting("Android")
    }
}
