package com.telefonica.mistica.chips

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import androidx.annotation.IdRes
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.telefonica.mistica.R
import com.telefonica.mistica.util.children

class HorizontalScrollChipGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val chipGroup: ChipGroup by lazy { findViewById<ChipGroup>(R.id.chips_group) }
    private val scrollView: HorizontalScrollView by lazy { findViewById<HorizontalScrollView>(R.id.chips_scroll_view) }
    private val horizontalPadding: Int = context.resources.getDimensionPixelOffset(R.dimen.chip_group_horizontal_padding)

    init {
        View.inflate(context, R.layout.horizontal_scroll_chip_group, this)
        setOnCheckedChangeListener { _, _ -> }
    }

    fun addChip(chip: Chip) {
        chipGroup.addView(chip)

        if (chip.isChecked) {
            chipGroup.clearCheck()
            chipGroup.check(chip.id)
            chipGroup.makeCheckedChipNonCheckable()
        }
    }

    fun removeAllChips() {
        chipGroup.removeAllViews()
    }

    fun setOnCheckedChangeListener(onCheckedChange: (chipGroup: ChipGroup, checkedChipId: Int) -> Unit) {
        chipGroup.setOnCheckedChangeListener { chipGroup, id ->
            chipGroup.makeCheckedChipNonCheckable()
            chipGroup.scrollToShowChip(id)
            onCheckedChange(chipGroup, id)
        }
    }

    private fun ChipGroup.makeCheckedChipNonCheckable() {
        children()
            .filterIsInstance<Chip>()
            .forEach { chip ->
                chip.isCheckable = chip.id != checkedChipId
                chip.isChecked = chip.id == checkedChipId
            }
    }

    private fun ChipGroup.scrollToShowChip(@IdRes checkedChipId: Int) {
        if (checkedChipId == View.NO_ID) return

        val chip = findViewById<Chip>(checkedChipId)

        addOnLayoutChangeListener(object : OnLayoutChangeListener {
            override fun onLayoutChange(
                view: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                view.removeOnLayoutChangeListener(this)
                val paddingRequiredToShowWholeChipPlusASpaceAtBothSides = 2 * horizontalPadding
                val rect = Rect(0, 0, chip.width + paddingRequiredToShowWholeChipPlusASpaceAtBothSides, chip.height)
                val isScrollNotSmooth = true
                scrollView.requestChildRectangleOnScreen(chip, rect, isScrollNotSmooth)
            }
        })
    }
}