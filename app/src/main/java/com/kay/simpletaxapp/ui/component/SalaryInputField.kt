package com.kay.simpletaxapp.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.AttachMoney
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kay.simpletaxapp.R
import com.kay.simpletaxapp.ui.theme.MEDIUM_PADDING
import com.kay.simpletaxapp.ui.viewmodel.TaxViewModel

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