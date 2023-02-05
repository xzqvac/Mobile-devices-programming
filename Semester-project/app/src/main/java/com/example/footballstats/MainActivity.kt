package com.example.footballstats

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.footballstats.theme.footballStatsTheme
import com.example.footballstats.viewmodel.liveFixtures.LiveFixturesModel
import com.example.footballstats.viewmodel.state.FixturesState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            footballStatsTheme {
                Column(modifier = Modifier.padding(10.dp)) {
                    TopAppBar()
                    FetchData()
                }
            }
        }
    }
}

@Composable
fun FetchData(liveFixturesModel: LiveFixturesModel = viewModel()) {
    Column {
        when(val state = liveFixturesModel.liveFixturesState.collectAsState().value) {
            is FixturesState.Empty -> Text(text = "No data avaliabe")
            is FixturesState.Loading -> Text(text = "Loading data")
            is FixturesState.Success -> Text(text = "Success")
            is FixturesState.Error -> Text(text = state.message)
        }
    }
}

@Composable
fun TopAppBar(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh Icon")
        }

        Text(text = "FootballStats", style = MaterialTheme.typography.h4)

        IconButton(onClick = { /*TODO*/ }){
            Icon(imageVector = Icons.Default.Star, contentDescription = "Favourite Icon")
        }
    }
}
