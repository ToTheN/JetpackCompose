package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTheme {
                Surface(modifier = Modifier
                    .fillMaxSize()) {
                    //uiStart(Msg("Monika ","Aur SainiSaab. Khana kha liya?"))
                    conversationStart(sampleData.simpleConversationData)
                }
            }
        }
    }
}

@Composable
fun conversationStart(messageList : List<Msg>){
    Box (modifier = Modifier.fillMaxSize()){
        Image(painter = painterResource(id = R.drawable.santa_claus), contentDescription = "santa clouse",
            modifier = Modifier.fillMaxSize().alpha(0.4f))
    }
    LazyColumn {
        items(messageList) {message ->
            uiStart(msg = message)
        }

    }
}

@Composable
fun uiStart(msg : Msg){
    Row(modifier = Modifier.padding(all = 8.dp )) {
        if(msg.person == "Monika") {
            Image(
                painter = painterResource(id = R.drawable.video_calling),
                contentDescription = "message icon",
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
                    .border(1.0.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
            )
        }else if(msg.person == "Tushar"){
            Image(
                painter = painterResource(id = R.drawable.panda_bear),
                contentDescription = "message icon",
                modifier = Modifier
                    .size(23.dp)
                    .clip(CircleShape)
                    .border(1.0.dp, MaterialTheme.colorScheme.tertiary, CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(text = msg.person,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(5.dp))
            Surface (shape = MaterialTheme.shapes.medium,
                shadowElevation = 4.dp,
                border = BorderStroke(0.3.dp, Color.Black)){
                Text(text = msg.chat,
                    modifier = Modifier.padding(all = 6.dp),
                    style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}

@Preview
@Composable
fun previewUi(){
    JetpackComposeTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            uiStart(Msg("Monika ","Aur SainiSaab. Khana kha liya?"))
        }
    }
}

data class Msg(
   val person : String , val chat: String
)