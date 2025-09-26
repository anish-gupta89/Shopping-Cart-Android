package com.ag.shoping

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ag.shoping.network.model.ExampleRequest
import com.ag.shoping.network.model.ExampleResponse
import com.ag.shoping.repository.ExampleRepository
import com.ag.shoping.ui.theme.ShoingCartTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
// import retrofit2.Response // No longer needed here for the API call return type

class MainActivity : ComponentActivity() {
    private val TAG = "MainActivity"
    private val exampleRepository = ExampleRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoingCartTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var apiResponseText by remember { mutableStateOf("Loading...") }

                    LaunchedEffect(key1 = Unit) {
                        launch(Dispatchers.IO) { // Use IO dispatcher for network calls
                            try {
                                val request = ExampleRequest(id = 1, data = "Sample API call data")
                                // Call API through the repository, which now returns ExampleResponse directly
                                val exampleResponse: ExampleResponse = exampleRepository.postExampleData(request)

                                withContext(Dispatchers.Main) { // Switch to Main dispatcher to update UI
                                    apiResponseText = "API call successful: ${exampleResponse.status} - ${exampleResponse.message}"
                                    Log.i(TAG, "API Response: $exampleResponse")
                                }
                            } catch (e: Exception) {
                                // Handle exceptions from network call or non-2xx HTTP responses
                                Log.e(TAG, "API Exception or Error: ", e)
                                withContext(Dispatchers.Main) {
                                    apiResponseText = "API call failed: ${e.message}"
                                }
                            }
                        }
                    }

                    Greeting(
                        name = apiResponseText,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name, // Displaying the API response or status
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ShoingCartTheme {
        Greeting("Android")
    }
}
