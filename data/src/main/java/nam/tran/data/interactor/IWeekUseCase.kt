package nam.tran.data.interactor

import androidx.lifecycle.LiveData
import nam.tran.data.model.DownloadData
import nam.tran.data.model.WeekChart
import nam.tran.data.model.WeekSong
import nam.tran.data.model.core.state.Resource

interface IWeekUseCase{
    val listWeekChart : LiveData<Resource<List<WeekChart>>>
    val listSongWeek : LiveData<Resource<List<WeekSong>>>
    val listSongDownload : LiveData<DownloadData>
    fun getData(position: Int? = null, pathFolder: String? = null)
    fun getDataExist(position: Int, listDownloadComplete: MutableList<Int>)
    fun downloadMusic(id: Int, url: String, resume: Boolean)
    fun updateStatusDownload(id: Int,status : Int,isDownload : Boolean)
    fun removeTaskDownload(item: DownloadData?)
}