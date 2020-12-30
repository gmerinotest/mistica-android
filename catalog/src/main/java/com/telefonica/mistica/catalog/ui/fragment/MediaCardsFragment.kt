package com.telefonica.mistica.catalog.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.telefonica.mistica.catalog.R
import com.telefonica.mistica.input.CheckBoxInput
import com.telefonica.mistica.input.TextInput
import com.telefonica.mistica.mediacard.MediaCardView


class MediaCardsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.media_card_fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.media_card_button_update)
            .setOnClickListener { updateMediaCardView(view) }
        updateMediaCardView(view)
    }

    private fun updateMediaCardView(view: View) {
        with(view.findViewById<MediaCardView>(R.id.media_card_view)) {
            setMultimedia(view)
            setTag(view.findViewById<TextInput>(R.id.input_tag).text.toString())
            setTitle(view.findViewById<TextInput>(R.id.input_title).text.toString())
            setSubtitle(view.findViewById<TextInput>(R.id.input_subtitle).text.toString())
            setDescription(view.findViewById<TextInput>(R.id.input_description).text.toString())
            setPrimaryButtonText(view.findViewById<TextInput>(R.id.input_primary_button).text.toString())
            setLinkButtonText(view.findViewById<TextInput>(R.id.input_link_button).text.toString())
            setAditionalCardContent(view)
            setClickListeners(view)
        }
    }

    private fun MediaCardView.setClickListeners(view: View) {
        setPrimaryButtonOnClick(View.OnClickListener {
            Toast.makeText(
                view.context,
                "Primary button clicked!",
                Toast.LENGTH_SHORT
            ).show()
        })
        setLinkButtonOnClick(View.OnClickListener {
            Toast.makeText(
                view.context,
                "Secondary button clicked!",
                Toast.LENGTH_SHORT
            ).show()
        })
        setMediaCardOnClick(View.OnClickListener {
            Toast.makeText(
                view.context,
                "Media card clicked!",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun MediaCardView.setAditionalCardContent(view: View) {
        if (view.findViewById<CheckBoxInput>(R.id.additional_content_checkbox).isChecked()) {
            val additionalContent = LayoutInflater.from(context).inflate(
                R.layout.media_card_additional_sample_content,
                this,
                false
            )
            setMediaCardAdditionalContent(additionalContent)
        } else {
            setMediaCardAdditionalContent(null)
        }
    }

    private fun MediaCardView.setMultimedia(view: View) {
        if (view.findViewById<CheckBoxInput>(R.id.video_content_checkbox).isChecked()) {
//            val mediaUri = Uri.parse(
//                "android.resource://"
//                        + context.packageName.toString() + "/raw/" + VIDEO_SAMPLE_LOCAL_FILE
//            )
                    val mediaUri = Uri.parse(VIDEO_SAMPLE_INTERNET_FILE)
            setVideo(mediaUri)
        } else {
            setImage(R.drawable.media_card_sample_image)
        }
    }

    companion object {
        private const val VIDEO_SAMPLE_LOCAL_FILE = "tacoma_narrows"
        private const val VIDEO_SAMPLE_INTERNET_FILE =
            "https://developers.google.com/training/images/tacoma_narrows.mp4"
    }
}