package com.example.mitienda.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mitienda.R
import com.example.mitienda.ui.theme.*

data class CartItem(
    val id: Int,
    val name: String,
    val size: String,
    val color: String,
    val price: Double,
    val quantity: Int,
    val imageRes: Int? = null
)

@Composable
fun CartScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = AppBackground,
        topBar = { CartTopBar() },
        bottomBar = { CartBottomNavigation() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(R.string.cart_title),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = stringResource(R.string.cart_subtitle),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 8.dp, bottom = 24.dp)
                )

                val cartItems = remember {
                    listOf(
                        CartItem(1, "Ethereal Silk Trench", "M", "Sand", 340.00, 1, R.drawable.astral),
                        CartItem(2, "Azure Velvet Stiletto", "38", "Emerald Tint", 210.00, 1)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    cartItems.forEach { item ->
                        CartItemRow(item = item)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
                PromoCodeSection()
                Spacer(modifier = Modifier.height(24.dp))
            }
            OrderSummaryPreview()
        }
    }
}

@Composable
fun CartTopBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Search Icon Placeholder
        Box(
            modifier = Modifier
                .size(24.dp)
                .padding(2.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .border(2.dp, BrandGreen, CircleShape)
            )
            Box(
                modifier = Modifier
                    .size(8.dp, 2.dp)
                    .background(BrandGreen)
                    .align(Alignment.BottomEnd)
            )
        }

        Text(
            text = stringResource(R.string.app_brand_name),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = BrandGreen
        )

        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFF444444))
        )
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF5F5F5))
            ) {
                if (item.imageRes != null) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.name,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .border(1.5.dp, Color(0xFF00E5FF), RoundedCornerShape(8.dp))
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                    // Delete Icon Placeholder
                    Box(
                        modifier = Modifier
                            .size(20.dp)
                            .padding(2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(2.dp)
                                .background(Color.LightGray)
                        )
                        Box(
                            modifier = Modifier
                                .size(12.dp, 14.dp)
                                .border(1.5.dp, Color.LightGray, RoundedCornerShape(2.dp))
                                .align(Alignment.BottomCenter)
                        )
                    }
                }
                Text(
                    text = "Size: ${item.size} | Color: ${item.color}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    QuantitySelector(item.quantity)
                    Text(
                        text = "$${"%.2f".format(item.price)}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PriceTeal
                    )
                }
            }
        }
    }
}

@Composable
fun QuantitySelector(quantity: Int) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(QuantityBackground)
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "—",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Bold,
            color = PriceTeal,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
        Text(
            text = quantity.toString(),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold
        )
        // Plus Icon Placeholder
        Box(
            modifier = Modifier.size(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(modifier = Modifier.fillMaxWidth().height(2.dp).background(PriceTeal))
            Box(modifier = Modifier.fillMaxHeight().width(2.dp).background(PriceTeal))
        }
    }
}

@Composable
fun PromoCodeSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFF1F5F2))
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.promo_code_label),
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(
                        stringResource(R.string.promo_code_placeholder),
                        color = Color.LightGray,
                        fontSize = 14.sp
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Button(
                onClick = { /* TODO */ },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ApplyButtonBackground),
                modifier = Modifier.height(52.dp),
                contentPadding = PaddingValues(horizontal = 24.dp)
            ) {
                Text(
                    text = stringResource(R.string.apply_button),
                    color = Color(0xFF5D7E73),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun OrderSummaryPreview() {
    Card(
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F2)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = stringResource(R.string.order_summary_title),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun CartBottomNavigation() {
    Surface(
        color = Color.White,
        shadowElevation = 16.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(stringResource(R.string.nav_home), false)
            BottomNavItem(stringResource(R.string.nav_categories), false)
            BottomNavItem(stringResource(R.string.nav_cart), true)
            BottomNavItem(stringResource(R.string.nav_profile), false)
        }
    }
}

@Composable
fun BottomNavItem(label: String, selected: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(56.dp, 36.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(NavSelected),
                contentAlignment = Alignment.Center
            ) {
                // Cart Icon Placeholder
                Box(modifier = Modifier.size(20.dp), contentAlignment = Alignment.Center) {
                    Box(modifier = Modifier.fillMaxSize().border(2.dp, Color.White, RoundedCornerShape(2.dp)))
                    Box(modifier = Modifier.size(10.dp, 2.dp).background(Color.White).align(Alignment.BottomCenter))
                }
            }
        } else {
            // Generic Icon Placeholder
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(1.5.dp, Color.Gray, RoundedCornerShape(4.dp))
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = if (selected) NavSelected else Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
    MiTiendaTheme {
        CartScreen()
    }
}
