package com.kay.simpletaxapp.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.kay.simpletaxapp.ui.theme.MEDIUM_PADDING
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel
import com.kay.simpletaxapp.ui.viewmodel.TaxViewState
import com.kay.simpletaxapp.util.formatCurrency

@Composable
fun TaxInfo(
    modifier: Modifier = Modifier,
    viewState: TaxViewState,
    percentage: Int
) {
    Column {
        Row(
            modifier = modifier.padding(start = MEDIUM_PADDING, end = MEDIUM_PADDING),
            horizontalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = modifier.weight(1f),
                text = "Tax Pay",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "Tax Percentage",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "Net",
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
        Row(
            modifier = modifier.padding(MEDIUM_PADDING),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = modifier.weight(1f),
                text = formatCurrency(viewState.taxAmount), // "$ ${viewState.taxAmount}",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "$percentage %",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = formatCurrency(viewState.netSalaryDouble),//"${viewState.netSalaryString} -",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaxInfoPreview() {
    val taxViewModel = TaxViewModel()
    val viewState = taxViewModel.viewState
    TaxInfo(
        viewState = viewState,
        percentage = 10
    )
}
