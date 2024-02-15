//package com.ufape.shaypado.ui.components
//
//import android.util.Log
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.outlined.ArrowDropDown
//import androidx.compose.material3.Divider
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.rotate
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import com.ufape.shaypado.ui.theme.White
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//
//
//data class AccordionModel(
//    val header: String,
//    val rows: List<Row>
//) {
//    data class Row(
//        val security: String,
//        val price: String
//    )
//}
//
//
////@Preview
////@Composable
////private fun AccordionHeader(
////    title: String = "Header",
////    isExpanded: Boolean = false,
////    onTapped: () -> Unit = {}
////) {
////    val degrees = if (isExpanded) 180f else 0f
////
////    val headerShape = RoundedCornerShape(
////        8.dp,
////        8.dp,
////        if (isExpanded) 0.dp else 8.dp,
////        if (isExpanded) 0.dp else 8.dp
////    )
////
////    CustomCard(elevation = 0, shape = headerShape) {
////        Row(
////            modifier = Modifier
////                .fillMaxWidth()
////                .clickable { onTapped() }
////                .padding(16.dp),
////            horizontalArrangement = Arrangement.SpaceBetween,
////            verticalAlignment = Alignment.CenterVertically
////        ) {
////            AppText(text = title)
////            Surface(shape = CircleShape) {
////                Icon(
////                    Icons.Outlined.ArrowDropDown,
////                    contentDescription = "arrow-down",
////                    modifier = Modifier.rotate(degrees),
////                    tint = White
////                )
////            }
////        }
////    }
////}
//
//@Preview
//@Composable
//private fun AccordionRow(
//    model: AccordionModel.Row = AccordionModel.Row("AAPL", "$328.89"),
//    backGroundColor: Color = Color.Transparent,
//    onTapped: () -> Unit = {}
//) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(color = backGroundColor)
//            .padding(8.dp)
//            .clickable { onTapped() },
//        horizontalArrangement = Arrangement.SpaceBetween,
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        AppText(text = model.security, textType = TextType.LABEL_MEDIUM)
//        Surface(color = Color.Green, shape = RoundedCornerShape(8.dp)) {
//            AppText(
//                textType = TextType.LABEL_MEDIUM,
//                text = model.security,
//            )
//        }
//    }
//}
//
//@Composable
//fun Accordion(modifier: Modifier = Modifier, model: AccordionModel) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//
//    val bodyShape = RoundedCornerShape(
//        if (expanded) 0.dp else 8.dp,
//        if (expanded) 0.dp else 8.dp,
//        8.dp,
//        8.dp
//    )
//
//    Column() {
//        AccordionHeader(title = model.header, isExpanded = expanded) {
//            expanded = !expanded
//        }
//        AnimatedVisibility(
//            visible = expanded,
//            Modifier.background(color = Color.LightGray)
//        ) {
//            CustomCard(shape = bodyShape) {
//                LazyColumn(
//                    modifier = Modifier.background(
//                        color = Color.White
//                    )
//                ) {
//                    for (row in model.rows) {
//                        item {
//                            AccordionRow(row)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun SelectableAccordion(
//    modifier: Modifier = Modifier,
//    model: AccordionModel,
//    onSelected: () -> Unit,
//    selected: Int
//) {
//    var expanded by rememberSaveable { mutableStateOf(false) }
//    var selectedItem by rememberSaveable { mutableStateOf(selected) }
//
//    val bodyShape = RoundedCornerShape(
//        if (expanded) 0.dp else 8.dp,
//        if (expanded) 0.dp else 8.dp,
//        8.dp,
//        8.dp
//    )
//
//    fun onItemSelected(selected: Int) {
//        Log.d("Accordion", "onItemSelected: $selected")
//        expanded = !expanded
//        selectedItem = selected
//        onSelected()
//    }
//
//    Column(modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
//        AccordionHeader(title = model.header, isExpanded = expanded) {
//            expanded = !expanded
//        }
//        AnimatedVisibility(visible = expanded) {
//            CustomCard(shape = bodyShape, elevation = 0) {
//                LazyColumn {
//                    for (row in model.rows) {
//                        item {
//                            AccordionRow(
//                                model = row,
//                                backGroundColor = if (selectedItem == model.rows.indexOf(row)) Color.LightGray else Color.Transparent,
//                                onTapped = { onItemSelected(model.rows.indexOf(row)) }
//                            )
//                            Divider()
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
