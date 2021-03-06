package dev.tran.nam.chart.chartsong.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.tran.nam.chart.chartsong.R
import dev.tran.nam.chart.chartsong.di.module.GlideApp
import nam.tran.data.model.DownloadStatus
import nam.tran.data.model.DownloadStatus.*
import nam.tran.data.model.SongStatus
import nam.tran.data.model.SongStatus.*
import nam.tran.data.model.core.state.ErrorResource

object BindingView {
    @JvmStatic
    @BindingAdapter("loadImageSong")
    fun loadImageNetwork(image: AppCompatImageView, urlImage: String?) {
        urlImage?.let {
            val circularProgressDrawable = CircularProgressDrawable(image.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()
            GlideApp.with(image).load(urlImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .thumbnail(0.5f)
                .transition(DrawableTransitionOptions.withCrossFade()).placeholder(circularProgressDrawable)
                .error(R.drawable.image_error).into(image)
        }
    }

    @JvmStatic
    @BindingAdapter("songStatus")
    fun updateSongStatus(image: AppCompatImageView, @SongStatus status: Int) {
        val drawable = when (status) {
            NONE_STATUS, CANCEL_DOWNLOAD -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_download)
            }
            DOWNLOADING, ERROR, CLOSE, CANCELING_DOWNLOAD -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_close)
            }
            PLAY, PAUSE_SONG -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_play)
            }
            PLAYING -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_pause)
            }
            else -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_download)
            }
        }
        image.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("downloadStatus")
    fun updateDownloadStatus(image: AppCompatImageView, @DownloadStatus status: Int) {
        val drawable = when (status) {
            RUNNING -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_pause)
            }
            PAUSE, NONE -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_play)
            }
            else -> {
                ContextCompat.getDrawable(image.context, R.drawable.icon_play)
            }
        }
        image.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter("updateErrorDownload")
    fun updateErrorDownload(textView : AppCompatTextView,errorResource: ErrorResource?){
        errorResource?.run {
            textView.text = String.format(textView.resources.getString(R.string.error), message)
        }
    }
}