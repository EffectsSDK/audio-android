package com.effectssdk.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.effectssdk.demo.R
import com.effectssdk.demo.domain.model.AudioModel


class AudioListAdapter(
	private val listener: AudioListAdapterListener,
	private val context: Context

) : RecyclerView.Adapter<AudioListAdapter.ViewHolder>() {

	private val data: ArrayList<AudioModel> = ArrayList()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.audio_file_list_item, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		holder.name.text = data[position].name
		holder.creationDate.text = data[position].creationDate.toString()
		holder.menuButton.setOnClickListener {
			showMenu(holder, position)
		}
		holder.container.setOnClickListener {
			listener.onItemClicked(data[position])
		}
		val listIndex = position + 1
		holder.index.text = listIndex.toString()
		val color = if (data[position].isSelected) context.getColor(R.color.grey) else context.getColor(R.color.white)
		holder.container.setBackgroundColor(color)

		holder.playButtonContainer.visibility = if (data[position].isSelected) View.VISIBLE else View.GONE
		holder.playButton.text = if (data[position].isPlayedOriginal) "stop" else "original"
		holder.playDenoiseButton.text = if (data[position].isPlayedDenoise) "stop" else "denoise"
		updateButtonDrawable(holder.playButton, data[position].isPlayedOriginal)
		updateButtonDrawable(holder.playDenoiseButton, data[position].isPlayedDenoise)

		holder.playButton.setOnClickListener {
			var model = data[position]
			model = model.copy(isPlayedOriginal = true)
			listener.onPlayClicked(model)
		}
		holder.playDenoiseButton.setOnClickListener {
			var model = data[position]
			model = model.copy(isPlayedDenoise = true)
			listener.onPlayDenoiseClicked(model)
		}
	}

	private fun showMenu(holder: ViewHolder, position: Int) {
		val menu = PopupMenu(context, holder.menuButton)
		menu.inflate(R.menu.menu_audio_file)
		menu.setOnMenuItemClickListener {
			when (it.itemId) {
				R.id.remove -> {
					listener.onStopClicked()
					listener.onRemoveClicked(data[position].name)
					return@setOnMenuItemClickListener true
				}

				R.id.rename -> {
					showRenameFileDialog(data[position].name)
					return@setOnMenuItemClickListener true
				}

				else -> {
					return@setOnMenuItemClickListener false
				}
			}
		}
		menu.show()
	}

	override fun getItemCount(): Int {
		return data.size
	}

	fun updateItems(list: List<AudioModel>) {
		data.clear()
		data.addAll(list.sortedBy { it.creationDate.time }.reversed())
	}

	private fun updateButtonDrawable(button: Button, isPlayed: Boolean) {
		if (isPlayed) {
			button.setCompoundDrawablesWithIntrinsicBounds(
				null,
				AppCompatResources.getDrawable(context, R.drawable.ic_stop_circle),
				null,
				null
			)
		} else {
			button.setCompoundDrawablesWithIntrinsicBounds(
				null,
				AppCompatResources.getDrawable(context, R.drawable.ic_play_circle),
				null,
				null
			)
		}

	}

	private fun showRenameFileDialog(oldFile: String) {
		val alert: AlertDialog.Builder = AlertDialog.Builder(context)
		val filenameTextField = EditText(context)
		filenameTextField.setText(oldFile)
		alert.setTitle(R.string.rename_file_dialog_title)
		alert.setView(filenameTextField)

		alert.setPositiveButton(R.string.apply) { _, _ ->
			val newFilename = filenameTextField.text.toString()
			listener.onRenameClicked(oldFile, newFilename)
		}

		alert.setNegativeButton(R.string.cancel) { _, _ ->

		}

		alert.show()
	}

	class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val container: ViewGroup = itemView.findViewById(R.id.container)
		val index: AppCompatTextView = itemView.findViewById(R.id.index)
		val name: AppCompatTextView = itemView.findViewById(R.id.file_name)
		val creationDate: AppCompatTextView = itemView.findViewById(R.id.file_creation_date)
		val menuButton: AppCompatImageButton = itemView.findViewById(R.id.options_menu)
		val playButtonContainer: LinearLayout = itemView.findViewById(R.id.play_button_container)
		val playButton: AppCompatButton = itemView.findViewById(R.id.play_original)
		val playDenoiseButton: AppCompatButton = itemView.findViewById(R.id.play_denoise)
	}

	interface AudioListAdapterListener {
		fun onRemoveClicked(filename: String)
		fun onRenameClicked(filename: String, newFilename: String)
		fun onStopClicked()
		fun onItemClicked(audioModel: AudioModel)
		fun onPlayClicked(audioModel: AudioModel)
		fun onPlayDenoiseClicked(audioModel: AudioModel)
	}
}