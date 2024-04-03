package com.example.jetpackcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackcompose.ui.theme.JetPackComposeTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class SplashActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    setUpNavHost(navController = navController)
                }
            }
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun setUpNavHost(navController: NavHostController){
    NavHost(navController = navController, startDestination = "splash" ){
        composable("splash"){
            SplashScreen(navController)
        }
        composable("main/{name}/{userId}/{timestamp}" ,
            arguments = listOf (
                navArgument("name"){
                    type = NavType.StringType
                },
                navArgument("userId"){
                    type = NavType.IntType
                },
                navArgument("timestamp"){
                    type = NavType.LongType
                }
            )
        ){
            var name = it.arguments?.getString("name")
            var userId = it.arguments?.getInt("userId")
            var timestamp = it.arguments?.getLong("timestamp")

            MainScreen(navController,name,userId,timestamp)

        }
        composable("post/{showOnlyPostByUser}",
            arguments = listOf(
                navArgument("showOnlyPostByUser"){
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ){
            val showOnlyPostByUser = it.arguments?.getBoolean("showOnlyPostByUser") ?: false
            PostScreen(showOnlyPostByUser)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "Jetpack Compose",
        )
        Button(modifier = Modifier.size(height = 60.dp, width = 140.dp), onClick = { navController.navigate("main/tushar/123/15478649")  }, shape = MaterialTheme.shapes.medium) {
            Text(text = "Go to Main ")
        }
    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(navController: NavController, name:String?, userId:Int?, timestamp: Long?){
    val user = remember {
        User(
            name = name!!,
            userId = userId!!,
            timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp!!), ZoneId.systemDefault())
        )
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "$user , welcome to Login Screen",
        )
        Button(modifier = Modifier.size(height = 60.dp, width = 140.dp), onClick = { navController.navigate("post/true")  }, shape = MaterialTheme.shapes.medium) {
            Text(text = "Go to Post ")
        }
    }
}

@Composable
fun PostScreen(showOnlyPostByUser : Boolean){
    Box(modifier = Modifier.fillMaxSize()){
        Text(text = "post screenn ${showOnlyPostByUser}")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetPackComposeTheme {
        Surface {
            SplashScreen(navController = rememberNavController())
        }
    }
}