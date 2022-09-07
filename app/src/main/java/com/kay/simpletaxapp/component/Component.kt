package com.kay.simpletaxapp.component

import android.util.Log
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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
    totalSalaryAmountState: String,
    taxViewModel: TaxViewModel,
    totalIncomeAfterTax: Double,
    taxPay: Double,
    percentage: Int,
    sliderPositionState: Float,
    onValChange: (String) -> Unit
) {
    // Valid state if totalBillState is not empty
    val validState = remember(totalSalaryAmountState) {
        totalSalaryAmountState.trim()
            .isNotEmpty() /* <- Returns a boolean "if it's not empty it will return true"*/
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader(incomeAfterTax = viewState.totalIncomeAfterTax)

    SalaryInputField(
        inputValueState = totalSalaryAmountState,
        labelId = stringResource(id = R.string.input_field_label),
        viewModel = taxViewModel,
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            // Todo - onValuedChanged
            onValChange(totalSalaryAmountState.trim()) // totalSalaryAmountState.value
            keyboardController?.hide()
        }
    )
    TaxInfo(
        totalSalaryAmountState = totalSalaryAmountState,
        // taxViewModel = taxViewModel,
        totalIncomeAfterTax = totalIncomeAfterTax,
        taxPay = taxPay,//viewState.taxAmountState,
        percentage = percentage, // <- fix this percentage
    )
    if (validState) {
        TheSlider(
            // totalSalaryAmountState = totalSalaryAmountState,
            sliderPositionState = sliderPositionState,
            viewModel = taxViewModel,
            percentage = percentage,
            // taxPay = taxPay,
            // totalSalaryAmountState = totalSalaryAmountState,
            // totalIncomeAfterTax = totalIncomeAfterTax
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
            Log.d("HeaderNameValue", "HeaderValue: $totalAfterTaxFormat")
        }
    }
}

// Think everything is okey here
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
                    Log.d("ViewModelInputValue", " \nNewInputValue: $newInputVal \nOldInputValue: $inputValueState")
                    // log Checked, The value is correct until slider is moved.
                },
                label = { Text(text = labelId) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.AttachMoney,
                        contentDescription = "Money Icon"
                    )
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
    // taxViewModel: TaxViewModel,
    totalSalaryAmountState: String,
    totalIncomeAfterTax: Double,
    taxPay: Double,
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
                text = "$ $taxPay", /* Double -> */
                textAlign = TextAlign.Center
            )
            Log.d("totalIncomeValue", "total income after tax is: $totalIncomeAfterTax")
            Text(
                modifier = modifier.weight(1f),
                text = "$percentage %",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "$totalSalaryAmountState -"/*totalSalaryAmountState.value*/,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TheSlider(
    // totalSalaryAmountState: String,
    // totalIncomeAfterTax: Double,
    // taxPay: Double,
    sliderPositionState: Float,
    viewModel: TaxViewModel,
    percentage: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState,
            onValueChange = { newVal ->
                Log.d("SliderChange", "BillForm: $newVal")
                viewModel.onSliderValueChange(newVal)
                // The newVal returns the float value
            }
        )
        Log.d("SliderValue", " \nValueSliderPositionState: $sliderPositionState")
    }
}

@Preview
@Composable
fun TopHeaderPreview() {
    TopHeader()
}

/*@Preview(showBackground = true)
@Composable
fun SalaryInputFieldPreview() {
    val totalSalary = remember {
        mutableStateOf("")
    }
    SalaryInputField(
        inputValueState = totalSalary,
        labelId = "Enter the salary",
        enabled = true,
        isSingleLine = true
    )
}*/

/*@Preview(showBackground = true)
@Composable
fun TaxInfoPreview() {
    val totalSalaryAmountState = remember {
        mutableStateOf("")
    }
    val totalIncomeAfterTax = remember {
        mutableStateOf(0.0)
    }
    val taxAmountState = remember {
        mutableStateOf(0.0)
    }
    TaxInfo(
        totalSalaryAmountState = totalSalaryAmountState,
        totalIncomeAfterTax = totalIncomeAfterTax,
        taxPay = taxAmountState,
        percentage = 10
    )
}*/

/*@Preview
@Composable
fun TheSliderPreview() {
    val totalSalaryAmountState = remember {
        mutableStateOf("")
    }
    val totalIncomeAfterTax = remember {
        mutableStateOf(0.0)
    }
    val taxAmountState = remember {
        mutableStateOf(0.0)
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    TheSlider(
        totalSalaryAmountState = totalSalaryAmountState,
        totalIncomeAfterTax = totalIncomeAfterTax,
        sliderPositionState = sliderPositionState,
        taxPay = taxAmountState,
        percentage = 1
    )
}*/
