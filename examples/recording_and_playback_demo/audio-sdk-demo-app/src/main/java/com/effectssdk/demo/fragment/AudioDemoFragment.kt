package com.effectssdk.demo.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.effectssdk.demo.R
import com.effectssdk.demo.adapter.AudioListAdapter
import com.effectssdk.demo.domain.model.AudioModel
import com.effectssdk.demo.presenter.AudioDemoFragmentPresenter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class AudioDemoFragment : BaseFragment(), AudioDemoFragmentPresenter.AudioDemoFragmentView {

	private lateinit var audioListView: RecyclerView
	private lateinit var recordAudioButton: AppCompatButton
	private lateinit var recordAudioNotification: AppCompatTextView
	private lateinit var progressBar: ProgressBar
	private lateinit var enablePlayback: CheckBox
	private lateinit var enableNoiseSuppression: CheckBox

	private lateinit var presenter: AudioDemoFragmentPresenter
	private lateinit var audioListAdapter: AudioListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		presenter = AudioDemoFragmentPresenter(
			requireContext(), application.audioListManager, this
		)
	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_audio_demo, container, false)
		recordAudioButton = view.findViewById(R.id.record_test_audio_button)
		enablePlayback = view.findViewById(R.id.enable_playback)
		enableNoiseSuppression = view.findViewById(R.id.enable_noise_suppression)
		recordAudioNotification = view.findViewById(R.id.recording)
		audioListView = view.findViewById(R.id.audio_file_list)
		progressBar = view.findViewById(R.id.sdk_init_bar)
		return view

	}

	@SuppressLint("ClickableViewAccessibility")
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		viewLifecycleOwner.lifecycle.addObserver(presenter)
		recordAudioButton.setOnClickListener {
			presenter.onRecordAudioButtonClicked()
		}
//		recordAudioButton.setOnTouchListener { _, event ->
//			when (event.action) {
//				MotionEvent.ACTION_DOWN -> {
//					recordAudioNotification.isVisible = true
//					presenter.startSpeechRecord()
//				}
//				MotionEvent.ACTION_UP -> {
//					recordAudioNotification.isVisible = false
//					presenter.startSpeechRecord()
//				}
//			}
//			false
//		}

		val audioButtonListener = object : AudioListAdapter.AudioListAdapterListener {

			override fun onRemoveClicked(filename: String) {
				presenter.removeAudio(filename)
			}

			override fun onRenameClicked(filename: String, newFilename: String) {
				presenter.renameAudioFile(filename, newFilename)
			}

			override fun onStopClicked() {
				presenter.stopAudio()
			}

			override fun onItemClicked(audioModel: AudioModel) {
				audioModel.isSelected = true
				presenter.onAudioItemClicked(audioModel)
			}

			override fun onPlayClicked(audioModel: AudioModel) {
				presenter.playAudio(audioModel)
			}

			override fun onPlayDenoiseClicked(audioModel: AudioModel) {
				presenter.playDenoiseAudio(audioModel)
			}


		}
		audioListAdapter = AudioListAdapter(audioButtonListener, requireContext())
		audioListView.adapter = audioListAdapter
		audioListView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

		enablePlayback.setOnCheckedChangeListener { _, isChecked ->
			presenter.enablePlayback(isChecked)
			if (isChecked) Toast.makeText(requireContext(), "Please, use headphones for this option", Toast.LENGTH_SHORT).show()
		}

		enableNoiseSuppression.setOnCheckedChangeListener { _, isChecked ->
			presenter.enableNoiseSuppressionForPlayback(isChecked)
		}
	}

	override fun updateAudioList(list: List<AudioModel>) {
		audioListAdapter.updateItems(list)
		audioListAdapter.notifyDataSetChanged()
	}

	override fun showProgressBar(isShown: Boolean) {
		progressBar.isVisible = isShown
	}

	override fun updateRecordButtonState(isRecordEnabled: Boolean, seconds: Int) {
		val timeString = LocalTime.ofSecondOfDay(seconds.toLong()).format(DateTimeFormatter.ofPattern("HH:mm:ss"))
		requireActivity().runOnUiThread {
			recordAudioButton.text = if (isRecordEnabled) timeString else resources.getString(R.string.start_mic_capturing)
		}
	}

	override fun showToast(text: String) {
		requireActivity().runOnUiThread {
			Toast.makeText(context, text, Toast.LENGTH_LONG).show()
		}
	}

}