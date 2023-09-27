package com.lbg.project.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lbg.project.R
import com.lbg.project.utils.Constants
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage


class FullImageActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Calling the composable function
            // to display element and its contents
            MainContent2()
        }
    }

    // function to display Top Bar
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainContent2() {
        // on below line we are specifying theme as scaffold.
        Scaffold( // in scaffold we are specifying top bar.
            topBar = {
                // inside top bar we are specifying background color.
                TopAppBar(
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = colorResource(R.color.colorPrimary),
                        titleContentColor = Color(R.color.colorPrimary),
                        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                        actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                    ),
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigation icon"
                            )
                        }
                    },
                    // along with that we are specifying title for our top bar.
                    title = {
                        // in the top bar we are specifying tile as a text
                        Text(
                            // on below line we are specifying
                            // text to display in top app bar.
                            text = "Image From URL",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            // on below line we are specifying
                            // modifier to fill max width.
                            modifier = Modifier.fillMaxWidth(),
                            // on below line we are
                            // specifying text alignment.
                            textAlign = TextAlign.Center,
                            // on below line we are
                            // specifying color for our text.
                            color = Color.White
                        )
                    }
                )
            },
            content = { MyContent2() }
        )
    }

    // Creating a composable function to
// create two Images and a spacer between them
// Calling this function as content in the above function
    @Composable
    fun MyContent2() {
        // on below line we are creating a column,
        Column(
            modifier = Modifier
                .fillMaxSize()
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            intent.getStringExtra(Constants.URL)?.let {
                GlideImage(
                    imageModel = it,
                    modifier = Modifier
                        .wrapContentSize()
                        .wrapContentHeight()
                        .fillMaxWidth(),
                    // shows a progress indicator when loading an image.
                    contentScale = ContentScale.Crop,
                    circularReveal = CircularReveal(duration = 100),
                    shimmerParams = ShimmerParams(
                        baseColor = MaterialTheme.colorScheme.background,
                        highlightColor = Gray,
                        durationMillis = 500,
                        dropOff = 0.55f,
                        tilt = 20f
                    )
                )
            }
        }
    }


    // For displaying preview in
// the Android Studio IDE emulator
    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview2() {
        MainContent2()
    }
}
