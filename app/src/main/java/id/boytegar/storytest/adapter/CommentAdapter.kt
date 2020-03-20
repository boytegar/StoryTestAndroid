package id.boytegar.storytest.adapter

import android.content.Context
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.boytegar.storytest.model.DetailStory
import kotlinx.android.synthetic.main.list_comment.view.*

class CommentAdapter(context: Context, var list: Int, var datas: List<DetailStory>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datas[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: DetailStory) {

            if(data.text != null){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    itemView.txt_comments.text = (Html.fromHtml(data.text, Html.FROM_HTML_MODE_COMPACT))
                } else {
                    itemView.txt_comments.text = (Html.fromHtml(data.text))
                }
            }

        }
    }
    override fun onCreateViewHolder(views: ViewGroup, position: Int):ViewHolder {
        val layoutInflater = LayoutInflater.from(views.context).inflate(list,views,false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}