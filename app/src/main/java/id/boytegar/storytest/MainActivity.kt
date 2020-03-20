package id.boytegar.storytest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import id.boytegar.storytest.adapter.StoryAdapter
import id.boytegar.storytest.viewmodel.StoryViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var storyViewModel: StoryViewModel
    var size_page = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        title = "Sedang Mengambil data..."
        storyViewModel = ViewModelProvider(this).get(StoryViewModel::class.java)

        storyViewModel.size_list.observe(this, Observer {
            size_page = it
        })

        storyViewModel.dataStory.observe(this!!, Observer {
            if(it.size == size_page){
                title = "Data Berhasil Di Ambil"
                progressBar.visibility = View.GONE
                val adapter = StoryAdapter(this, R.layout.list_item, it)
                val linearLayoutManager = LinearLayoutManager(this)
                list_items.layoutManager = linearLayoutManager
                list_items.hasFixedSize()
                list_items.adapter = adapter
                adapter.onItemClick = { it1 ->
                    val dats = Gson().toJson(it1)
                    val intent = Intent(this, DetailStoryActivity::class.java)
                    intent.putExtra("data", dats)
                    startActivityForResult(intent, 1)
                }
            }
        })

        storyViewModel.showError.observe(this, Observer {
            Log.e("ERROR", it)
        })
        storyViewModel.list.observe(this, Observer {
            Log.e("LIST", it.toString())
        })
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
                val myStr = data?.getStringExtra("MyData")
                if (myStr!=null){
                    txt_fav.text = "Fav: $myStr"
                }else{
                    txt_fav.text = "no data"
                }
    }

}
