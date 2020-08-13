package com.telefonica.mistica.catalog.ui

import android.icu.text.Collator.ReorderCodes.OTHERS
import android.os.Bundle
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.telefonica.mistica.catalog.R
import com.telefonica.mistica.catalog.ui.fragment.*

class ComponentCatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        @StyleRes
        val themeOverride: Int? =
            intent.getIntExtra(EXTRA_THEME, NO_THEME_OVERRIDE).takeIf { it != NO_THEME_OVERRIDE }

        themeOverride?.let {
            setTheme(it)
        }

        super.onCreate(savedInstanceState)

        setContentView(R.layout.screen_component_catalog)

        val fragment: Fragment = when (intent.getSerializableExtra(EXTRA_SECTION)) {
            Section.BUTTONS -> ButtonsCatalogFragment()
            Section.INPUTS -> InputsCatalogFragment()
            Section.SNACKBARS -> SnackBarCatalogFragment()
            Section.FEEDBACKS -> FeedbackScreenCatalogFragment(themeOverride)
            Section.LOAD_ERROR_FEEDBACK -> LoadErrorFeedbackCatalogFragment()
            Section.POPOVERS -> PopOverCatalogFragment()
            Section.BADGES -> BadgesCatalogFragment()
            Section.OTHERS -> OthersCatalogFragment()
            Section.SCROLL_CONTENT_INDICATOR -> ScrollContentIndicatorCatalogFragment()
            Section.TAG -> TagsCatalogFragment()
            Section.LISTS -> ListsCatalogFragment()
            Section.HEADERS -> HeadersCatalogFragment()
            Section.HIGHLIGHTED_CARDS -> HighlightedCardsCatalogFragment()
            else -> OthersCatalogFragment()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.catalog_container, fragment)
        }.commit()
    }

    companion object {
        const val EXTRA_SECTION = "extra_section"
        const val EXTRA_THEME = "extra_theme"

        private const val NO_THEME_OVERRIDE = -1
    }
}

enum class Section {
    BUTTONS,
    INPUTS,
    SNACKBARS,
    FEEDBACKS,
    LOAD_ERROR_FEEDBACK,
    POPOVERS,
    BADGES,
    SCROLL_CONTENT_INDICATOR,
    TAG,
    LISTS,
    HEADERS,
    HIGHLIGHTED_CARDS,
    OTHERS,
}