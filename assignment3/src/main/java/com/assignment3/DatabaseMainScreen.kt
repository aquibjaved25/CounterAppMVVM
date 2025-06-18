package com.assignment3

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.assignment3.model.User
import com.assignment3.nav.NavDestination
import com.assignment3.vm.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatabaseMainScreen(
    navController: NavController,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val users by userViewModel.usersFlow.collectAsState()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp)
                    .border(4.dp, Color.Black, shape = RectangleShape)
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                text = "Users ",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
            if (users.isEmpty()) {
                NoUsersView(
                    modifier = Modifier.weight(1f),
                    onAddUserClick = {
                        userViewModel.addUser {
                            Toast
                                .makeText(context, "User added successfully", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                )
            } else {
                userViewModel.setIValue(users)
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    UsersRowList(
                        modifier = Modifier.fillMaxWidth(),
                        users = users,
                        navController = navController
                    )
                    UsersList(
                        modifier = Modifier.weight(1f),
                        users = users,
                        navController = navController
                    )

                    Button(
                        onClick = {
                            userViewModel.addUser {
                                Toast
                                    .makeText(
                                        context,
                                        "User added successfully",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                    ) {
                        Text("Add Users")
                    }
                }
            }
        }


    }

}


@Composable
private fun UsersRowList(
    modifier: Modifier = Modifier,
    users: List<User>,
    navController: NavController
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(users) { user ->
            UserRow(
                modifier = Modifier.fillMaxWidth(),
                user = user,
                navController = navController
            )
        }
    }
}

@Composable
private fun UserRow(
    modifier: Modifier = Modifier,
    user: User,
    navController: NavController
) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .clickable{
                navController.navigate(NavDestination.TypeSafeDestination(user.fullName,user.userid,user.email,user.about))
            }

    ) {
        AsyncImage(
            modifier = Modifier
                .size(width = 80.dp, height = 80.dp)
                .clip(CircleShape)
                .border(3.dp, Color.Black, shape = CircleShape),
            model = "https://picsum.photos/id/${user.userid}/720/720",
            contentDescription = user.fullName,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun UsersList(
    modifier: Modifier = Modifier,
    users: List<User>,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(users) { user ->
            UserItem(
                modifier = Modifier.fillMaxWidth(),
                user = user,
                navController = navController
            )
        }
    }
}

@Composable
private fun UserItem(
    modifier: Modifier = Modifier,
    user: User,
    navController: NavController
) {
    Card(modifier = modifier.clickable{

        navController.navigate(NavDestination.TypeSafeDestination(user.fullName,user.userid,user.email,user.about))

    }) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(width = 80.dp, height = 80.dp)
                    .clip(CircleShape)
                    .border(3.dp, Color.Black, shape = CircleShape),
                model = "https://picsum.photos/id/${user.userid}/720/720",
                contentDescription = user.fullName,
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start

            ) {
                Text(
                    text = "ID: ${user.userid}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Name: ${user.fullName}",
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = "Email: ${user.email}",
                    style = MaterialTheme.typography.titleSmall
                )


            }
        }
    }
}


@Composable
fun NoUsersView(
    modifier: Modifier = Modifier,
    onAddUserClick: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Button(onClick = onAddUserClick) {
                Text("Add Users")
            }
        }
    }
}
