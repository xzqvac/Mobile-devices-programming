package com.example.footballstats

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.mvvmlivescorestarter.ui.theme.Green900
import com.example.footballstats.data.remote.models.Data
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
fun LiveFixtures(liveFixtures: List<Data>) {

    Column(modifier = Modifier.padding(15.dp)) {
        Text(
            text = "Live Matches",
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(top = 12.dp)
        )
        if (liveFixtures.isEmpty()) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "No live matches")
                Text(
                    text = "No live matches currently",
                    style = MaterialTheme.typography.h6
                )
            }
        }
        else {
            LazyRow(
                modifier = Modifier.padding(top = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(liveFixtures.size) {
                    LiveFixtureItem(match = liveFixtures[it])
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LiveFixtureItem(match: Data) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier
            .width(300.dp)
            .height(150.dp),
        elevation = 0.dp
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = match.group.groupName,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.h5
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val homeScore = match.stats.homeScore
                val awayScore = match.stats.awayScore

                Text(
                    text = match.homeTeam.commonName,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = "$homeScore:$awayScore",
                    style = MaterialTheme.typography.h5
                )
                Text(
                    text = match.awayTeam.commonName,
                    style = MaterialTheme.typography.h6
                )
            }
            Chip(
                onClick = { /*TODO*/ },
                colors = ChipDefaults.chipColors(
                    contentColor = Color.White,
                    backgroundColor = Green900
                ),
                modifier = Modifier
                    .align(
                        Alignment.CenterHorizontally
                    )
                    .padding(top = 20.dp)
            ) {
                Text(matchStatus(match))
            }
        }
    }
}

fun matchStatus(match: Data): String {
    return when (match.statusCode) {
        1 -> "${match.minute} '"
        11 -> "half time"
        0 -> "not started"
        3 -> "finished"
        5 -> "cancelled"
        4 -> "postponed"
        17 -> "to be announced"
        12 -> "extra time"
        13 -> "penalties"
        else -> "-"
    }
}

@Composable
fun FetchData(liveFixturesModel: LiveFixturesModel = viewModel()) {
    Column {
        when (val state = liveFixturesModel.liveFixturesState.collectAsState().value) {
            is FixturesState.Empty -> Text(text = "No data avaliabe")
            is FixturesState.Loading -> Text(text = "Loading data")
            is FixturesState.Success -> LiveFixtures(liveFixtures = state.data)
            is FixturesState.Error -> Text(text = state.message)
        }
    }
}

@Composable
fun TopAppBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh Icon")
        }

        Text(text = "FootballStats", style = MaterialTheme.typography.h4)

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Star, contentDescription = "Favourite Icon")
        }
    }
}
