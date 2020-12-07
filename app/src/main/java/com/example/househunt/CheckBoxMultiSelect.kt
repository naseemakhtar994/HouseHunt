package com.example.househunt

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import java.util.*


class  CheckBoxMultiSelect {
    fun showDiaog(activity: ViewPropertyListing){


        val strings: MutableList<String> = ArrayList()

        strings.add(activity.getString(R.string.onebhk))
        strings.add(activity.getString(R.string.twobhk))
        strings.add(activity.getString(R.string.threebhk))
        strings.add(activity.getString(R.string.rhk))
        strings.add(activity.getString(R.string.paint_house))
        strings.add(activity.getString(R.string.villa))
        strings.add(activity.getString(R.string.bangalow))


        val indices = intArrayOf(0, 1,2,3,4,5,6)

        MaterialDialog(activity).show {
            listItemsMultiChoice(items = strings ,initialSelection=indices) { _, index, text ->
                // Invoked when the user selects item(s)
                activity.getFilterAMinities(text as ArrayList<*>);
            }
            positiveButton(R.string.Choose)

        }
    }
}

