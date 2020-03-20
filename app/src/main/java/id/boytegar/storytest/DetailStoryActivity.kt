package id.boytegar.storytest

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import id.boytegar.storytest.adapter.CommentAdapter
import id.boytegar.storytest.adapter.StoryAdapter
import id.boytegar.storytest.model.DetailStory
import id.boytegar.storytest.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.activity_detail_story.*
import java.text.SimpleDateFormat
import java.util.*


class DetailStoryActivity : AppCompatActivity() {

    lateinit var storyViewModel: StoryViewModel
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_story)
        setSupportActionBar(toolbar2)
        title = "Detail Story"
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
        val data = intent.getStringExtra("data")
        initView(data)


    }

    fun initView(data: String){
        val detailStory =
            Gson().fromJson(data!!, DetailStory::class.java)
        txt_title.text = detailStory.title
        txt_name.text = "By: ${detailStory.by}"
        if(detailStory.text != null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                txt_desc.text = (Html.fromHtml(detailStory.text, Html.FROM_HTML_MODE_COMPACT))
            } else {
                txt_desc.text = (Html.fromHtml(detailStory.text))
            }
        }else{
            txt_desc.text = "tidak Ada Deskripsi"
        }

        imageView.setOnClickListener {
            val intent = Intent()
            intent.putExtra("MyData", detailStory.title)
            setResult(1, intent)
            finish()
        }

        txt_date.text = "Time : "+UnixToDate(detailStory.time!!)



        storyViewModel.dataStory.observe(this, androidx.lifecycle.Observer {
            if(detailStory.kids?.size == it.size){
                val adapter = CommentAdapter(this, R.layout.list_comment, it)
                val linearLayoutManager = LinearLayoutManager(this)
                list_comment.layoutManager = linearLayoutManager
                list_comment.hasFixedSize()
                list_comment.adapter = adapter
            }

        })
        if (detailStory.kids != null){
            storyViewModel.getComment(detailStory.kids!!)
        }

    }

    fun UnixToDate(unix: Int): String{
        val date = Date(unix * 1000L)
        val sdf = SimpleDateFormat("yyyy:MM:dd")

        //sdf.timeZone = TimeZone.getTimeZone("GMT-4")
        val formattedDate = sdf.format(date)
        return formattedDate
    }
}
