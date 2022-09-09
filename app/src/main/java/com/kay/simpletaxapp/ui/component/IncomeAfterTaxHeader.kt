package com.kay.simpletaxapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kay.simpletaxapp.R
import com.kay.simpletaxapp.ui.theme.*
import java.text.NumberFormat
import java.util.*

@Composable
fun IncomeAfterTaxHeader(incomeAfterTax: Double = 0.0) {
    val totalAfterTaxFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("nb-NO"))
        .format(incomeAfterTax)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(EXTRA_LARGE_PADDING)
            .height(TOP_HEADER_HEIGHT)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = MaterialTheme.colors.topHeaderBackground
    ) {
        Column(
            modifier = Modifier.padding(LARGE_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.income_after_tax),
                color = MaterialTheme.colors.topHeaderTextColor,
                style = MaterialTheme.typography.h5
            )
            Text(
                text = totalAfterTaxFormat,
                color = MaterialTheme.colors.topHeaderTextColor,
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview
@Composable
fun IncomeAfterTaxHeaderPreview() {
    IncomeAfterTaxHeader()
}