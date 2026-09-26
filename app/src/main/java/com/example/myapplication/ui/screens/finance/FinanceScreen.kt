package com.example.myapplication.ui.screens.finance

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.rounded.AccountBalance
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Payment
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
private val DarkBadgeBg = Color(0xFF1E293B)

private val SeledynBg = Color(0xFFCCFBF1)
private val SeledynText = Color(0xFF0F766E)
private val MorskiTurkus = Color(0xFF14B8A6)

private val OrangeOczekujeBg = Color(0xFFFFF7ED)
private val OrangeOczekujeText = Color(0xFFC2410C)

private val GrayZaplanowanaBg = Color(0xFFF1F5F9)
private val GrayZaplanowanaText = Color(0xFF475569)

private val GreenOplaconaBg = Color(0xFFDCFCE7)
private val GreenOplaconaText = Color(0xFF15803D)

private data class Installment(
    val title: String,
    val statusLabel: String,
    val statusBg: Color,
    val statusText: Color,
    val dueDate: String,
    val amount: String,
    val canPay: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FinanceScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Tab state: 0 = "Do opłacenia", 1 = "Historia wpłat"
    var selectedTab by remember { mutableIntStateOf(0) }

    // Dialog state for BLIK
    var showBlikDialog by remember { mutableStateOf(false) }
    var blikCode by remember { mutableStateOf("") }

    val pendingInstallments = remember {
        listOf(
            Installment(
                title = "Czesne – Rata 3/5",
                statusLabel = "Oczekuje",
                statusBg = OrangeOczekujeBg,
                statusText = OrangeOczekujeText,
                dueDate = "15.11.2024",
                amount = "1 200,00 zł",
                canPay = true
            ),
            Installment(
                title = "Czesne – Rata 4/5",
                statusLabel = "Zaplanowana",
                statusBg = GrayZaplanowanaBg,
                statusText = GrayZaplanowanaText,
                dueDate = "15.12.2024",
                amount = "1 200,00 zł",
                canPay = true
            )
        )
    }

    val historyInstallments = remember {
        listOf(
            Installment(
                title = "Czesne – Rata 1/5",
                statusLabel = "Opłacona",
                statusBg = GreenOplaconaBg,
                statusText = GreenOplaconaText,
                dueDate = "15.09.2024",
                amount = "1 200,00 zł",
                canPay = false
            ),
            Installment(
                title = "Czesne – Rata 2/5",
                statusLabel = "Opłacona",
                statusBg = GreenOplaconaBg,
                statusText = GreenOplaconaText,
                dueDate = "15.10.2024",
                amount = "1 200,00 zł",
                canPay = false
            ),
            Installment(
                title = "Opłata za legitymację ELS",
                statusLabel = "Opłacona",
                statusBg = GreenOplaconaBg,
                statusText = GreenOplaconaText,
                dueDate = "01.10.2024",
                amount = "22,00 zł",
                canPay = false
            )
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Finanse",
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            text = "Rok akademicki 2024/25",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // GŁÓWNA KARTA PODSUMOWANIA (ciemnogranatowe tło)
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = DarkNavy
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    // Etykieta
                    Text(
                        text = "ŁĄCZNA KWOTA DO OPŁACENIA",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White.copy(alpha = 0.7f)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Kwota
                    Text(
                        text = "2 400,00 zł",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        ),
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Badges row: ikona zegara "Termin: 15.11.2024" i "2 raty pozostałe"
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ciemny badge
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = DarkBadgeBg
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.Schedule,
                                    contentDescription = null,
                                    tint = Color.White.copy(alpha = 0.8f),
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Termin: 15.11.2024",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontWeight = FontWeight.SemiBold
                                    ),
                                    color = Color.White.copy(alpha = 0.9f)
                                )
                            }
                        }

                        // Jasny badge (seledynowy)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = SeledynBg
                        ) {
                            Text(
                                text = "2 raty pozostałe",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = SeledynText,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Pasek postępu: "Postęp opłat 3/5 rat" oraz pasek 60% w kolorze morskim
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Postęp opłat 3/5 rat",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            Text(
                                text = "60%",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MorskiTurkus
                            )
                        }

                        LinearProgressIndicator(
                            progress = { 0.6f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = MorskiTurkus,
                            trackColor = Color.White.copy(alpha = 0.2f)
                        )
                    }
                }
            }

            // PRZEŁĄCZNIK ZAKŁADEK: "Do opłacenia" / "Historia wpłat"
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    // Zakładka "Do opłacenia"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selectedTab == 0) MaterialTheme.colorScheme.surface else Color.Transparent
                            )
                            .clickable { selectedTab = 0 }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Do opłacenia",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = if (selectedTab == 0) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (selectedTab == 0) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Zakładka "Historia wpłat"
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selectedTab == 1) MaterialTheme.colorScheme.surface else Color.Transparent
                            )
                            .clickable { selectedTab = 1 }
                            .padding(vertical = 12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Historia wpłat",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = if (selectedTab == 1) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (selectedTab == 1) MaterialTheme.colorScheme.onSurface
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // LISTA RAT DO OPŁACENIA / HISTORIA
            AnimatedVisibility(
                visible = selectedTab == 0,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for (inst in pendingInstallments) {
                        InstallmentCard(
                            installment = inst,
                            onPayClick = { showBlikDialog = true }
                        )
                    }
                }
            }

            AnimatedVisibility(
                visible = selectedTab == 1,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    for (inst in historyInstallments) {
                        InstallmentCard(
                            installment = inst,
                            onPayClick = {}
                        )
                    }
                }
            }

            // CIEMNOGRANATOWY PRZYCISK "Zapłać przez BLIK"
            Button(
                onClick = { showBlikDialog = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkNavy,
                    contentColor = Color.White
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Payment,
                        contentDescription = "BLIK",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Zapłać przez BLIK",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            // LINK POD PRZYCISKIEM: "Pobierz fakturę / dane do przelewu"
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        Toast.makeText(
                            context,
                            "Dane do przelewu / faktura zostały pobrane",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Rounded.Download,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Pobierz fakturę / dane do przelewu",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Subkonto info
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.AccountBalance,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Numer subkonta do przelewu tradycyjnego:",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "89 1090 1362 0000 0001 2345 6789",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // BLIK Payment Dialog
    if (showBlikDialog) {
        AlertDialog(
            onDismissRequest = { showBlikDialog = false },
            title = {
                Text(
                    text = "Płatność BLIK",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Wprowadź 6-cyfrowy kod z aplikacji bankowej:",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    OutlinedTextField(
                        value = blikCode,
                        onValueChange = { if (it.length <= 6) blikCode = it },
                        label = { Text("Kod BLIK") },
                        placeholder = { Text("123 456") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (blikCode.length == 6) {
                            showBlikDialog = false
                            blikCode = ""
                            Toast.makeText(context, "Płatność BLIK zrealizowana pomyślnie!", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(context, "Wprowadź poprawny 6-cyfrowy kod BLIK", Toast.LENGTH_SHORT).show()
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = DarkNavy)
                ) {
                    Text("Zapłać")
                }
            },
            dismissButton = {
                TextButton(onClick = { showBlikDialog = false }) {
                    Text("Anuluj")
                }
            }
        )
    }
}

@Composable
private fun InstallmentCard(
    installment: Installment,
    onPayClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Column: Title, Badge, Subtitle
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = installment.title,
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = installment.statusBg
                    ) {
                        Text(
                            text = installment.statusLabel,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = installment.statusText,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Termin: ${installment.dueDate}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Right Column: Amount & "Zapłać >" link
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = installment.amount,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                if (installment.canPay) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .clickable(onClick = onPayClick)
                            .padding(vertical = 2.dp, horizontal = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Zapłać",
                            style = MaterialTheme.typography.labelLarge.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.primary
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                } else {
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = GreenOplaconaText,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Opłacono",
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = GreenOplaconaText
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceScreenPreview() {
    MyApplicationTheme {
        FinanceScreen()
    }
}
