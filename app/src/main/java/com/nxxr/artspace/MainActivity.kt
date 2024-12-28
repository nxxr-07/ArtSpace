package com.nxxr.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nxxr.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceLayout(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    var currImageId by remember {
        mutableStateOf(1)
    }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ArtImage(currImageId)
        Spacer(modifier = Modifier.height(16.dp))
        Column {
            ArtTitle(currImageId)
            ArtArtist(currImageId)
        }

        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    currImageId = (currImageId % 5) - 1
                }
            ) {
                Text(text = "Previous")
            }
            Button(
                modifier = Modifier.width(120.dp),
                onClick = {
                    currImageId = (currImageId % 5) + 1
                }
            ) {
                Text(text = "Next")
            }

        }
    }
}

@Composable
fun ArtImage(
    image: Int = 1
) {
    var imageRes = R.drawable.the_fall
    when (image) {
        1 -> imageRes = R.drawable.the_fall
        2 -> imageRes = R.drawable.the_girl_with_a_pearl
        3 -> imageRes = R.drawable.starry_night
        4 -> imageRes = R.drawable.the_napoleon
        5 -> imageRes = R.drawable.the_wanderer
    }
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = "The Fall",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(9f / 16f)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, Color.Gray, RoundedCornerShape(16.dp))
    )
}

@Composable
fun ArtTitle(
    image: Int = 1
) {
    var title = "The Fall"
    when (image) {
        1 -> title = "The Fall"
        2 -> title = "The Girl with a Pearl Earring"
        3 -> title = "Starry Night"
        4 -> title = "The Napoleon"
        5 -> title = "The Wanderer"
    }
    Text(
        text = title,
        modifier = Modifier.padding(bottom = 4.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun ArtArtist(
    image: Int = 1
) {
    var artist = "Peter Paul Rubens"
    when (image) {
        1 -> artist = "Peter Paul Rubens"
        2 -> artist = "Johannes Vermeer"
        3 -> artist = "Vincent van Gogh"
        4 -> artist = "Jacques-Louis David"
        5 -> artist = " Caspar David Friedrich"
    }
    Text(
        text = artist,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Gray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}