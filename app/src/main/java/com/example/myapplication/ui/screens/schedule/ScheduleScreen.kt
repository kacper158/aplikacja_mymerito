package com.example.myapplication.ui.screens.schedule

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Apartment
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.Sync
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

// Colors
private val DarkNavy = Color(0xFF0F172A)

private val WykladBg = Color(0xFFDBEAFE)
private val WykladText = Color(0xFF1E40AF)

private val LabBg = Color(0xFFDCFCE7)
private val LabText = Color(0xFF15803D)

private val CwiczeniaBg = Color(0xFFFFEDD5)
private val CwiczeniaText = Color(0xFFC2410C)

private val StatusGreen = Color(0xFF16A34A)

private data class DayItem(
    val dayName: String,
    val dayNumber: String
)

private data class ScheduleClassItem(
    val timeInterval: String,
    val label: String,
    val labelBg: Color,
    val labelText: Color,
    val title: String,
    val room: String,
    val instructor: String,
    val isAttendanceConfirmed: Boolean = false,
    val filterCategory: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    // Days list: Cz 17, Pt 18, Sb 19 (default active), Nd 20, Pn 21, Wt 22, Śr 23
    val days = remember {
        listOf(
            DayItem("Cz", "17"),
            DayItem("Pt", "18"),
            DayItem("Sb", "19"),
            DayItem("Nd", "20"),
            DayItem("Pn", "21"),
            DayItem("Wt", "22"),
            DayItem("Śr", "23")
        )
    }
    var selectedDayIndex by remember { mutableIntStateOf(2) } // Default: Sb 19

    // Filter Chips: "Wszystkie zajęcia", "Ćwiczenia / lab", "Wykłady"
    val filterOptions = listOf("Wszystkie zajęcia", "Ćwiczenia / lab", "Wykłady")
    var selectedFilter by remember { mutableStateOf("Wszystkie zajęcia") }

    // Schedule items
    val allScheduleItems = remember {
        listOf(
            ScheduleClassItem(
                timeInterval = "08:15 – 09:45",
                label = "Wykład",
                labelBg = WykladBg,
                labelText = WykladText,
                title = "Programowanie obiektowe",
                room = "Sala A-210",
                instructor = "dr hab. M. Kowalski",
                isAttendanceConfirmed = true,
                filterCategory = "Wykłady"
            ),
            ScheduleClassItem(
                timeInterval = "10:00 – 11:30",
                label = "Laboratorium",
                labelBg = LabBg,
                labelText = LabText,
                title = "Bazy danych",
                room = "B-104",
                instructor = "mgr A. Nowak",
                isAttendanceConfirmed = false,
                filterCategory = "Ćwiczenia / lab"
            ),
            ScheduleClassItem(
                timeInterval = "13:00 – 14:30",
                label = "Ćwiczenia",
                labelBg = CwiczeniaBg,
                labelText = CwiczeniaText,
                title = "Sieci komputerowe",
                room = "C-302",
                instructor = "dr P. Wiśniewski",
                isAttendanceConfirmed = false,
                filterCategory = "Ćwiczenia / lab"
            )
        )
    }

    val filteredItems = remember(selectedFilter, selectedDayIndex) {
        when (selectedFilter) {
            "Ćwiczenia / lab" -> allScheduleItems.filter { it.filterCategory == "Ćwiczenia / lab" }
            "Wykłady" -> allScheduleItems.filter { it.filterCategory == "Wykłady" }
            else -> allScheduleItems
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Plan zajęć",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Semestr zimowy 2024/25",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        floatingActionButton = {
            // Pływający przycisk dolny: Ciemnogranatowy przycisk "Synchronizuj kalendarz" z ikoną
            ExtendedFloatingActionButton(
                onClick = {
                    Toast.makeText(context, "Kalendarz został pomyślnie zsynchronizowany!", Toast.LENGTH_SHORT).show()
                },
                containerColor = DarkNavy,
                contentColor = Color.White,
                shape = RoundedCornerShape(28.dp),
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Sync,
                        contentDescription = "Synchronizuj",
                        tint = Color.White
                    )
                },
                text = {
                    Text(
                        text = "Synchronizuj kalendarz",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = 88.dp)
        ) {
            // POZIOMY PASEK DNI TYGODNIA
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    itemsIndexed(days) { index, day ->
                        val isSelected = index == selectedDayIndex
                        DayPill(
                            dayName = day.dayName,
                            dayNumber = day.dayNumber,
                            isSelected = isSelected,
                            onClick = { selectedDayIndex = index }
                        )
                    }
                }
            }

            // FILTRY (PIGUŁKI / CHIPSY)
            item {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(filterOptions.size) { index ->
                        val filterName = filterOptions[index]
                        val isSelected = filterName == selectedFilter
                        FilterChipPill(
                            title = filterName,
                            isSelected = isSelected,
                            onClick = { selectedFilter = filterName }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            // OS CZASU ZAJĘĆ (z kropkami i pionową linią czasu z lewej strony)
            itemsIndexed(filteredItems) { index, item ->
                TimelineScheduleCard(
                    item = item,
                    isLast = index == filteredItems.size - 1
                )
            }
        }
    }
}

@Composable
private fun DayPill(
    dayName: String,
    dayNumber: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(52.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                if (isSelected) DarkNavy else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dayName,
                style = MaterialTheme.typography.labelSmall,
                color = if (isSelected) Color.White.copy(alpha = 0.8f) else MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = dayNumber,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                ),
                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun FilterChipPill(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        shadowElevation = if (isSelected) 3.dp else 0.dp,
        tonalElevation = if (isSelected) 2.dp else 0.dp
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
            ),
            color = if (isSelected) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)
        )
    }
}

@Composable
private fun TimelineScheduleCard(
    item: ScheduleClassItem,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(horizontal = 16.dp)
    ) {
        // Os czasu: Pionowa linia + kropka
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            // Node / Kropka
            Box(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(DarkNavy)
            )

            // Pionowa linia
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(MaterialTheme.colorScheme.outlineVariant)
                )
            } else {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        Spacer(modifier = Modifier.width(14.dp))

        // Karta zajęć
        Card(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 16.dp),
            shape = RoundedCornerShape(18.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Górny rząd: Godziny + Etykieta
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Schedule,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item.timeInterval,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    // Etykieta (Wykład / Laboratorium / Ćwiczenia)
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = item.labelBg
                    ) {
                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = item.labelText,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Tytuł zajęć
                Text(
                    text = item.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Lokalizacja i Prowadzący
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Apartment,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item.room,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Person,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = item.instructor,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                // Presence status (if present)
                if (item.isAttendanceConfirmed) {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = StatusGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Obecność potwierdzona",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = StatusGreen
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScheduleScreenPreview() {
    MyApplicationTheme {
        ScheduleScreen()
    }
}
