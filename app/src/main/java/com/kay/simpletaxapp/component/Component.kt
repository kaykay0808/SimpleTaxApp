package com.kay.simpletaxapp.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kay.simpletaxapp.R
import com.kay.simpletaxapp.data.TaxViewState
import com.kay.simpletaxapp.ui.theme.EXTRA_LARGE_PADDING
import com.kay.simpletaxapp.ui.theme.LARGE_PADDING
import com.kay.simpletaxapp.ui.theme.MEDIUM_PADDING
import com.kay.simpletaxapp.ui.theme.TOP_HEADER_HEIGHT
import com.kay.simpletaxapp.ui.theme.topHeaderBackground
import com.kay.simpletaxapp.ui.theme.topHeaderTextColor
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaxForm(
    modifier: Modifier = Modifier,
    viewState: TaxViewState,
    taxViewModel: TaxViewModel,
    percentage: Int,
    sliderPositionState: Float,
    onValChange: (String) -> Unit
) {
    // Valid state if totalBillState is not empty
    val validState = remember(viewState.totalSalaryAmountState) {
        viewState.totalSalaryAmountState.trim()
            .isNotEmpty() /* <- Returns a boolean "if it's not empty it will return true"*/
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(incomeAfterTax = viewState.totalIncomeAfterTax)

    SalaryInputField(
        inputValueState = viewState.totalSalaryAmountState,
        labelId = stringResource(id = R.string.input_field_label),
        viewModel = taxViewModel,
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            // Todo - onValuedChanged
            onValChange(viewState.totalSalaryAmountState.trim())
            keyboardController?.hide()
        }
    )
    TaxInfo(
        percentage = percentage,
        viewState = viewState
    )
    if (validState) {
        TheSlider(
            viewModel = taxViewModel,
            sliderPositionState = sliderPositionState,
        )
    } else {
        Box {
            // show a sad empty box
        }
    }
}

@Composable
fun TopHeader(
    incomeAfterTax: Double = 0.0
) {
    val totalAfterTaxFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("nb-NO"))
        .format(incomeAfterTax)/*"%.2f".format(incomeAfterTax)*/
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(EXTRA_LARGE_PADDING)
            .height(TOP_HEADER_HEIGHT)
            .clip(shape = CircleShape.copy(all = CornerSize(12.dp))),
        color = MaterialTheme.colors.topHeaderBackground
    ) {
        Column(
            modifier = Modifier
                .padding(LARGE_PADDING),
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

@Composable
fun SalaryInputField(
    modifier: Modifier = Modifier,
    inputValueState: String,
    labelId: String,
    isSingleLine: Boolean,
    enabled: Boolean,
    viewModel: TaxViewModel,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray),
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            OutlinedTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = MEDIUM_PADDING),
                value = inputValueState, // removed.value
                onValueChange = { newInputVal ->
                    viewModel.onInputValueChange(newInputVal)
                },
                label = { Text(text = labelId) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.AttachMoney,
                        contentDescription = "Money Icon"
                    )
                },
                trailingIcon = {
                               IconButton(
                                   onClick = { viewModel.onResetInputValueChange() }
                               ) {
                                   Icon(
                                       imageVector = Icons.Filled.Close,
                                       contentDescription = stringResource(id = R.string.close_icon),
                                       tint = MaterialTheme.colors.primary
                                   )
                               }

                },
                textStyle = TextStyle(
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                singleLine = isSingleLine,
                enabled = enabled,
                keyboardOptions = KeyboardOptions(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = onAction
            )
        }
    }
}

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
                text = "$ ${viewState.taxAmountState}",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "$percentage %",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "${viewState.totalSalaryAmountState} -",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TheSlider(
    viewModel: TaxViewModel,
    sliderPositionState: Float,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState,
            onValueChange = { newVal ->
                viewModel.onSliderValueChange(newVal)
            }
        )
    }
}

@Preview
@Composable
fun TopHeaderPreview() {
    TopHeader()
}

@Preview(showBackground = true)
@Composable
fun SalaryInputFieldPreview() {
    val taxViewModel = TaxViewModel()
    SalaryInputField(
        viewModel = taxViewModel,
        inputValueState = "1000",
        labelId = "Enter the salary",
        enabled = true,
        isSingleLine = true,
    )
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

@Preview
@Composable
fun TheSliderPreview() {
    val taxViewModel = TaxViewModel()
    val viewState = taxViewModel.viewState

    TheSlider(
        viewModel = taxViewModel,
        sliderPositionState = viewState.sliderPositionState,
    )
}
