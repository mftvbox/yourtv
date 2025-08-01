package com.horsenma.mytv1.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.horsenma.mytv1.SP
import com.horsenma.mytv1.data.TV

class TVModel(var tv: TV) : ViewModel() {
    private val _position = MutableLiveData<Int>()
    val position: LiveData<Int>
        get() = _position

    var retryTimes = 0
    var retryMaxTimes = 8

    var groupIndex = 0
    var listIndex = 0

    private val _errInfo = MutableLiveData<String>()
    val errInfo: LiveData<String>
        get() = _errInfo

    fun setErrInfo(info: String) {
        _errInfo.value = info
    }

    private val _videoUrl = MutableLiveData<String>()
    val videoUrl: LiveData<String>
        get() = _videoUrl

    fun setVideoUrl(url: String) {
        _videoUrl.value = url
    }

    private fun getVideoUrl(): String? {
        if (_videoIndex.value == null || tv.uris.isEmpty()) {
            return null
        }

        if (videoIndex.value!! >= tv.uris.size) {
            return null
        }

        return tv.uris[_videoIndex.value!!]
    }

    private val _like = MutableLiveData<Boolean>()
    val like: LiveData<Boolean>
        get() = _like

    fun setLike(liked: Boolean) {
        _like.value = liked
    }

    private val _ready = MutableLiveData<Boolean>()
    val ready: LiveData<Boolean>
        get() = _ready

    fun setReady() {
        _ready.value = true
    }

    private val _videoIndex = MutableLiveData<Int>()
    private val videoIndex: LiveData<Int>
        get() = _videoIndex

    init {
        _position.value = 0
        _videoIndex.value = 0
        _like.value = SP.getLike(tv.id)
        val videoUrl = getVideoUrl()
        _videoUrl.value = videoUrl ?: ""
    }

    fun update(t: TV) {
        tv = t
    }

    companion object {
        private const val TAG = "TVModel"
    }
}