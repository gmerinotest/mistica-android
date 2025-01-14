package com.telefonica.mistica.catalog.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.telefonica.mistica.catalog.R
import com.telefonica.mistica.list.ListRowView
import com.telefonica.mistica.list.ListRowView.AssetType
import com.telefonica.mistica.list.ListRowView.Companion.TYPE_IMAGE
import com.telefonica.mistica.list.ListRowView.Companion.TYPE_LARGE_ICON
import com.telefonica.mistica.list.ListRowView.Companion.TYPE_SMALL_ICON
import com.telefonica.mistica.list.MisticaRecyclerView

class ListsCatalogFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.screen_fragment_lists_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list: MisticaRecyclerView = view.findViewById(R.id.list)
        list.adapter = ListAdapter(boxed = false)

        val boxedList: MisticaRecyclerView = view.findViewById(R.id.boxed_list)
        boxedList.adapter = ListAdapter(boxed = true)
    }

    @Suppress("MagicNumber")
    class ListAdapter(private val boxed: Boolean) : RecyclerView.Adapter<ListViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
            ListViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.screen_fragment_lists_catalog_item,
                    parent,
                    false
                ) as ListRowView
            )

        override fun getItemCount(): Int = 25

        override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
            val rowView: ListRowView = holder.rowView
            rowView.setBoxed(boxed)
            when (position) {
                0 -> rowView.configureView()
                1 -> rowView.configureView(
                    withAction = true
                )
                2 -> rowView.configureView(
                    withAction = true,
                    withBadge = true,
                    withBadgeDescription = "You have unread messages"
                )
                3 -> rowView.configureView(
                    withAction = true,
                    withBadgeNumeric = 1
                )
                4 -> rowView.configureView(
                    withLongDescription = false
                )
                5 -> rowView.configureView(
                    withLongDescription = false,
                    withAction = true
                )
                6 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON
                )
                7 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true
                )
                8 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true,
                    withBadge = true
                )
                9 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true,
                    withBadgeNumeric = 5,
                    withBadgeDescription = "5 new messages"
                )
                10 -> rowView.configureView(
                    withLongDescription = false,
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON
                )
                11 -> rowView.configureView(
                    withLongDescription = false,
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true
                )
                12 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_SMALL_ICON
                )
                13 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_SMALL_ICON,
                    withAction = true
                )
                14 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_SMALL_ICON,
                    withAction = true,
                    withBadge = true
                )
                15 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_SMALL_ICON,
                    withAction = true,
                    withBadgeNumeric = 10
                )
                16 -> rowView.configureView(
                    withLongTitle = true,
                    withLongDescription = true
                )
                17 -> rowView.configureView(
                    withLongTitle = true,
                    withLongDescription = true,
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON
                )
                18 -> rowView.configureView(
                    withLongTitle = true,
                    withLongDescription = true,
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true
                )
                19 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true,
                    withHeadline = true
                )
                20 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true,
                    withSubtitle = true
                )
                21 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withAction = true,
                    withSubtitle = true,
                    withHeadline = true
                )
                22 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_LARGE_ICON,
                    withLongDescription = false,
                    withAction = true,
                    withSubtitle = true,
                    withHeadline = true
                )
                23 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_IMAGE,
                    withLongDescription = false
                )
                24 -> rowView.configureView(
                    withAsset = true,
                    withAssetType = TYPE_IMAGE
                )
            }
        }

        @SuppressLint("SetTextI18n")
        private fun ListRowView.configureView(
            withLongTitle: Boolean = false,
            withLongDescription: Boolean? = null,
            withAsset: Boolean = false,
            @AssetType withAssetType: Int = TYPE_SMALL_ICON,
            withAction: Boolean = false,
            withBadge: Boolean = false,
            withBadgeNumeric: Int = 0,
            withHeadline: Boolean = false,
            withSubtitle: Boolean = false,
            withBadgeDescription: String? = null
        ) {
            if (withHeadline) {
                setHeadlineLayout(R.layout.list_row_text_headline)
                (getHeadline()!! as TextView).text = "Headline"
            } else {
                setHeadlineLayout(ListRowView.HEADLINE_NONE)
            }

            setTitle(if (withLongTitle) "Title long enough to need 2 lines to show it, just for testing purposes." else "Title")
            setSubtitle(if (withSubtitle) "Any Subtitle" else null)
            setDescription(
                withLongDescription?.let { long ->
                    if (long) {
                        "Description long enough to need 2 lines to show it, just for testing purposes."
                    } else {
                        "Description"
                    }
                }
            )

            setAssetType(withAssetType)
            setAssetResource(getAssetResource(withAsset, withAssetType))

            if (withAction) {
                setActionLayout(R.layout.list_row_chevron_action)
                setOnClickListener { Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show() }
            } else {
                setActionLayout(ListRowView.ACTION_NONE)
                isClickable = false
            }

            when {
                withBadge -> setBadge(true, withBadgeDescription)
                withBadgeNumeric > 0 -> setNumericBadge(withBadgeNumeric, withBadgeDescription)
                else -> setBadge(false, withBadgeDescription)
            }
        }

        private fun getAssetResource(withAsset: Boolean, withAssetType: Int): Int? =
            if (withAsset) {
                if (withAssetType == TYPE_IMAGE) {
                    R.drawable.highlighted_card_custom_background
                } else {
                    R.drawable.ic_lists
                }
            } else {
                null
            }
    }

    class ListViewHolder(val rowView: ListRowView) : RecyclerView.ViewHolder(rowView)
}
