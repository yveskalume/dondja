package com.dondja.dondja.util.ui
//
//import android.content.Context
//import android.content.res.Configuration
//import com.airbnb.epoxy.EpoxyModel.SpanSizeOverrideCallback
//
//
//class NumItemsInGridRow(context: Context, forPhone: Int, forTablet: Int, forWideTablet: Int) : SpanSizeOverrideCallback {
//
//    val isTabletScreen = context.resources.configuration.screenLayout  == 4 && Configuration.SCREENLAYOUT_SIZE_MASK == 4
//    // FIXME: 01/05/21 isphone
//
//    private val numItemsForCurrentScreen: Int = if (isWideTabletScreen(context)) forWideTablet else if (isTabletScreen(context)) forTablet else forPhone
//
//    override fun getSpanSize(totalSpanCount: Int, position: Int, itemCount: Int): Int {
//        check(totalSpanCount % numItemsForCurrentScreen == 0) { "Total Span Count of : $totalSpanCount can not evenly fit: $numItemsForCurrentScreen cards per row" }
//        return totalSpanCount / numItemsForCurrentScreen
//    }
//
//    companion object {
//        /** Shows one item per row on phone and two for all tablet sizes.  */
//        fun oneColumnPhoneTwoColumnTablet(context: Context?): NumItemsInGridRow {
//            return NumItemsInGridRow(context, 1, 2, 2)
//        }
//
//        /** Specify how many items to show per grid row on phone. Tablet will show more items per row according to a default ratio.  */
//        fun forPhoneWithDefaultScaling(context: Context?, numItemsPerRowOnPhone: Int): NumItemsInGridRow {
//            return NumItemsInGridRow(context, numItemsPerRowOnPhone, round(numItemsPerRowOnPhone * 1.5f), numItemsPerRowOnPhone * 2)
//        }
//    }
//
//}