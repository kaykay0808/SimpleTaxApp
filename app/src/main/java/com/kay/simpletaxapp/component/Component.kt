package com.kay.simpletaxapp.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.mutableStateOf
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
import com.kay.simpletaxapp.ui.theme.EXTRA_LARGE_PADDING
import com.kay.simpletaxapp.ui.theme.LARGE_PADDING
import com.kay.simpletaxapp.ui.theme.MEDIUM_PADDING
import com.kay.simpletaxapp.ui.theme.TOP_HEADER_HEIGHT
import com.kay.simpletaxapp.ui.theme.topHeaderBackground
import com.kay.simpletaxapp.ui.theme.topHeaderTextColor

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TaxForm(
    modifier: Modifier = Modifier,
    totalSalaryAmountState: MutableState<String>,
    // totalIncomeAfterTax: MutableState<Double>,
    sliderPositionState: MutableState<Float>,
    percentage: Int,
    onValChange: (String) -> Unit
) {
    // Valid state if totalBillState is not empty
    val validState = remember(totalSalaryAmountState.value) {
        totalSalaryAmountState.value.trim()
            .isNotEmpty() /* <- Returns a boolean "if it's not empty it will return true"*/
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TopHeader()

    SalaryInputField(
        inputValueState = totalSalaryAmountState,
        labelId = stringResource(id = R.string.input_field_label),
        enabled = true,
        isSingleLine = true,
        onAction = KeyboardActions {
            if (!validState) return@KeyboardActions
            // Todo - onValuedChanged
            onValChange(totalSalaryAmountState.value.trim())
            keyboardController?.hide()
        }
    )
    TaxInfo(percentage = percentage)
    TheSlider(
        // totalSalaryAmountState = totalSalaryAmountState,
        sliderPositionState = sliderPositionState,
        percentage = percentage
    )
}

@Composable
fun TopHeader(
    /*TaxAfterIncome*/
) {
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
                text = "$ 1000000",
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
    inputValueState: MutableState<String>,
    labelId: String,
    isSingleLine: Boolean,
    enabled: Boolean,
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
                value = inputValueState.value,
                onValueChange = { inputValueState.value = it },
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
                text = "$$$",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "$percentage %",
                textAlign = TextAlign.Center
            )
            Text(
                modifier = modifier.weight(1f),
                text = "0",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TheSlider(
    // totalSalaryAmountState: MutableState<String>,
    // totalIncomeAfterTax: MutableState<Double>,
    sliderPositionState: MutableState<Float>,
    percentage: Int
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = sliderPositionState.value,
            onValueChange = { newVal ->
                Log.d("SliderChange", "BillForm: $newVal")
                //totalSalaryAmountState.value =
                sliderPositionState.value = newVal
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
    val totalSalary = remember {
        mutableStateOf("")
    }
    SalaryInputField(
        inputValueState = totalSalary,
        labelId = "Enter the salary",
        enabled = true,
        isSingleLine = true
    )
}

@Preview(showBackground = true)
@Composable
fun TaxInfoPreview() {
    TaxInfo(percentage = 10)
}

@Preview
@Composable
fun TheSliderPreview() {
    val totalSalaryAmountState = remember {
        mutableStateOf("")
    }
    val sliderPositionState = remember {
        mutableStateOf(0f)
    }
    TheSlider(
        sliderPositionState = sliderPositionState,
        //totalSalaryAmountState = totalSalaryAmountState,
        percentage = 1
    )
}
