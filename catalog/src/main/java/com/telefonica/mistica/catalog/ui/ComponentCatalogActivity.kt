package com.telefonica.mistica.catalog.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.telefonica.mistica.catalog.R
import com.telefonica.mistica.catalog.ui.fragment.*

class ComponentCatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        intent.getIntExtra(EXTRA_THEME, NO_THEME_OVERRIDE).let {
            if (it != NO_THEME_OVERRIDE) {
                setTheme(it)
            }
        }

        super.onCreate(savedInstanceState)

        setContentView(R.layout.screen_component_catalog)

        val fragment: Fragment = when (intent.getSerializableExtra(EXTRA_SECTION)) {
            Section.BUTTONS -> ButtonsCatalogFragment()
            Section.INPUTS -> InputsCatalogFragment()
            Section.SNACKBARS -> SnackBarCatalogFragment()
            Section.FEEDBACKS -> FeedbackScreenCatalogFragment()
            Section.LOAD_ERROR_FEEDBACK -> LoadErrorFeedbackCatalogFragment()
            Section.POPOVERS -> PopOverCatalogFragment()
            Section.BADGES -> BadgesCatalogFragment()
            Section.OTHERS -> OthersCatalogFragment()
            Section.SCROLL_CONTENT_INDICATOR -> ScrollContentIndicatorCatalogFragment()
            Section.TAG -> TagsCatalogFragment()
            Section.LISTS -> ListsCatalogFragment()
            Section.HEADERS -> HeadersCatalogFragment()
            else -> OthersCatalogFragment()
        }

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.catalog_container, fragment)
        }.commit()
    }

    companion object {
        const val EXTRA_SECTION = "extra_section"
        const val EXTRA_THEME = "extra_theme"

        const val NO_THEME_OVERRIDE = -1
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
    OTHERS
}