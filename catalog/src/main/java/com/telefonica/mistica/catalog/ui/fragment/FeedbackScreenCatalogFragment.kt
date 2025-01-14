package com.telefonica.mistica.catalog.ui.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.annotation.StyleRes
import androidx.fragment.app.Fragment
import com.telefonica.mistica.catalog.R
import com.telefonica.mistica.catalog.ui.activity.FeedbackScreenCatalogActivity
import com.telefonica.mistica.feedback.screen.view.FeedbackScreenView
import com.telefonica.mistica.feedback.screen.view.FeedbackScreenView.FeedbackType
import com.telefonica.mistica.input.CheckBoxInput
import com.telefonica.mistica.input.DropDownInput
import com.telefonica.mistica.input.TextInput

class FeedbackScreenCatalogFragment(
    @StyleRes private val themeOverride: Int? = null
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.screen_fragment_feedback_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val typeDropDown: DropDownInput = view.findViewById(R.id.dropdown_feedback_type)
        val inputTitle: TextInput = view.findViewById(R.id.input_feedback_title)
        val inputSubtitle: TextInput = view.findViewById(R.id.input_feedback_subtitle)
        val inputFirstButtonText: TextInput = view.findViewById(R.id.input_feedback_first_button)
        val inputSecondButtonText: TextInput = view.findViewById(R.id.input_feedback_second_button)
        val checkBoxSecondButtonAsLink: CheckBoxInput = view.findViewById(R.id.check_feedback_second_button_as_link)
        val checkBoxShowLoadingInButton: CheckBoxInput = view.findViewById(R.id.check_feedback_show_loading_in_button)
        val createButton: Button = view.findViewById(R.id.button_create_feedback)

        with(typeDropDown.dropDown) {
            setAdapter(
                DropDownInput.Adapter(
                    view.context,
                    R.layout.dropdown_menu_popup_item,
                    Type.values().map { it.name }
                )
            )
            setText(Type.SUCCESS.name)
        }

        createButton.setOnClickListener {
            showFeedbackActivity(
                type = Type.valueOf(typeDropDown.dropDown.text.toString()).type,
                context = view.context,
                title = inputTitle.toNullableString(),
                subtitle = inputSubtitle.toNullableString(),
                firstButtonText = inputFirstButtonText.toNullableString(),
                secondButtonText = inputSecondButtonText.toNullableString(),
                showSecondButtonAsLink = checkBoxSecondButtonAsLink.isChecked(),
                showLoadingInButton = checkBoxShowLoadingInButton.isChecked()
            )
        }
    }

    private fun showFeedbackActivity(
        context: Context,
        @FeedbackType type: Int,
        title: String?,
        subtitle: String?,
        @LayoutRes customContentLayout: Int = FeedbackScreenCatalogActivity.INVALID_DEFAULT_VALUE,
        firstButtonText: String?,
        secondButtonText: String?,
        showSecondButtonAsLink: Boolean = FeedbackScreenCatalogActivity.SHOW_SECOND_BUTTON_AS_LINK_DEFAULT_VALUE,
        showLoadingInButton: Boolean = FeedbackScreenCatalogActivity.SHOW_LOADING_IN_BUTTON_DEFAULT_VALUE
    ) {
        Intent(context, FeedbackScreenCatalogActivity::class.java).apply {
            putExtra(FeedbackScreenCatalogActivity.EXTRA_TYPE, type)
            putExtra(FeedbackScreenCatalogActivity.EXTRA_TITLE, title)
            putExtra(FeedbackScreenCatalogActivity.EXTRA_SUBTITLE, subtitle)
            putExtra(FeedbackScreenCatalogActivity.EXTRA_FIRST_BUTTON_TEXT, firstButtonText)
            putExtra(FeedbackScreenCatalogActivity.EXTRA_SECOND_BUTTON_TEXT, secondButtonText)
            putExtra(FeedbackScreenCatalogActivity.EXTRA_CUSTOM_CONTENT, customContentLayout)
            putExtra(
                FeedbackScreenCatalogActivity.EXTRA_SHOW_SECOND_BUTTON_AS_LINK,
                showSecondButtonAsLink
            )
            putExtra(
                FeedbackScreenCatalogActivity.EXTRA_SHOW_LOADING_IN_BUTTON,
                showLoadingInButton
            )
            themeOverride?.let { putExtra(FeedbackScreenCatalogActivity.EXTRA_THEME, it) }
            context.startActivity(this)
        }
    }

    private fun TextInput.toNullableString(): String? = this.text.toString()

    private enum class Type(@FeedbackType val type: Int) {
        SUCCESS(FeedbackScreenView.TYPE_SUCCESS),
        ERROR(FeedbackScreenView.TYPE_ERROR),
        INFO(FeedbackScreenView.TYPE_INFO)
    }
}