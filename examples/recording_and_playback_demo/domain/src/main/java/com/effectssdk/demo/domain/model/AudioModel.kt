package com.effectssdk.demo.domain.model

import java.util.Date

data class AudioModel(
	val name: String = "",
	var isPlayedDenoise: Boolean = false,
	var isPlayedOriginal: Boolean = false,
	val creationDate: Date = Date(0),
	var isSelected: Boolean = false,
)
