package com.example.assignment2

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LazyColumnExample() {
    val list:List<Users> = getUserList()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp)
    ) {

        item {
            Spacer(modifier = Modifier.run { height(30.dp) })
        }
        item {  LazyListHeader(
            modifier = Modifier.fillMaxWidth()
                .border(5.dp,  Color.Black)
                .background(Color.LightGray),
            item = "Users",

        )}

        items(list) { item ->
            LazyListItem(
                modifier = Modifier.fillMaxWidth()
                    .clip(RoundedCornerShape(18.dp))
                    .border(3.dp,  Color.Black, shape = RoundedCornerShape(18.dp))
                    .background(Color.LightGray),
                item = item,

            )
        }
    }
}


@Composable
fun LazyListItem(
    modifier: Modifier = Modifier,
    item: Users,
) {
    Log.d("TAG", "Printing $item")

    Column(
        modifier = modifier
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(.5f),
                text = item.userId,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.weight(.5f),
                text = item.fullName,
                style = MaterialTheme.typography.headlineMedium
            )
        }


        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(.5f),
                text = item.userName,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                modifier = Modifier.weight(.5f),
                text = item.email,
                style = MaterialTheme.typography.headlineMedium
            )
        }

        Text(
            modifier = Modifier
                .border(4.dp,  Color.Black, shape = CircleShape).padding(5.dp), textAlign = TextAlign.Center,
            text = " ${item.count} ", fontSize = 30.sp,  fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun LazyListHeader(
    modifier: Modifier = Modifier,
    item: String,
) {
    Log.d("TAG", "Printing $item")

        Row(
            modifier = modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item,
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 30.sp, fontWeight = FontWeight.Bold
            )
        }

}

@Preview
@Composable
private fun LazyColumnExamplePreview() {
    LazyColumnExample()
}

