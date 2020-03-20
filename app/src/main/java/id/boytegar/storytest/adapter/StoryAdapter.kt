package id.boytegar.storytest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.boytegar.storytest.model.DetailStory
import kotlinx.android.synthetic.main.list_item.view.*

class StoryAdapter(context: Context, var list: Int,var datas: List<DetailStory>) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {

    lateinit var onItemClick : ((DetailStory) -> Unit)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = datas[position]
        holder.bind(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: DetailStory) {
            itemView.txt_title.text = data.title
            itemView.txt_score.text = "Score ${data.score}"

            data.kids?.let {
                itemView.txt_comments.text = "Comment ${data.kids?.size}"
            } ?: run {
                itemView.txt_comments.text = "Comment 0"
            }
            itemView.setOnClickListener {
                onItemClick.invoke(data)
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