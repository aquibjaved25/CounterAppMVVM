package com.assignment3

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.assignment3.model.User
import com.assignment3.nav.NavDestination
import com.assignment3.vm.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(navController: NavController,
                  destination: NavDestination.TypeSafeDestination,
                  userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null
                        )
                    }
                },
                title = { Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp)
                        .border(4.dp, Color.Black, shape = RectangleShape)
                        .padding(5.dp),
                    textAlign = TextAlign.Center,
                    text = "User ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                ) })
        }
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp,)
                .clip(RoundedCornerShape(18.dp))
                .border(3.dp, Color.Black, shape = RoundedCornerShape(18.dp))
        ) {
            AsyncImage(
                modifier = Modifier.wrapContentWidth().height(200.dp).padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .clip(RectangleShape)
                    .border(3.dp, Color.Black, shape = RectangleShape),
                model = "https://picsum.photos/id/${destination.userId}/720/720",
                contentDescription = destination.userName,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp,), text = "UserId: ${destination.userId}")
            Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp,),text = "Email: ${destination.email}")
            Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp,),text = "Full name: ${destination.userName}")
            Text(modifier = Modifier.padding(start = 20.dp, end = 20.dp,),text = "About: ${destination.about}")
            Spacer(modifier = Modifier.height(20.dp))
        }
            Button( modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                onClick = {
                    userViewModel.deleteUser(User(destination.userId,destination.email,destination.userName,destination.about)) {
                        Toast
                            .makeText(
                                context,
                                "User Deleted successfully",
                                Toast.LENGTH_SHORT
                            )
                            .show()
                    }
                }
            ) {
                Text("Delete User")
            }
    }
    }
}